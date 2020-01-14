/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author riley
 */
@Stateless
public class CrustType {

    private int crustTypeId;
    private String name;

    public CrustType() {
    }

    public CrustType(int crustTypeId, String name) {
        this.crustTypeId = crustTypeId;
        this.name = name;
    }

    public int getCrustTypeId() {
        return crustTypeId;
    }

    public void setCrustTypeId(int crustTypeId) {
        this.crustTypeId = crustTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
