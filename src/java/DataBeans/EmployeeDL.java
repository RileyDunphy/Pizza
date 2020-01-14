/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

import BusinessBeans.Employee;
import BusinessBeans.Toppings;
import static DataBeans.SizeDL.conn;
import static DataBeans.SizeDL.gt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author riley
 */
@Singleton
public class EmployeeDL {

    public static EmployeeDL instance;
    public static Connection conn;
    @EJB
    public static GetConnection gt;

    protected EmployeeDL() {
        gt = gt.GetInstance();
        conn = gt.GetConnection();
    }

    public static EmployeeDL GetInstance() {
        if (instance == null) {
            instance = new EmployeeDL();//calls the protected constructor
        }
        return instance;
    }

    public static String login(Employee e) {
        try {

            String sql = "select * from pizzadb.employee where username = '" + e.getUserName() + "' and password = '" + e.getPassword() + "'";
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            if (rs.getString(2) != null) {
                e.setId(rs.getInt(1));
                Toppings.setEmpAddedBy(e.getId());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("employeeId", e.getId());
                return "Administration.jsp?msg=Login successful%empid=" + e.getId();
            } else {
                return "AdminLogin.xhtml?msg=Login failed&faces-redirect=true";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return "AdminLogin.xhtml?msg=Login failed&faces-redirect=true";
        }
    }
}
