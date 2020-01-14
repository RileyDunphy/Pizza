<%-- 
    Document   : ManageOrders
    Created on : 2-Dec-2019, 10:25:57 AM
    Author     : riley
 I used this free bootstrap template for my website: https://themewagon.com/themes/free-bootstrap-4-html5-pizza-website-template-pizza/
--%>

<%@page import="BusinessBeans.OrderBL"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="BusinessBeans.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" style="background-color: black" id="ftco-navbar">
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
        <br>
        <div class="container ftco-animated">
            <%
                if (session.getAttribute("employeeId") == null) {
                    response.sendRedirect("AdminLogin.xhtml?msg=Please log in");
                }
                String message = request.getParameter("msg");
                if (message != null) {
                    out.println("<h1>" + message + "</h1>");
                }
            %>
            <form method="post" id="orderForm" name="hello" action="manageorders_proc">
                <input type = "radio" name="radOrder" id="1" value ="Filled" required="required">
                <label for ="Filled">Filled</label>
                <input type = "radio" name="radOrder" id="2" value ="Pending" required="required">
                <label for ="Pending">Pending</label><br>
                <input type="submit" id="sendForm" value="View Orders" ><hr>
            </form>
            <%
                if (request.getParameter("orderType") != null) {
                    ArrayList<Order> orders = OrderBL.getOrders(request.getParameter("orderType"));
                    if (orders != null) {
                        for (Order o : orders) {
                            out.println("<b><h3>Order number:</b> " + o.getOrderId() + "</b></h3>");
                            out.println("<b>Customer ID:</b> " + o.getCustomer().getCustomerId());
                            out.println("<br><b>Customer Name:</b> " + o.getCustomer().getFirstName() + " " + o.getCustomer().getLastName());
                            out.println("<br><b>Date Placed:</b> " + o.getPlaceDateTime());
                            out.println("<br><b>Price:</b> " + NumberFormat.getCurrencyInstance().format(o.getTotalPrice()) + "<br>");
                            if (o.getOrderStatus().equals("PENDING")) {
                                out.println("<a href=\"manageorders_proc?order=" + o.getOrderId() + "\">Fill Order</a>");
                            }
                            out.println("<hr>");
                        }
                    } else {
                        out.println("<b><h3>No " + request.getParameter("orderType") + " Orders At The Moment</b></h3>");
                    }
                }

            %>
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
