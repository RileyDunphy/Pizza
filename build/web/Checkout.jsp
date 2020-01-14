<%-- 
    Document   : Checkout
    Created on : 19-Nov-2019, 3:48:09 PM
    Author     : riley
 I used this free bootstrap template for my website: https://themewagon.com/themes/free-bootstrap-4-html5-pizza-website-template-pizza/
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="BusinessBeans.Toppings"%>
<%@page import="BusinessBeans.Pizza"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
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

        <div class="container">
            <h1 class="text-center">Confirm your Order Details</h1>
            <div class="row">
                <div class="col-6 text-center">
                    <%
                        double grandTotal = 0;
                        int count = 1;
                        if (session.getAttribute("pizzas") == null) {
                            response.sendRedirect("OrderPage.jsp?msg=You never created a pizza");
                        } else {
                            ArrayList<Pizza> pizzas = (ArrayList<Pizza>) session.getAttribute("pizzas");
                            for (Pizza p : pizzas) {
                                double total = p.getPrice();
                                out.println("<b><h3>Pizza Number " + count + "</b></h3> <b>Size:</b> " + p.getSize().getName() + "<BR><b>Crust:</b> " + p.getCrustType().getName() + "<BR><b>Base Price:</b> " + NumberFormat.getCurrencyInstance().format(p.getPrice()) + "<BR>");
                                ArrayList<Toppings> toppings = p.getToppings();
                                if (!toppings.isEmpty()) {
                                    out.print("<fieldset><h6>Toppings</h6>");
                                    for (Toppings t : toppings) {
                                        out.print(t.getName() + ": " + NumberFormat.getCurrencyInstance().format(t.getPrice()) + "<BR>");
                                        total += t.getPrice();
                                    }
                                    out.print("</fieldset>");
                                }
                                out.print("<b><h5>Total Price: " + NumberFormat.getCurrencyInstance().format(total) + "</h5></b><hr>");
                                grandTotal += total;
                                count++;
                            }
                            out.println("<b><h5>Order Subtotal: " + NumberFormat.getCurrencyInstance().format(grandTotal) + "</b></h5>");
                            out.println("<b><h5>Tax: " + NumberFormat.getCurrencyInstance().format(grandTotal * 0.15) + "</b></h5>");
                            out.println("<b><h4>Grand Total: " + NumberFormat.getCurrencyInstance().format(grandTotal * 1.15) + "</b></h4>");
                        }
                    %>
                </div>
                <div class="col-6 text-center">
                    <form method="post" id="order_form" action="checkout_proc">
                        <p>
                            <label for="orderType">Order Type<br> </label><br>
                            <select name="orderType" id="orderType"  required>
                                <option value="delivery">Delivery</option>
                                <option value="pickup">Pick-up</option>
                            </select>
                        </p>
                        <p>
                            <label for="firstName">First Name<br> </label><br>
                            <input name="firstName" maxlength="45" id="firstName" required/>
                        </p>
                        <p>
                            <label for="lastName">Last Name<br> </label><br>
                            <input name="lastName" maxlength="45" id="lastName" required/>
                        </p>
                        <p>
                            <label for="phoneNumber">Phone Number<br> </label><br>
                            <input type="tel" name="phoneNumber" maxlength="45" id="phoneNumber" required pattern="\d{3}[\-]\d{3}[\-]\d{4}" title="Phone Number Format 123-456-7890"/>
                        </p>
                        <p>
                            <label for="email">Email </label><br>
                            <input type="email" maxlength="45" name="email" id="email"/>
                        </p>
                        <p>
                            <label for="houseNumber">House Number<br> </label><br>
                            <input type="number" maxlength="11" name="houseNumber" id="housesNumber" required/>
                        </p>
                        <p>
                            <label for="street">Street<br> </label><br>
                            <input name="street" maxlength="45" id="street" required/>
                        </p>
                        <p>
                            <label for="province">Province<br> </label><br>
                            <select name="province" id="province"  required>
                                <option value="NB">NB</option>
                                <option value="NS">NS</option>
                                <option value="PE">PE</option>
                                <option value="NL">NL</option>
                                <option value="QC">QC</option>
                                <option value="ON">ON</option>
                                <option value="MB">MB</option>
                                <option value="SK">SK</option>
                                <option value="AB">AB</option>
                                <option value="BC">BC</option>
                                <option value="YT">YT</option>
                                <option value="NT">NT</option>
                                <option value="NU">NU</option>
                            </select>
                        </p>
                        <p>
                            <label for="postalCode">Postal Code<br> </label><br>
                            <input name="postalCode" maxlength="7" id="postalCode" required pattern="[A-Za-z][0-9][A-Za-z] [0-9][A-Za-z][0-9]" title="Postal Code"/>
                        </p>
                        <p>
                            <label for="paymentMethod">Payment Method<br> </label><br>
                            <select name="paymentMethod" id="paymentMethod">
                                <option value="cod">Cash on Delivery</option>
                                <option value="online">Online</option>
                            </select>
                        </p>
                        <input type="hidden" id="totalPrice" name="totalPrice" value="<%=grandTotal * 1.15%>">
                        <input type="submit" name="button" id="button" value="Place Order">
                    </form>
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
            </div>
        </div>
    </body>
</html>
