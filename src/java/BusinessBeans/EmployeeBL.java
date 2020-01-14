/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import DataBeans.EmployeeDL;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author riley
 */
@Named(value = "employeeBL")
@ViewScoped
@ManagedBean
public class EmployeeBL implements Serializable {

    @ManagedProperty(value = "#{employee}")
    private Employee employee;
    public static EmployeeDL edl = EmployeeDL.GetInstance();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    @PostConstruct
    public void init() {
        employee = new Employee();
    }
    
    public String login() {
        return edl.login(employee);
    }
}
