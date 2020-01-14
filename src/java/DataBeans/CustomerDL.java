/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.Customer;
import static DataBeans.SizeDL.conn;
import static DataBeans.SizeDL.gt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author riley
 */
@Singleton
public class CustomerDL {

    public static CustomerDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected CustomerDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static CustomerDL GetInstance() {
        if (instance == null) {
            instance = new CustomerDL();//calls the protected constructor
        }
        return instance;
    }

    public static Customer getCustomer(int id) {
        
        String sql = "select * from customer where customerid = " + id;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(1));
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void addCustomer(Customer c) {
        
        String sql = "insert into customer (firstName,lastName,phoneNumber,email,houseNumber,street,province,postalCode) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            s.setString(1, c.getFirstName());
            s.setString(2, c.getLastName());
            s.setString(3, c.getPhoneNumber());
            s.setString(4, c.getEmail());
            s.setInt(5, c.getHouseNumber());
            s.setString(6, c.getStreet());
            s.setString(7, c.getProvince());
            s.setString(8, c.getPostalCode());
            s.executeUpdate();
            ResultSet tableKeys = s.getGeneratedKeys();
            tableKeys.first();
            c.setCustomerId(tableKeys.getInt(1));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean updateCustomer(Customer c) {
        
        String sql = "update customers set firstName=?,lastName=?,phoneNumber=?,email=?,houseNumber=?,street=?,province=?,postalCode=? where customerId = ?";
        try {
            PreparedStatement s = conn.prepareStatement(sql);
            s.setString(1, c.getFirstName());
            s.setString(2, c.getLastName());
            s.setString(3, c.getPhoneNumber());
            s.setString(4, c.getEmail());
            s.setInt(5, c.getHouseNumber());
            s.setString(6, c.getStreet());
            s.setString(7, c.getProvince());
            s.setString(8, c.getPostalCode());
            s.setInt(9, c.getCustomerId());
            if (s.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
}
