/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BusinessBeans.CrustType;
import BusinessBeans.CrustTypeBL;
import BusinessBeans.Pizza;
import BusinessBeans.PizzaBL;
import BusinessBeans.Size;
import BusinessBeans.SizeBL;
import BusinessBeans.Toppings;
import BusinessBeans.ToppingsBL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author riley
 */
public class order_proc extends HttpServlet {

    @EJB
    Pizza p;

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
            out.println("<title>Servlet order_proc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet order_proc at " + request.getContextPath() + "</h1>");
            int count = 0;
            ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
            ArrayList<Toppings> allToppings = ToppingsBL.getAllAvailableToppings();
            while (count < Integer.parseInt(request.getParameter("quantity"))) {
                p = new Pizza(CrustTypeBL.FetchCrustTypeByName(request.getParameter("radCrust")), false, SizeBL.FetchSizeByName(request.getParameter("radSize")));
                PizzaBL.calculatePrice(p);
                pizzas.add(p);
                ArrayList<Toppings> pizzaToppings = new ArrayList<Toppings>();
                //Right here create a function for whatever toppings that were checked, map to pizza by insert into the pizzas toppings map
                for (Toppings t : allToppings) {
                    if (request.getParameter(t.getName()) != null) {
                        pizzaToppings.add(t);
                    }
                }
                pizzas.get(count).setToppings(pizzaToppings);
                count++;
            }
            HttpSession session = request.getSession();

            if (session.getAttribute("pizzas") != null) {
                ArrayList<Pizza> sessionPizzas = (ArrayList<Pizza>) session.getAttribute("pizzas");
                sessionPizzas.addAll(pizzas);
                session.setAttribute("pizzas", sessionPizzas);
            } else {
                session.setAttribute("pizzas", pizzas);
            }
            response.sendRedirect("OrderPage.jsp?msg=Pizza has been added to cart");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            response.sendRedirect("OrderPage.jsp?msg=An error occured");
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
