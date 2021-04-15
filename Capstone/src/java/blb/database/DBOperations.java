/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.database;

import blb.domain.orders.Order;
import blb.domain.products.Product;
import blb.domain.products.ReportDay;
import blb.domain.users.Customer;
import blb.domain.users.Employee;
import java.sql.CallableStatement;
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
 * This class contains all of the methods used by the application to communicate with the bridgeland bread database
 * @author Sebastian Wild, Alexander Peluso, Matthew Brydges
 * Updated: March 27, 2021
 */
public class DBOperations {
    
    //Return the arraylist with all the order number, notes.
    //columns 1 and 3 in the report
    public ArrayList getDailyReportProductionList (String date){
        
        ArrayList<Order> dailyReportProductionList = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, order_notes from orders where delivery_date = ?";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Order order = new Order(rs.getInt(1), "---", rs.getString(2));
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
    
    public ArrayList<Product> getBread() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select product_name, product_price from products where category = 'Bread';";
            
        ConnectionPool cp = ConnectionPool.getInstance();
        try {           
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            int i = 1;
            while(rs.next()){
                list.add(new Product(i++, rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**
     * Provides the order information to be displayed in column two in the report
     * @param orderNum
     * @return 
     */
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
    
    /**
     * Adds a new customer entry to the database
     * @param firstname 
     * @param lastname
     * @param address
     * @param customertype
     * @param postalcode
     * @param community
     * @param email
     * @param password
     * @param phonenumber
     * @param status
     * @return true if the action was successful
     */
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
    
    /**
     * [POJO Version] Adds a new customer entry to the database
     * @param customer represents the new customer with state to be added to the database
     * @return true if the operation was successful
     */
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
    
    /**
     * Updates a customer entry in the database with the information provided
     * @param id the CURRENT id of the customer (cannot be changed with this method)
     * @param firstname the new firstname
     * @param lastname the new lastname
     * @param address the new address
     * @param customertype the new customertype (S or C)
     * @param postalcode the new postal code
     * @param community the new community
     * @param email the new email
     * @param password the new password
     * @param phonenumber the new phone number
     * @param status the new status (A or I)
     * @return true if the operation was successful
     */
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
    
    /**
     * [POJO Version] updates a customer entry in the database
     * @param customer represents the customer with new state to be updated
     * @return true if the operation was successful
     */
    public boolean updateCustomer(Customer customer) {
        boolean result = false;
        String sql = "update customer set (lastname = ?, firstname = ?, customer_type = ?, street_address = ?, community = ?, postal_code = ?, email = ?, phone_number = ?, customer_status = ?) "
                + "where customer_id = ?";
        
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
            
            stmnt.setInt(10, customer.getCustomerId());
            
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
    
    /**
     * Adds a new product entry to the database
     * @param name the product name
     * @param price the product price
     * @param category the product category
     * @param description the product description
     * @param status the product status
     * @return true if the operation was successful
     */
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
    
    /**
     * [POJO Version] adds a new product entry to the database
     * @param product represents the new product to be added, contains its data
     * @return true if the operation was successful
     */
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
    
    /**
     * Updates a product in the database with the information provided
     * @param id the CURRENT id of the product (can't be updated with this method)
     * @param name the new name for the product
     * @param price the new price of the product
     * @param category the new category of the product
     * @param description the new description og the product
     * @param status the new status of the product
     * @return true if the action was successful
     */
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
    
    /**
     * [POJO Version] updates the properties of a product in the database with the information provided
     * @param product represents the product with the new state to be updated
     * @return true if the action was successful
     */
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
    
    /**
     * Adds a new employee to the database
     * @param email the new employee's email for authentication
     * @param password the new employee's password for authentication
     * @param accesslevel determines the type of employee, i.e 0 for manager/regular employee access, 1 for admin super user
     * @return true if the action was successful
     */
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
    /**
     * [POJO Version] adds a new employee entry to the database
     * @param employee represents the new employee including all needed information
     * @return true if the action was successful
     */
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
    
    /**
     * Updates an employee entry in the database with the information provided
     * @param id the CURRENT id of the employee (can't be updated with this method)
     * @param email the new email for the employee
     * @param password the new password for the employee
     * @param accesslevel the new access level for the employee
     * @return true if the action was successful
     */
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
    
    /**
     * [POJO Version] Updates an employee entry in the database with the information provided
     * @param employee represents the employee with updated information
     * @return true if the action was successful
     */
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
    
    /**
     * Retrieves the customer information of all customers with an order due on the given date
     * @param date the date 
     * @return an arraylist of comma separated strings each representing a customer with an order on the given date.
     * The format is order id number, first name, last name, address, postal code, community name
     */
    public ArrayList<String> getCustomerWithOrdersByDate(String date) {
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
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5);
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
    
    /**
     * Returns the number of different deliveries that occur on a given date
     * 
     * @param date the date to check delivery count for
     * @return the number of deliveries for the provided date
     */
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
    
    /**
     * Authenticates the user by checking their credentials against the database, and returning their level of access.
     * @param username
     * @param password
     * @return -1 if the credentials are invalid. 0 if the credentials correspond to a regular user. 1 if the credentials correspond to an employee level user.
     * and 2 if the credentials correspond to an admin super user.
     */
    public int authUser(String username, String password) {
        int result = -1;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql1 = "select count(customer_id) from customer where customer_email = ? AND customer_password = ?";
        String sql2 = "select employee_access_level from employeeauthentication where employee_email = ? AND employee_password = ?";
        try {
            Connection conn = cp.getConnection();
            PreparedStatement st1 = conn.prepareStatement(sql1);
            PreparedStatement st2 = conn.prepareStatement(sql2);
            
            st1.setString(1, username);
            st1.setString(2, password);
            
            st2.setString(1, username);
            st2.setString(2, password);
            
            ResultSet rs1 = st1.executeQuery();
            while(rs1.next()){
                if (rs1.getInt(1) > 0)
                    result = 0;
            }
            rs1.close();
            st1.close();
            
            ResultSet rs2 = st2.executeQuery();
            while(rs2.next()) {
                if (rs2.getInt(1) == 0) {
                    result = 1;
                } else if (rs2.getInt(1) == 1) {
                    result = 2;
                }
            }
            rs2.close();
            st2.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    /**
     * Retrieves the customer information of all customers with an order due within the given date range
     * @param startdate the beginning of the date range 
     * @param enddate the upper limit of the date range
     * @return an arraylist of comma separated strings each representing a customer with an order on the given date.
     * The format is order id number, first name, last name, address, postal code, community name
     */
    public ArrayList<String> getCustomerWithOrdersByDateRange(String startdate, String enddate) {
        ArrayList<String> customerOrdersList = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, first_name, last_name, street_address, postal_code, community " +
                    "from customer natural join orders " +
                    "where delivery_date between cast(? AS DATE) and cast(? AS DATE) " +
                    "order by community";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(startdate));
            st.setString(2, parseDate(enddate));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5);
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
    
    /**
     * Retrieves the customer information of all customers with an order due within the given month
     * @param month the month number (1-12) by the gregorian calendar
     * @return an arraylist of comma separated strings each representing a customer with an order on the given date.
     * The format is order id number, first name, last name, address, postal code, community name
     */
    public ArrayList<String> getCustomerWithOrdersByMonth(int month) {
        ArrayList<String> customerOrdersList = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, first_name, last_name, street_address, postal_code, community, MONTH(delivery_date) " +
                "from customer natural join orders " +
                "where MONTH(delivery_date) = ? " +
                "order by community";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5);
                customerOrdersList.add(resultstring);
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customerOrdersList;
    }
    
    /**
     * Retrieves a list of products due to be produced for orders on the given date
     * @param date the date
     * @return an arraylist of comma seperated strings each representing a product and its production details that are due for an order on the given date
     * The format is: the order id, the name of the product, the order notes
     */
    public ArrayList<String> getProductionForDate(String date) {
        ArrayList<String> productionlist = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, product_name, order_notes " +
                "from orders natural join orderitems " +
                "where delivery_date = ?";
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2);
                productionlist.add(resultstring);
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
        
        return productionlist;
    }
    
    /**
     * Retrieves a list of products due to be produced for orders within the given date range
     * @param startdate the beginning of the date range
     * @param enddate the upper limit of the date range
     * @return an arraylist of comma seperated strings each representing a product and its production details that are due for an order on the given date
     * The format is: the order id, the name of the product, the order notes
     */
    public ArrayList<String> getProductionForDateRange(String startdate, String enddate) {
        ArrayList<String> productionlist = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, product_name, order_notes " +
                "from orders natural join orderitems  " +
                "where delivery_date between cast(? AS DATE) and cast(? AS DATE);";
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(startdate));
            st.setString(2, parseDate(startdate));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2);
                productionlist.add(resultstring);
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
        
        return productionlist;
    }
    
    /**
     * Retrieves a list of products due to be produced for orders within the given month
     * @param month the month number (1-12) by the gregorian calendar
     * @return an arraylist of comma seperated strings each representing a product and its production details that are due for an order on the given date
     * The format is: the order id, the name of the product, the order notes
     */
    public ArrayList<String> getProductionForMonth(int month) {
        ArrayList<String> productionlist = new ArrayList <> ();
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "select order_id, product_name, order_notes" +
                "from orders natural join orderitems " +
                "where MONTH(delivery_date) = ?";
        
        try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String resultstring = rs.getInt(1) + "," + rs.getString(1) + "," + rs.getString(2);
                productionlist.add(resultstring);
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return productionlist;
    }
    
    /*
     * PROCEDURE CALLERS 
     */
    
    /**
     * Calls the stored procedure for creating a commercial client order with up to four products. 
     * This creates orders for new commercial clients, and for commercial clients without standing orders value
	set to true, there will be another procedure taking care of that.
	
	The procedure is capable of taking in any combination of the 4 products, doesn't matter the combination, 
	the not used values must be set to NULL for the product name and 0 for the quantity for the procedure
	to work correctly.
        * 
     * @param customer_num
     * @param standing_order 
     * @param order_notes
     * @param product_name1
     * @param product_quantity1
     * @param product_name2
     * @param product_quantity2
     * @param product_name3
     * @param product_quantity3
     * @param product_name4
     * @param product_quantity4
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean multiOrderCreationCommClient(int customer_num, char standing_order, String order_notes, String product_name1, int product_quantity1, String product_name2, int product_quantity2, String product_name3, int product_quantity3, String product_name4, int product_quantity4, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_multi_order_creation_commercial_client(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, Character.toString(standing_order));
            cs.setString(3, order_notes);
            cs.setString(4, product_name1);
            cs.setInt(5, product_quantity1);
            cs.setString(6, product_name2);
            cs.setInt(7, product_quantity2);
            cs.setString(8, product_name3);
            cs.setInt(9, product_quantity3);
            cs.setString(10, product_name4);
            cs.setInt(11, product_quantity4);
            cs.setString(12, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Calls the stored procedure which creates the orders for any subscription that is
	1 loaf every two week, for a price of $12.50 a month
        * It takes in five values, the customer id, the standing_order value, which in theory should always
	be a N, for no, and last the notes section. The last two values are for the order items procedure
	they would be the product name, and the products if any.
        * The procedure does some minor error handling as it will check if a customer number is valid.
     * @param customer_num
     * @param standing_order
     * @param order_notes
     * @param product_name
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean multiOrderCreationFortnightly(int customer_num, char standing_order, String order_notes, String product_name, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_multi_order_creation_every_two_weeks(?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, Character.toString(standing_order));
            cs.setString(3, order_notes);
            cs.setString(4, product_name);
            cs.setString(5, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Calls the stored procedure which creates a weekly one loaf subscription
     * It takes in five values, the customer id, the standing_order value, which in theory should always
	be a N, for no, and last the notes section. The last two values are for the order items procedure
	they would be the product name, and the products if any.

	The procedure does some minor error handling as it will check if a customer number is valid.
     * @param customer_num
     * @param standing_order
     * @param order_notes
     * @param product_name
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean multiOrderCreationWeekly(int customer_num, char standing_order, String order_notes, String product_name, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_multi_order_creation_every_week(?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, Character.toString(standing_order));
            cs.setString(3, order_notes);
            cs.setString(4, product_name);
            cs.setString(5, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Call the stored procedure for the creation of a single one loaf order

	It takes in five values, the customer id, the standing_order value, which in theory should always
	be a N, for no, and last the notes section. The last two values are for the order items procedure
	they would be the product name, and the products if any.

	The procedure does some minor error handling as it will check if a customer number is valid.
     * @param customer_num
     * @param order_notes
     * @param product_name
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean createSingleOrderOneLoaf(int customer_num, String order_notes, String product_name, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_single_order_creation_one_loaf(?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, order_notes);
            cs.setString(3, product_name);
            cs.setString(4, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Calls the stored procedure for the creation of a single two loaf order.
     * The procedure does some minor error handling as it will check if a customer number is valid, and will
     * additionally check if the two products provided are the same or not, creating the appropriate number of order items
     * @param customer_num
     * @param order_notes
     * @param product_name1
     * @param product_name2
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean createSingleOrderTwoLoaf(int customer_num, String order_notes, String product_name1, String product_name2, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_single_order_creation_two_loaves(?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, order_notes);
            cs.setString(3, product_name1);
            cs.setString(4, product_name2);
            cs.setString(5, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Calls the stored procedure for the creation of a single three loaf order.
     * 
     * The procedure does some minor error handling as it will check if a customer number is valid, and will
     * additionally check if the three products provided are the same or not, creating the appropriate number of order items
     * 
     * @param customer_num
     * @param order_notes
     * @param product_name1
     * @param product_name2
     * @param product_name3
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean createSingleOrderThreeLoaf(int customer_num, String order_notes, String product_name1, String product_name2, String product_name3, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_single_order_creation_one_loaf(?, ?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, order_notes);
            cs.setString(3, product_name1);
            cs.setString(4, product_name2);
            cs.setString(5, product_name3);
            cs.setString(6, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Calls the stored procedure for the creation of a weekly two loaf subscription.
     * 
     * The procedure does some minor error handling as it will check if a customer number is valid. Additionally,
     * the procedure will check if the two products are the same or not, and will handle the creation of entries in the orderitems
     * table accordingly.
     * 
     * @param customer_num
     * @param standing_order
     * @param order_notes
     * @param product_name1
     * @param product_name2
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean multiOrderCreationTwoLoavesWeekly(int customer_num, char standing_order, String order_notes, String product_name1, String product_name2, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_multi_order_creation_two_loaves_every_week(?, ?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, Character.toString(standing_order));
            cs.setString(3, order_notes);
            cs.setString(4, product_name1);
            cs.setString(5, product_name2);
            cs.setString(6, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * This procedure is for the weekly three loaf subscription

	It takes in five values, the customer id, the standing_order value, which in theory should always
	be a N, for no, and last the notes section. The last two values are for the order items procedure
	they would be the product name, and the products if any.

	The procedure does some minor error handling as it will check if a customer number is valid.
     * @param customer_num
     * @param standing_order
     * @param order_notes
     * @param product_name1
     * @param product_name2
     * @param product_name3
     * @param product_notes
     * @return true if the operation was successful
     */
    public boolean multiOrderCreationThreeLoavesWeekly(int customer_num, char standing_order, String order_notes, String product_name1, String product_name2, String product_name3, String product_notes) {
        boolean result = false;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        String sql = "{call P_multi_order_creation_every_week(?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            Connection conn = cp.getConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, customer_num);
            cs.setString(2, Character.toString(standing_order));
            cs.setString(3, order_notes);
            cs.setString(4, product_name1);
            cs.setString(5, product_name2);
            cs.setString(6, product_name3);
            cs.setString(7, product_notes);
            int rowsaffected = cs.executeUpdate();
            
            if (rowsaffected > 0)
                result = true;
            
            cs.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}