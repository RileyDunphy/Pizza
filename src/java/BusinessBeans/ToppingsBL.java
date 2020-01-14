/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.ToppingsDL;
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
public class ToppingsBL {
    
    public static ToppingsDL tdl = ToppingsDL.GetInstance();

    public static int addTopping(Toppings t) {
        return tdl.addTopping(t);
    }

    public static int deleteTopping(Toppings t) {
        return tdl.deleteTopping(t);
    }

    public static int updateTopping(Toppings t) {
        return tdl.updateTopping(t);
    }
    
    public static Toppings getToppingById(int id){
        return tdl.getToppingById(id);
    }

    public static ArrayList<Toppings> getAllAvailableToppings() {
        return tdl.getAllAvailableToppings();
    }
}
