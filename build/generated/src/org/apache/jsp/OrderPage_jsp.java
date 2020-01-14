package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.ejb.EJB;
import BusinessBeans.ToppingsBL;
import BusinessBeans.CrustTypeBL;
import BusinessBeans.SizeBL;
import BusinessBeans.Size;
import BusinessBeans.CrustType;
import java.util.Map.Entry;
import java.util.HashMap;
import BusinessBeans.Toppings;
import java.util.ArrayList;
import BusinessBeans.Pizza;
import java.sql.DriverManager;

public final class OrderPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Order a Pizza</title>\n");
      out.write("        ");

            String message = request.getParameter("msg");
            if (message != null) {
                out.println("<script>alert('" + message + "');</script>");
            }
        
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Josefin+Sans\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Nothing+You+Could+Do\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/open-iconic-bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/animate.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/owl.carousel.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/owl.theme.default.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/magnific-popup.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/aos.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/ionicons.min.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap-datepicker.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery.timepicker.css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/flaticon.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/icomoon.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light\" id=\"ftco-navbar\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <a class=\"navbar-brand\" href=\"OrderPage.jsp\"><span class=\"flaticon-pizza-1 mr-1\"></span>Pizza<br><small>Done Right</small></a>\n");
      out.write("                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#ftco-nav\" aria-controls=\"ftco-nav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("                    <span class=\"oi oi-menu\"></span> Menu\n");
      out.write("                </button>\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"ftco-nav\">\n");
      out.write("                    <ul class=\"navbar-nav ml-auto\">\n");
      out.write("                        <li class=\"nav-item active\"><a href=\"AdminLogin.xhtml\" class=\"nav-link\">Employee Login</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h1>Fill Out Your Pizza Order</h1>\n");
      out.write("            <form method=\"post\" id=\"order_form\" action=\"order_proc\">\n");
      out.write("                <fieldset>\n");
      out.write("                    <p>\n");
      out.write("                        <h4>Pizza Size </h4>\n");
      out.write("                        ");

                            ArrayList<Size> sizes = SizeBL.getAllAvailableSizes();
                            if (sizes != null) {
                                for (Size s : sizes) {
                                    out.println("<input type = \"radio\" name=\"radSize\" id=\"" + s.getSizeId() + "\" value =\"" + s.getName() + "\" required=\"required\">");
                                    out.println("<label for =\"" + s.getName() + "\">" + s.getName() + "</label>");
                                }
                            }
                        
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <h4>Pizza Crust </h4> \n");
      out.write("                        ");

                            ArrayList<CrustType> crusttypes = CrustTypeBL.getAllAvailableCrustTypes();
                            if (crusttypes != null) {
                                for (CrustType c : crusttypes) {
                                    out.println("<input type = \"radio\" name=\"radCrust\" id=\"" + c.getCrustTypeId() + "\" value =\"" + c.getName() + "\" required=\"required\">");
                                    out.println("<label for =\"" + c.getName() + "\">" + c.getName() + "</label>");
                                }
                            }
                        
      out.write("\n");
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <h4>Toppings65ty7u8t7 </h4>\n");
      out.write("                        ");

                            ArrayList<Toppings> toppings = ToppingsBL.getAllAvailableToppings();
                            if (toppings != null) {
                                for (Toppings t : toppings) {
                                    out.println("<input type = \"checkbox\" name=\"" + t.getName() + "\" value =\"" + t.getToppingId() + "\"/>");
                                    out.println("<label for =\"" + t.getName() + "\">" + t.getName() + " $" + t.getPrice() + "</label>");
                                }
                            }
                        
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                        <label for=\"quantity\">Quantity: </label>\n");
      out.write("                        <select name=\"quantity\" id=\"quantity\"  required>\n");
      out.write("                            <option value=\"1\">1</option>\n");
      out.write("                            <option value=\"2\">2</option>\n");
      out.write("                            <option value=\"3\">3</option>\n");
      out.write("                            <option value=\"4\">4</option>\n");
      out.write("                            <option value=\"5\">5</option>\n");
      out.write("                        </select>\n");
      out.write("                    </p>\n");
      out.write("                </fieldset> \n");
      out.write("                <input type=\"submit\" name=\"button\" id=\"button\" value=\"Add to Cart\">\n");
      out.write("            </form>\n");
      out.write("            <div><h1><a href=\"Checkout.jsp\">Proceed to Checkout</a></h1></div>\n");
      out.write("            <div id=\"ftco-loader\" class=\"show fullscreen\"><svg class=\"circular\" width=\"48px\" height=\"48px\"><circle class=\"path-bg\" cx=\"24\" cy=\"24\" r=\"22\" fill=\"none\" stroke-width=\"4\" stroke=\"#eeeeee\"/><circle class=\"path\" cx=\"24\" cy=\"24\" r=\"22\" fill=\"none\" stroke-width=\"4\" stroke-miterlimit=\"10\" stroke=\"#F96D00\"/></svg></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <script src=\"js/jquery.min.js\"></script>\n");
      out.write("            <script src=\"js/jquery-migrate-3.0.1.min.js\"></script>\n");
      out.write("            <script src=\"js/popper.min.js\"></script>\n");
      out.write("            <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("            <script src=\"js/jquery.easing.1.3.js\"></script>\n");
      out.write("            <script src=\"js/jquery.waypoints.min.js\"></script>\n");
      out.write("            <script src=\"js/jquery.stellar.min.js\"></script>\n");
      out.write("            <script src=\"js/owl.carousel.min.js\"></script>\n");
      out.write("            <script src=\"js/jquery.magnific-popup.min.js\"></script>\n");
      out.write("            <script src=\"js/aos.js\"></script>\n");
      out.write("            <script src=\"js/jquery.animateNumber.min.js\"></script>\n");
      out.write("            <script src=\"js/bootstrap-datepicker.js\"></script>\n");
      out.write("            <script src=\"js/jquery.timepicker.min.js\"></script>\n");
      out.write("            <script src=\"js/scrollax.min.js\"></script>\n");
      out.write("            <script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false\"></script>\n");
      out.write("            <script src=\"js/google-map.js\"></script>\n");
      out.write("            <script src=\"js/main.js\"></script>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
