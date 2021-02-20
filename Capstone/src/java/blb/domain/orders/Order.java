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
    private User user;
    private Product product;
    
    public Order() {
        this.user = null;
        this.product = null;
    }
    
    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
