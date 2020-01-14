/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.OrderDL;
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
import javax.ejb.Stateless;

/**
 *
 * @author riley
 */
@Stateless
public class OrderBL {
    
    public static OrderDL odl = OrderDL.GetInstance();

    public static Order getOrder(int id) {
        return odl.getOrder(id);
    }

    public static void addOrder(Order o) {
        odl.addOrder(o);
    }

    public static boolean updateOrder(Order o) {
        return odl.updateOrder(o);
    }

    public static ArrayList<Order> getOrders(String orderStatus) {
        return odl.getOrders(orderStatus);
    }

    public static int fillOrder(Order o) {
        return odl.fillOrder(o);

    }
}
