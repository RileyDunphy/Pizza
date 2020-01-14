/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.CustomerDL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;

/**
 *
 * @author riley
 */
@Stateless
public class CustomerBL {
    
    public static CustomerDL cdl = CustomerDL.GetInstance();

    public static Customer getCustomer(int id) {
        return cdl.getCustomer(id);
    }

    public static void addCustomer(Customer c) {
        cdl.addCustomer(c);
    }

    public static boolean updateCustomer(Customer c) {
        return cdl.updateCustomer(c);
    }
}
