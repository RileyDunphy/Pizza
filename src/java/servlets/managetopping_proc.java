/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BusinessBeans.Toppings;
import BusinessBeans.ToppingsBL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author riley
 */
public class managetopping_proc extends HttpServlet {

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
            out.println("<title>Servlet managetopping_proc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managetopping_proc at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            if (request.getParameter("topping") != null) {
                Toppings t = ToppingsBL.getToppingById(Integer.parseInt(request.getParameter("topping")));
                if (ToppingsBL.deleteTopping(t) > 0) {
                    response.sendRedirect("ManageToppings.jsp?msg=Topping has been removed");
                } else {
                    response.sendRedirect("ManageToppings.jsp?msg=Error removing topping");
                }
            } else {
                try {
                    Toppings t = new Toppings();
                    t.setName(request.getParameter("name"));
                    t.setPrice(Double.parseDouble(request.getParameter("price")));
                    if (ToppingsBL.addTopping(t) > 0) {
                        response.sendRedirect("ManageToppings.jsp?msg=Topping has been added");
                    } else {
                        response.sendRedirect("ManageToppings.jsp?msg=Error adding topping");
                    }
                } catch (Exception ex) {
                    response.sendRedirect("ManageToppings.jsp?msg=Error adding topping");
                }
            }

        } catch (Exception ex) {
            response.sendRedirect("ManageToppings.jsp?msg=Please specify a topping");
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
