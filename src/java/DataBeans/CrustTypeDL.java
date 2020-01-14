/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.CrustType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author riley
 */
@Singleton
public class CrustTypeDL {
    
    public static CrustTypeDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;
    
    protected CrustTypeDL(){
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static CrustTypeDL GetInstance(){
        if(instance == null){
            instance = new CrustTypeDL();//calls the protected constructor
        }
        return instance;
    }
    
    public ArrayList<CrustType> getAllAvailableCrustTypes() {
        
        ArrayList<CrustType> crustTypes = new ArrayList<CrustType>();
        String sql = "select * from crusttypes";
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            //loop through for each topping on the pizza: get the topping info from the database, 
            //create a new topping with it and add it to the arraylist
            do {
                crustTypes.add(new CrustType(rs.getInt(1),rs.getString(2)));
            } while (rs.next());
            return crustTypes;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public CrustType FetchCrustTypeByName(String name){
        try {
            
            String sql = "select * from pizzadb.crusttypes where name = \"" + name+"\"";
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            return new CrustType(rs.getInt(1), rs.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(CrustType.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
