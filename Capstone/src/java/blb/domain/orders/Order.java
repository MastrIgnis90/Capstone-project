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
    private Product product;
    private String orderDate;
    private String notes;
    private String type;
    
    public Order() {
        this.user = null;
        this.product = null;
    }
    
    public Order(int orderNum, String note) {
        this.orderNum = orderNum;
        this.notes = note;
    }
    
    public Order(User user, Product product, String orderDate, String type) {
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
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public String getType() {
        return type;
    }

    public String getOrderDate() {
        return orderDate;
    }
    
    public int getOrderNum() {
        return orderNum;
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
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
