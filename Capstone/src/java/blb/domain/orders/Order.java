/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.domain.orders;

import blb.domain.products.Product;
import blb.domain.users.User;

/**
 *
 * @author Sebastian Wild
 */
public class Order {

    private int orderNum;
    private User user;
    private String product;
    private String orderDate;
    private String notes;
    private String breadType;
    private double price;
    private char standingOrder;

    public Order() {
        this.user = null;
        this.product = null;
    }

    public Order(int orderNum, String note) {
        if (note == null) {
            this.notes = "-----";
        } else {
            this.notes = note;
        }
        this.orderNum = orderNum;
    }

    public Order(int orderNum, String product, String note) {
        if (note == null) {
            this.notes = "-----";
        } else {
            this.notes = note;
        }
        this.product = product;
        this.orderNum = orderNum;
    }

    public Order(int orderNum, User user, String product, String orderDate, String notes, String breadType, double price, char standingOrder) {
        this.orderNum = orderNum;
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.notes = notes;
        this.breadType = breadType;
        this.price = price;
        this.standingOrder = standingOrder;
    }

    /*public Order(User user, Product product, String orderDate, String type) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.type = type;
        this.notes = "";
    }
    
    public Order(User user, Product product, String orderDate, String type, String notes) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.type = type;
        this.notes = notes;
    }
    
    public Order(User user, Product product, String orderDate) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.notes = "";
    }*/
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getStandingOrder() {
        return standingOrder;
    }

    public void setStandingOrder(char standingOrder) {
        this.standingOrder = standingOrder;
    }

    public String toString() {
        return this.orderNum + " " + this.notes;
    }

}
