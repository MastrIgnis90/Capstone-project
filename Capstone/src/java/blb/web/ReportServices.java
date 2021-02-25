/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
import blb.domain.orders.Order;
import blb.utils.GeneratePDF;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author Sebastian Wild
 */
@WebServlet(name = "ReportServices", urlPatterns = {"/ReportServices"})
public class ReportServices extends HttpServlet {

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
        
        boolean getPreviousReport = Boolean.parseBoolean(request.getParameter("getPreviousReport"));
        boolean getDailyReport = Boolean.parseBoolean(request.getParameter("getDailyReport"));
        boolean getNextDailyReport = Boolean.parseBoolean(request.getParameter("getNextDailyReport"));
        boolean sortDailyReport = Boolean.parseBoolean(request.getParameter("sortDailyReport"));
        String date = request.getParameter("dailyReportDate");
        String action = request.getParameter("action");
        ArrayList<Order> orders = new ArrayList<>();
        
        DBOperations dbops = new DBOperations();
        
        
        if(action!=null){
            response.setContentType("application/pdf;charset=UTF-8");
            response.addHeader("Content-Disposition", "inline; filename=" + "orders.pdf");
            ServletOutputStream out = response.getOutputStream();
            orders = dbops.getDailyReportProductionList(date);
            ByteArrayOutputStream baos = GeneratePDF.getpdfFile(orders);
            baos.writeTo(out);
        } else {
        
            if(getDailyReport) {
                
            } else if(getPreviousReport || getNextDailyReport){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE d, MMMM y");
                    Calendar c = Calendar.getInstance();
                    c.setTime(sdf.parse(date));
                    
                    if(getPreviousReport){
                        c.add(Calendar.DATE, -1);
                    } else if (getNextDailyReport) {
                        c.add(Calendar.DATE, 1);
                    }
                    
                    date = sdf.format(c.getTime());
                
                } catch (ParseException ex) {
                    Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else if(sortDailyReport) {
                String sortDailyReportBy = request.getParameter("sortDailyReportBy");
                String orderOfSortDailyReport = request.getParameter("orderOfSortDailyReport");
                
                //order = dbops.getList(date, sortDailyReportBy, orderOfSortDailyReport);
            }
            
            orders = dbops.getDailyReportProductionList(date);
            request.setAttribute("dailyReportProductionList", orders);
            request.setAttribute("reportDate", date);
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
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
