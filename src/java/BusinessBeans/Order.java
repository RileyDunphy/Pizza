/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.apache.tools.ant.util.DateUtils;

/**
 *
 * @author riley
 */
@Stateless
public class Order {

    private int orderId;
    private double totalPrice;
    private String orderStatus;
    private Date deliveryDateTime;
    private Date placeDateTime;
    private Customer customer;

    public Order() {

    }

    public Order(int orderId, double totalPrice, String orderStatus, Date deliveryDateTime, Date placeDateTIme, Customer customer) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.deliveryDateTime = deliveryDateTime;
        this.placeDateTime = placeDateTIme;
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(Date deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public Date getPlaceDateTime() {
        return placeDateTime;
    }

    public void setPlaceDateTime(Date placeDateTime) {
        this.placeDateTime = placeDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
  
}
