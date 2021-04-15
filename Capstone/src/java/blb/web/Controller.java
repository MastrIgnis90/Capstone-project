/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
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
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

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
        boolean getPreviousReport = Boolean.parseBoolean(request.getParameter("getPreviousReport"));
        boolean goToDeliverySchedule = Boolean.parseBoolean(request.getParameter("goToDeliverySchedule"));
        boolean goToDailyReport = Boolean.parseBoolean(request.getParameter("goToDailyReport"));
        boolean goToManageClients = Boolean.parseBoolean(request.getParameter("goToManageClients"));
        boolean goToManageProducts = Boolean.parseBoolean(request.getParameter("goToManageProducts"));
        
        DBOperations dbops = new DBOperations();
        
        if(goToDeliverySchedule) {
            request.getRequestDispatcher("/WEB-INF/deliveryScheduleScreen.jsp").forward(request, response);
        }else if(goToDailyReport){
            String date = new SimpleDateFormat("EEEE d, MMMM y").format(new Date());
            request.setAttribute("reportDate", date);
            request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if (goToManageClients) {
            request.getRequestDispatcher("customerServices").forward(request, response);
        } else if (goToManageProducts) {
            
        }
        else if(getPreviousReport) {
            request.getRequestDispatcher("ReportServices").forward(request, response);
        } else if(action==null) {
            request.getRequestDispatcher("/WEB-INF/LoginScreen.jsp").forward(request, response);
        } else if(action.equals("Login")) {
            request.getRequestDispatcher("LoginServices").forward(request, response);
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
