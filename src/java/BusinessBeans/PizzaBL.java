/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.PizzaDL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author riley
 */
@Stateless
public class PizzaBL {
    
    public static PizzaDL pdl = PizzaDL.GetInstance();

    public static void calculatePrice(Pizza p) {
        pdl.calculatePrice(p);

    }

    public static void addPizza(Pizza p) {
       pdl.addPizza(p);
    }

    public static ArrayList<Toppings> getToppings(int pizzaId) {
        return pdl.getToppings(pizzaId);
    }

    public static void mapTopping(Toppings t, Pizza p) {
        pdl.mapTopping(t,p);
    }
}
