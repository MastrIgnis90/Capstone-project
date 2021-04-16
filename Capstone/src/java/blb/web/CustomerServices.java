/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
import blb.domain.orders.Order;
import blb.domain.users.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 804850
 */
@WebServlet(name = "CustomerServices", urlPatterns = {"/customerServices"})
public class CustomerServices extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBOperations dbops = new DBOperations();
        
        String action = request.getParameter("action");
        String customerIdParam = request.getParameter("customerId");
        
        String sortCustomerBy = request.getParameter("sortCustomerBy");
        String orderOfSortCustomer = request.getParameter("orderOfSortCustomer");
        
        String searchCustomerFirstName = request.getParameter("searchCustomerFirstName");
        
        String searchCustomerLastName = request.getParameter("searchCustomerLastName");
        
        String newCustomerFirstName = request.getParameter("newCustomerFirstName");
        String newCustomerLastName = request.getParameter("newCustomerLastName");
        String newCustomerAddress = request.getParameter("newCustomerAddress");
        String newCustomerPostalCode = request.getParameter("newCustomerPostalCode");
        String newCustomerPhoneNumber = request.getParameter("newCustomerPhoneNumber");
        
        String updateCustomerFirstName = request.getParameter("updateCustomerFirstName");
        String updateCustomerLastName = request.getParameter("updateCustomerLastName");
        String updateCustomerAddress = request.getParameter("updateCustomerAddress");
        String updateCustomerEmail = request.getParameter("updateCustomerEmail");
        String updateCustomerPostalCode = request.getParameter("updateCustomerPostalCode");
        String updateCustomerPhoneNumber = request.getParameter("updateCustomerPhoneNumber");
        
        String newOrderBreadOptions = request.getParameter("newOrderBreadOptions");
        String newOrderQuantity = request.getParameter("newOrderQuantity");
        String newOrderDeliveryDate = request.getParameter("newOrderDeliveryDate");
        String newOrderNote = request.getParameter("newOrderNote");
        
        ArrayList<Customer> customerList = (ArrayList<Customer>)request.getAttribute("customerList");
        
        int customerId = 0;
        if (customerList == null) {
            customerList = dbops.getCustomersForManager();
        }
        
        if (customerIdParam != null) {
            customerId = Integer.parseInt(customerIdParam);
        }
        
        if (sortCustomerBy != null && orderOfSortCustomer != null) {
            
        } else if (searchCustomerFirstName != null) {
            if (!searchCustomerFirstName.equals(""))
                customerList = dbops.searchCustomerFirstName(searchCustomerFirstName);
            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
        } else if (searchCustomerLastName != null) {
            if (!searchCustomerLastName.equals(""))
                customerList = dbops.searchCustomerLastName(searchCustomerLastName);
            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
        } else if (newCustomerFirstName != null
                && newCustomerLastName != null 
                && newCustomerAddress != null 
                && newCustomerPostalCode != null 
                && newCustomerPhoneNumber != null) {

            dbops.addCustomer(newCustomerFirstName, newCustomerLastName, newCustomerAddress, 'P', newCustomerPostalCode, "", "", "", Long.parseLong(newCustomerPhoneNumber), 'A');
            customerList = dbops.getCustomersForManager();
            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
        } else if (action != null) {
            if (action.equals("delete")) {
                dbops.deleteCustomer(customerId);
                customerList = dbops.getCustomersForManager();
                request.setAttribute("customerList", customerList);
                request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                Customer customer = dbops.getCustomerById(customerId);
                request.setAttribute("customer", customer);
                ArrayList<Order> orderList = dbops.getOrdersForCustomer(customerId);
                request.setAttribute("orderList", orderList);
                request.getRequestDispatcher("/WEB-INF/clientDetailsScreen.jsp").forward(request, response);
            } else {
                request.setAttribute("customerList", customerList);
                request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
            }
        } else if (updateCustomerFirstName != null
//                && updateCustomerLastName != null 
//                && updateCustomerAddress != null 
//                && updateCustomerEmail != null
//                && updateCustomerPostalCode != null 
//                && updateCustomerPhoneNumber != null
                ) {
            
            dbops.updateCustomer(customerId, updateCustomerFirstName, updateCustomerLastName, updateCustomerAddress, updateCustomerEmail, updateCustomerPostalCode, Long.parseLong(updateCustomerPhoneNumber) );
            Customer customer = dbops.getCustomerById(customerId);
            request.setAttribute("customer", customer);
            ArrayList<Order> orderList = dbops.getOrdersForCustomer(customerId);
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("/WEB-INF/clientDetailsScreen.jsp").forward(request, response);
        } else if (newOrderBreadOptions!=null
                && newOrderQuantity!=null
                && newOrderDeliveryDate!=null
                && newOrderNote!=null) {
            
        }
        else {
            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("/WEB-INF/clientScreen.jsp").forward(request, response);
        }
        
        
    }
    
//    private ArrayList<Customer> sortCustomerList(ArrayList list, String sortBy, String order) {
//        
//        return list;
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
