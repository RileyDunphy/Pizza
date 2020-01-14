/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import static DataBeans.PizzaDL.conn;
import static DataBeans.PizzaDL.instance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

/**
 *
 * @author riley
 */
@Singleton
public class GetConnection {
    
    private static Connection conn;
    private static GetConnection instance;
    
    protected GetConnection() {
        //JDBC
        String dbURL = "jdbc:mysql://localhost:3306/pizzadb?useSSL=false";
        String username = "root";
        String password = "";//blank password for root

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GetConnection GetInstance() {
        if (instance == null) {
            instance = new GetConnection();//calls the protected constructor
        }
        return instance;
    }
    
    public static Connection GetConnection(){
        return conn;
    }
}
