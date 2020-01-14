/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author riley
 */
@Stateless
public class Toppings {

    private int toppingId;
    private String name;
    private double price;
    private Date createDate;
    private boolean isActive;
    private static int empAddedBy;

    public Toppings() {
        
    }

    public Toppings(int toppingId, String name, double price, Date createDate, boolean isActive) {
        this.toppingId = toppingId;
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.isActive = isActive;
    }

    public static int getEmpAddedBy() {
        return empAddedBy;
    }

    public static void setEmpAddedBy(int empAddedBy) {
        Toppings.empAddedBy = empAddedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }
  
}
