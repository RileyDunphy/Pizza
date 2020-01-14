/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BusinessBeans.Customer;
import BusinessBeans.CustomerBL;
import BusinessBeans.Order;
import BusinessBeans.OrderBL;
import BusinessBeans.Pizza;
import BusinessBeans.PizzaBL;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author riley
 */
public class checkout_proc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkout_proc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkout_proc at " + request.getContextPath() + "</h1>");
            
            //Create customer object and add to datebase
            Customer c = new Customer();
            c.setFirstName(request.getParameter("firstName"));
            c.setLastName(request.getParameter("lastName"));
            c.setPhoneNumber(request.getParameter("phoneNumber"));
            c.setEmail(request.getParameter("email"));
            c.setHouseNumber(Integer.parseInt(request.getParameter("houseNumber")));
            c.setStreet(request.getParameter("street"));
            c.setProvince(request.getParameter("province"));
            c.setPostalCode(request.getParameter("postalCode"));
            CustomerBL.addCustomer(c);
            
            //Create order object and add to database
            Order o = new Order();
            o.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
            o.setCustomer(c);
            OrderBL.addOrder(o);
            
            //Get the session so we can get the pizzas and add them to database
            HttpSession session = request.getSession();
            for (Pizza p : (ArrayList<Pizza>) session.getAttribute("pizzas")) {
                p.setOrder(o);
                PizzaBL.addPizza(p);
            }
            session.setAttribute("pizzas", null);
            
            //Add ordertype to session and delivery time to session
            session.setAttribute("orderType", request.getParameter("orderType"));
            session.setAttribute("finishTime", LocalDateTime.now().plusMinutes(30));
            
            //If they're paying online, send them to pay pal, if not then send to delivery page
            if (request.getParameter("paymentMethod").equals("online")) {
                response.sendRedirect("http://paypal.com");
            } else {
                response.sendRedirect("DeliveryTime.jsp?msg=Pizza is currently being made");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            response.sendRedirect("Checkout.jsp?msg=An error occured with your order");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
