/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.Size;
import java.sql.Connection;
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
public class SizeDL {

    public static SizeDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected SizeDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static SizeDL GetInstance() {
        if (instance == null) {
            instance = new SizeDL();//calls the protected constructor
        }
        return instance;
    }

    public static ArrayList<Size> getAllAvailableSizes() {
        ArrayList<Size> sizes = new ArrayList<Size>();
        String sql = "select * from sizes";
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            do {
                sizes.add(new Size(rs.getInt(1), rs.getString(2)));
            } while (rs.next());
            return sizes;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static Size FetchSizeByName(String name) {
        try {
            String sql = "select * from pizzadb.sizes where name = \"" + name + "\"";
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            return new Size(rs.getInt(1), rs.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(Size.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
