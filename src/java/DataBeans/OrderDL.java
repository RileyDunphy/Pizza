/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.Customer;
import BusinessBeans.CustomerBL;
import BusinessBeans.Order;
import BusinessBeans.Toppings;
import static DataBeans.SizeDL.conn;
import static DataBeans.SizeDL.gt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class OrderDL {

    public static OrderDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected OrderDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static OrderDL GetInstance() {
        if (instance == null) {
            instance = new OrderDL();//calls the protected constructor
        }
        return instance;
    }

    public static Order getOrder(int id) {
        
        String sql = "select * from orders where orderId = " + id;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            return new Order(rs.getInt(1), rs.getDouble(2), rs.getString(6), rs.getDate(3), rs.getDate(4), CustomerBL.getCustomer(rs.getInt(5)));
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void addOrder(Order o) {
        
        String sql = "insert into orders (totalPrice,deliveryDateTime,customerId)values(?,?,?)";
        try {
            PreparedStatement s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            s.setDouble(1, o.getTotalPrice());
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String currentTime = LocalDateTime.now().plusMinutes(30).format(formatter);
            s.setString(2, currentTime);
            s.setInt(3, o.getCustomer().getCustomerId());
            s.executeUpdate();
            ResultSet tableKeys = s.getGeneratedKeys();
            tableKeys.first();
            o.setOrderId(tableKeys.getInt(1));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean updateOrder(Order o) {
        
        String sql = "update orders set totalPrice=?,deliveryDateTime=?,placedDateTime=?,orderStatus=? where orderId = ?";
        try {
            PreparedStatement s = conn.prepareStatement(sql);
            s.setDouble(1, o.getTotalPrice());
            s.setDate(2, o.getDeliveryDateTime());
            s.setDate(3, o.getPlaceDateTime());
            s.setString(4, o.getOrderStatus());
            s.setInt(5, o.getOrderId());
            if (s.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public static ArrayList<Order> getOrders(String orderStatus) {
        
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = null;
        if (orderStatus.equals("Pending")) {
            sql = "select * from pizzadb.orders where orderstatus = 'PENDING'";
        } else if (orderStatus.equals("Filled")) {
            sql = "select * from pizzadb.orders where orderstatus = 'FILLED'";
        }
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            //loop through for each order: get the order info from the database, 
            //create a new order with it and add it to the arraylist
            do {
                Customer customer = CustomerBL.getCustomer(rs.getInt(5));
                orders.add(new Order(rs.getInt(1), rs.getDouble(2), rs.getString(6), rs.getDate(3), rs.getDate(4), customer));
            } while (rs.next());
            return orders;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static int fillOrder(Order o) {
        try {
            
            String sql = "update pizzadb.orders set orderStatus = 'FILLED' where orderId = " + o.getOrderId();
            PreparedStatement s = conn.prepareStatement(sql);
            return s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Toppings.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }
}
