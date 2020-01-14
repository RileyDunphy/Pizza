/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.SizeDL;
import java.sql.Connection;
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
public class SizeBL {
    
    public static SizeDL sdl = SizeDL.GetInstance();

    public static ArrayList<Size> getAllAvailableSizes() {
        return sdl.getAllAvailableSizes();
    }
    
    public static Size FetchSizeByName(String name){
        return sdl.FetchSizeByName(name);
    }
}
