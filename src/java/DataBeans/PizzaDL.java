/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.Pizza;
import BusinessBeans.Toppings;
import static DataBeans.SizeDL.conn;
import static DataBeans.SizeDL.gt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author riley
 */
@Singleton
public class PizzaDL {

    public static PizzaDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected PizzaDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static PizzaDL GetInstance() {
        if (instance == null) {
            instance = new PizzaDL();//calls the protected constructor
        }
        return instance;
    }

    public static void calculatePrice(Pizza p) {
        p.setPrice(0);
        switch (p.getCrustType().getCrustTypeId()) {
            case 1:
                p.setPrice(p.getPrice() + 0);
                break;

            case 2:
                p.setPrice(p.getPrice() + 1.99);
                break;

            case 3:
                p.setPrice(p.getPrice() + 0);
                break;

            case 4:
                p.setPrice(p.getPrice() + 2.49);
                break;

            case 5:
                p.setPrice(p.getPrice() + 1.49);
                break;
        }

        switch (p.getSize().getSizeId()) {
            case 1:
                p.setPrice(p.getPrice() + 7.00);
                break;
            case 2:
                p.setPrice(p.getPrice() + 12.00);
                break;
            case 3:
                p.setPrice(p.getPrice() + 17.00);
                break;
        }

    }

    public static void addPizza(Pizza p) {
        try {
            
            String sql = "insert into pizza (sizeId,isFinished,crustTypeId,price, orderId) values (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, p.getSize().getSizeId());
            stmt.setBoolean(2, p.isIsFinished());
            stmt.setInt(3, p.getCrustType().getCrustTypeId());
            stmt.setDouble(4, p.getPrice());
            stmt.setInt(5, p.getOrder().getOrderId());
            stmt.executeUpdate();
            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.first();
            p.setPizzaId(tableKeys.getInt(1));
            for (Toppings t : p.getToppings()) {
                mapTopping(t, p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pizza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Toppings> getToppings(int pizzaId) {
        
        ArrayList<Toppings> toppings = new ArrayList<Toppings>();
        //Get all the toppings on the pizza
        String sql = "select * from pizza_toppings_map where pizzaId = " + pizzaId;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            //loop through for each topping on the pizza: get the topping info from the database, 
            //create a new topping with it and add it to the arraylist
            do {
                sql = "select * from toppings where toppingId = " + rs.getInt(1);
                Statement s2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rsTopping = s2.executeQuery(sql);
                rsTopping.first();
                toppings.add(new Toppings(rsTopping.getInt(1), rsTopping.getString(2), rsTopping.getDouble(3), rsTopping.getDate(4), rsTopping.getBoolean(6)));
                rsTopping.close();
            } while (rs.next());
            return toppings;
        } catch (SQLException ex) {
            Logger.getLogger(Pizza.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void mapTopping(Toppings t, Pizza p) {
        try {
            
            String sql = "insert into pizza_toppings_map (toppingId, pizzaId) values (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, t.getToppingId());
            stmt.setInt(2, p.getPizzaId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pizza.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
