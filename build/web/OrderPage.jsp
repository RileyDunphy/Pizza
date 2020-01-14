<%-- 
    Document   : OrderPage
    Created on : 14-Nov-2019, 11:36:52 AM
    Author     : riley
 I used this free bootstrap template for my website: https://themewagon.com/themes/free-bootstrap-4-html5-pizza-website-template-pizza/
--%>


<%@page import="javax.ejb.EJB"%>
<%@page import="BusinessBeans.ToppingsBL"%>
<%@page import="BusinessBeans.CrustTypeBL"%>
<%@page import="BusinessBeans.SizeBL"%>
<%@page import="BusinessBeans.Size"%>
<%@page import="BusinessBeans.CrustType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="BusinessBeans.Toppings"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BusinessBeans.Pizza"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order a Pizza</title>
        <%
            String message = request.getParameter("msg");
            if (message != null) {
                out.println("<script>alert('" + message + "');</script>");
            }
        %>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do" rel="stylesheet">
        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="OrderPage.jsp"><span class="flaticon-pizza-1 mr-1"></span>Pizza<br><small>Done Right</small></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>
                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active"><a href="AdminLogin.xhtml" class="nav-link">Employee Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container text-center">
            <h1>Fill Out Your Pizza Order</h1>
            <form method="post" id="order_form" action="order_proc">
                <fieldset>
                    <p>
                    <h4>Pizza Size </h4>
                    <%
                        ArrayList<Size> sizes = SizeBL.getAllAvailableSizes();
                        if (sizes != null) {
                            for (Size s : sizes) {
                                out.println("<input type = \"radio\" name=\"radSize\" id=\"" + s.getSizeId() + "\" value =\"" + s.getName() + "\" required=\"required\">");
                                out.println("<label for =\"" + s.getName() + "\">" + s.getName() + "</label>");
                            }
                        }
                    %>
                    </p>
                    <p>
                    <h4>Pizza Crust </h4> 
                    <%
                        ArrayList<CrustType> crusttypes = CrustTypeBL.getAllAvailableCrustTypes();
                        if (crusttypes != null) {
                            for (CrustType c : crusttypes) {
                                out.println("<input type = \"radio\" name=\"radCrust\" id=\"" + c.getCrustTypeId() + "\" value =\"" + c.getName() + "\" required=\"required\">");
                                out.println("<label for =\"" + c.getName() + "\">" + c.getName() + "</label>");
                            }
                        }
                    %>

                    </p>
                    <p>
                    <h4>Toppings </h4>
                    <%
                        ArrayList<Toppings> toppings = ToppingsBL.getAllAvailableToppings();
                        if (toppings != null) {
                            for (Toppings t : toppings) {
                                out.println("<input type = \"checkbox\" name=\"" + t.getName() + "\" value =\"" + t.getToppingId() + "\"/>");
                                out.println("<label for =\"" + t.getName() + "\">" + t.getName() + " $" + t.getPrice() + "</label>");
                            }
                        }
                    %>
                    </p>
                    <p>
                    <h4>Quantity</h4>
                    <select name="quantity" id="quantity"  required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    </p>
                </fieldset> 
                <input type="submit" name="button" id="button" value="Add to Cart">
            </form><hr>
            <div><h1><a href="Checkout.jsp">Proceed to Checkout</a></h1></div>
            <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


            <script src="js/jquery.min.js"></script>
            <script src="js/jquery-migrate-3.0.1.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.easing.1.3.js"></script>
            <script src="js/jquery.waypoints.min.js"></script>
            <script src="js/jquery.stellar.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/jquery.magnific-popup.min.js"></script>
            <script src="js/aos.js"></script>
            <script src="js/jquery.animateNumber.min.js"></script>
            <script src="js/bootstrap-datepicker.js"></script>
            <script src="js/jquery.timepicker.min.js"></script>
            <script src="js/scrollax.min.js"></script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
            <script src="js/google-map.js"></script>
            <script src="js/main.js"></script>
        </div>
    </body>
</html>
