/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

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
public class ToppingsDL {

    public static ToppingsDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected ToppingsDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static ToppingsDL GetInstance() {
        if (instance == null) {
            instance = new ToppingsDL();//calls the protected constructor
        }
        return instance;
    }

    public static int addTopping(Toppings t) {
        try {
            String sql = "insert into toppings (name, price, empAddedBy, isActive) values (?,?,?,1)";
            PreparedStatement s = conn.prepareStatement(sql);
            s.setString(1, t.getName());
            s.setDouble(2, t.getPrice());
            s.setInt(3, Toppings.getEmpAddedBy());
            return s.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Toppings.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static int deleteTopping(Toppings t) {
        try {
            
            String sql = "update pizzadb.toppings set isActive = 0 where toppingId = " + t.getToppingId();
            PreparedStatement s = conn.prepareStatement(sql);
            return s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Toppings.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static int updateTopping(Toppings t) {
        try {
            
            String sql = "update pizzadb.toppings set name = ? AND price = ? AND isActive = ? where toppingId = " + t.getToppingId();
            PreparedStatement s = conn.prepareStatement(sql);
            s.setString(1, t.getName());
            s.setDouble(2, t.getPrice());
            s.setBoolean(3, t.isIsActive());
            return s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Toppings.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static Toppings getToppingById(int id) {
        try {
            
            String sql = "select * from pizzadb.toppings where toppingID = " + id;
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            return new Toppings(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getBoolean(5));
        } catch (SQLException ex) {
            Logger.getLogger(Toppings.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ArrayList<Toppings> getAllAvailableToppings() {
        
        ArrayList<Toppings> toppings = new ArrayList<Toppings>();
        String sql = "select * from toppings where isActive = 1";
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            //loop through for each topping on the pizza: get the topping info from the database, 
            //create a new topping with it and add it to the arraylist
            do {
                toppings.add(new Toppings(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDate(4), rs.getBoolean(6)));
            } while (rs.next());
            return toppings;
        } catch (SQLException ex) {
            return null;
        }
    }
}
