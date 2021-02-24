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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    private User user;
    private Product product;
    private String orderDate;
    private int price;
    private String notes;
    private String status;
    
    public Order() {
        this.user = null;
        this.product = null;
    }
    
    public Order(User user, Product product, String orderDate, int price, String status) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
        this.notes = "";
    }
    
    public Order(User user, Product product, String orderDate, int price, String status, String notes) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
        this.notes = notes;
    }
    
    public Order(User user, Product product, String orderDate, int price) {
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
        this.price = price;
        this.status = "On-going";
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
