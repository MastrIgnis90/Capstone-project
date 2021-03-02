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
    private String type;
    
    public Order() {
        this.user = null;
        this.product = null;
    }
    
    public Order(int orderNum, String note) {
        if(note==null){
            this.notes = "-----";
        } else {
            this.notes = note;
        }
        this.orderNum = orderNum;
    }
    
    public Order(int orderNum, String product, String note) {
        if(note==null){
            this.notes = "-----";
        } else {
            this.notes = note;
        }
        this.product = product;
        this.orderNum = orderNum;
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
    
    public String toString(){
        return this.orderNum + " " + this.notes;
    }
    
}
