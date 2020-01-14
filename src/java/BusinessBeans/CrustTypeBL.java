/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.CrustTypeDL;
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
public class CrustTypeBL {

    public static CrustTypeDL ctdl = CrustTypeDL.GetInstance();
    
    public static ArrayList<CrustType> getAllAvailableCrustTypes() {
        return ctdl.getAllAvailableCrustTypes();
    }
    
    public static CrustType FetchCrustTypeByName(String name){
        return ctdl.FetchCrustTypeByName(name);
    }
}
