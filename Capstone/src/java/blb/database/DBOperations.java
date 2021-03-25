/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.database;

import blb.domain.orders.Order;
import blb.domain.products.Product;
import blb.domain.users.Customer;
import blb.domain.users.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Wild, Alexander Peluso
 * Updated: Feb 22, 2021
 */
public class DBOperations {
    
    //Return the arraylist with all the order number, notes.
    //columns 1 and 3 in the report
    public ArrayList getDailyReportProductionList (String date){
        
        ArrayList<Order> dailyReportProductionList = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, product_name, order_notes from orders natural join orderitems natural join products where delivery_date = ?";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Order order = new Order(rs.getInt(1), rs.getString(2), rs.getString(3));
                dailyReportProductionList.add(order);
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dailyReportProductionList;
    }
    
    //provides the order describe to be displayed column two in the report
    public String getOrderString (int orderNum){
        
        String order = "";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "SELECT * FROM bridgelandbakery.orderitems WHERE order_id = ? ";

         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderNum);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                order += rs.getString(2) + " ";
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }
    
    public boolean addCustomer(String firstname, String lastname, String address, char customertype, String postalcode, String community, String email, String password, int phonenumber, char status) {
        boolean result = false;
        String sql = "insert into customer (lastname, firstname, customer_type, street_address, community, postal_code, email, phone_number, customer_status) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, lastname);
            stmnt.setString(2, firstname);
            stmnt.setString(3, Character.toString(customertype));
            stmnt.setString(4, address);
            stmnt.setString(5, community);
            stmnt.setString(6, postalcode);
            stmnt.setString(7, email);
            stmnt.setInt(8, phonenumber);
            stmnt.setString(9, Character.toString(status));
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean addCustomer(Customer customer) {
        boolean result = false;
        String sql = "insert into customer (lastname, firstname, customer_type, street_address, community, postal_code, email, phone_number, customer_status) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, customer.getLastName());
            stmnt.setString(2, customer.getFirstName());
            stmnt.setString(3, Character.toString(customer.getCustomerType()));
            stmnt.setString(4, customer.getAddress());
            stmnt.setString(5, customer.getCommunity());
            stmnt.setString(6, customer.getPostalCode());
            stmnt.setString(7, customer.getEmail());
            stmnt.setInt(8, customer.getPhoneNumber());
            stmnt.setString(9, Character.toString(customer.getStatus()));
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean updateCustomer(int id,String firstname, String lastname, String address, char customertype, String postalcode, String community, String email, String password, int phonenumber, char status) {
        
        boolean result = false;
        String sql = "update customer set (lastname = ?, firstname = ?, customer_type = ?, street_address = ?, community = ?, postal_code = ?, email = ?, phone_number = ?, customer_status = ?) "
                + "where customer_id = ?";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, lastname);
            stmnt.setString(2, firstname);
            stmnt.setString(3, Character.toString(customertype));
            stmnt.setString(4, address);
            stmnt.setString(5, community);
            stmnt.setString(6, postalcode);
            stmnt.setString(7, email);
            stmnt.setInt(8, phonenumber);
            stmnt.setString(9, Character.toString(status));
            
            stmnt.setInt(10, id);
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
//    public boolean updateCustomer(Customer customer) {
//        
//    }

    public boolean addProduct(String name, double price, String category, String description, String status ) {
        boolean result = false;
        
        String sql = "insert into products (product_name, product_price, category, product_description, product_status) "
                + "values (?, ?, ?, ?, ?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, name);
            stmnt.setDouble(2, price);
            stmnt.setString(3, category);
            stmnt.setString(4, description);
            stmnt.setString(5, status);
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean addProduct(Product product) {
        boolean result = false;
        
        String sql = "insert into products (product_name, product_price, category, product_description, product_status) "
                + "values (?, ?, ?, ?, ?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, product.getProductName());
            stmnt.setDouble(2, product.getProductPrice());
            stmnt.setString(3, product.getProductCategory());
            stmnt.setString(4, product.getProductDescription());
            stmnt.setString(5, product.getProductStatus());
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean updateProduct(int id, String name, double price, String category, String description, String status) {
        boolean result = false;
        
        String sql = "update products set (product_name = ?, product_price = ?, category = ?, product_description = ?, product_status = ?) "
                + "where product_id = ?";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, name);
            stmnt.setDouble(2, price);
            stmnt.setString(3, category);
            stmnt.setString(4, description);
            stmnt.setString(5, status);
            
            stmnt.setInt(6, id);
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean updateProduct(Product product) {
        boolean result = false;
        
        String sql = "update products set (product_name = ?, product_price = ?, category = ?, product_description = ?, product_status = ?) "
                + "where product_id = ?";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, product.getProductName());
            stmnt.setDouble(2, product.getProductPrice());
            stmnt.setString(3, product.getProductCategory());
            stmnt.setString(4, product.getProductDescription());
            stmnt.setString(5, product.getProductStatus());
            
            stmnt.setInt(6, product.getProductId());
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean addEmployee( String email, String password, int accesslevel) {
        boolean result = false;
        
        String sql = "insert into employeeauthentication (employee_email, employee_password, employee_access_level) "
                + "values (?, ?, ?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, email);
            stmnt.setString(2, password);
            stmnt.setInt(3, accesslevel);
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean addEmployee(Employee employee) {
        boolean result = false;
        
        String sql = "insert into employeeauthentication (employee_email, employee_password, employee_access_level) "
                + "values (?)";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, employee.getEmail());
            stmnt.setString(2, employee.getPassword());  
            stmnt.setInt(3, employee.getAccessLevel());
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean updateEmployee(int id, String email, String password, int accesslevel) {
        boolean result = false;
        
        String sql = "update employeeauthentication set employee_email=?, employee_password=?, employee_access_level = ? "
                + "where employee_id = ?";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, email);
            stmnt.setString(2, password);
            stmnt.setInt(3, accesslevel);
            stmnt.setInt(4, id);
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        
        String sql = "update employeeauthentication set employee_email=?, employee_password=?, employee_access_level = ? "
                + "where employee_id = ?";
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(sql);
            
            stmnt.setString(1, employee.getEmail());
            stmnt.setString(2, employee.getPassword());  
            stmnt.setInt(3, employee.getAccessLevel());
            stmnt.setInt(4, employee.getEmployeeId());
            
            int rowsaffected = stmnt.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            stmnt.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    
    private String parseDate(String date) throws ParseException {
            Date dateObj = new SimpleDateFormat("EEEE d, MMMM y").parse(date);
            date = new SimpleDateFormat("yyyy-MM-dd").format(dateObj);
        
        return date;
    }
    
    public ArrayList<String> getCustomerOrdersByDate(String date) {
        ArrayList<String> customerOrdersList = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, first_name, last_name, street_address, postal_code, community "
                + "from customer natural join orders where delivery_date = ? order by community";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5);
                customerOrdersList.add(resultstring);
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerOrdersList;
    }
    
    public int deliveryCount(String date) {
        int count = 0;
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select count(customer_id) from customer natural join orders where delivery_date = ?";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    
}