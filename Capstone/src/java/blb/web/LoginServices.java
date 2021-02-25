/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sebastian Wild
 */
@WebServlet(name = "LoginServices", urlPatterns = {"/LoginServices"})
public class LoginServices extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        
        
            
           
        if(username.trim().equals("") || password.trim().equals("")){// nothing supplied
            request.setAttribute("message", "Both username and password are required");
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        } else if(username.trim().equals("john@john") && password.trim().equals("password")){//!!!!!!!!! CHANGE TO DBOPS!!!!!!!
            //request.setAttribute("dailyReportProductionList", dbops.getProductionList());
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE d, MMMM y");
            request.setAttribute("reportDate", sdf.format(new Date()).toString());
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);// !!!! change to correct report page!!!!
            
            
        } else { //invalid username or password
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }
    }

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
