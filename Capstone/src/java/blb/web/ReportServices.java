/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
import blb.domain.orders.Order;
import blb.utils.DateHelper;
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
import java.util.Locale;
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
        boolean getWeeklyReport = Boolean.parseBoolean(request.getParameter("getWeeklyReport"));
        boolean getNextWeeklyReport = Boolean.parseBoolean(request.getParameter("getNextWeeklyReport"));
        boolean getPreviousWeeklyReport = Boolean.parseBoolean(request.getParameter("getPreviousWeeklyReport"));
        boolean print = Boolean.parseBoolean(request.getParameter("print"));
        boolean getMonthlyReport = Boolean.parseBoolean(request.getParameter("getMonthlyReport"));
        String date = request.getParameter("dailyReportDate");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        ArrayList<Order> orders;
        DBOperations dbops = new DBOperations();
        DateHelper dh = new DateHelper();
        
        if(print){
            response.setContentType("application/pdf;charset=UTF-8");
            response.addHeader("Content-Disposition", "inline; filename=" + "orders.pdf");
            ServletOutputStream out = response.getOutputStream();
            orders = dbops.getDailyReportProductionList(date);
            ByteArrayOutputStream baos = GeneratePDF.getpdfFile(orders);
            baos.writeTo(out);
            
        } else if(getDailyReport) {
            
            request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
            request.setAttribute("reportDate", date);
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
            
        }else if(getPreviousReport){
            try {
                request.setAttribute("reportDate", dh.prevDate(date));
                request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
                //System.out.println(dh.parseDatabase(date));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if(getNextDailyReport){
            try {
                request.setAttribute("reportDate", dh.nextDate(date));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if(sortDailyReport) {
            String sortDailyReportBy = request.getParameter("sortDailyReportBy");
            String orderOfSortDailyReport = request.getParameter("orderOfSortDailyReport");
            
            //order = dbops.getList(date, sortDailyReportBy, orderOfSortDailyReport);
        } else if(getWeeklyReport) {
            try {
                request.setAttribute("startDate", dh.weekStartDate(startDate));
                request.setAttribute("endDate", dh.weekEndDate(endDate));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getRequestDispatcher("/WEB-INF/reportWeeklyScreen.jsp").forward(request, response);
        }
        
        else if(getMonthlyReport) {
            request.getRequestDispatcher("/WEB-INF/reportMonthlyScreen.jsp").forward(request, response);
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
