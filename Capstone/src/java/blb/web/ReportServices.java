/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.web;

import blb.database.DBOperations;
import blb.domain.orders.Order;
import blb.domain.products.ReportDay;
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
import java.time.Month;
import java.time.format.TextStyle;
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
@WebServlet(name = "ReportServices", urlPatterns = {"/reportServices"})
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
        boolean getPreviousMonthlyReport = Boolean.parseBoolean(request.getParameter("getPreviousMonthlyReport"));
        boolean getNextMonthlyReport = Boolean.parseBoolean(request.getParameter("getNextMonthlyReport"));
        String date = request.getParameter("dailyReportDate");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        ArrayList<Order> orders;

        if (date == null) {
            date = new SimpleDateFormat("EEEE MMMM d, y").format(new Date());
            //request.setAttribute("reportDate", date);
        } else if (date.equals("")) {
            date = new SimpleDateFormat("EEEE MMMM d, y").format(new Date());
        }

        DBOperations dbops = new DBOperations();
        DateHelper dh = new DateHelper();

        if (print) {
            response.setContentType("application/pdf;charset=UTF-8");
            response.addHeader("Content-Disposition", "inline; filename=" + "orders.pdf");
            ServletOutputStream out = response.getOutputStream();
            orders = dbops.getDailyReportProductionList(date);
            ByteArrayOutputStream baos = GeneratePDF.getpdfFile(orders);
            baos.writeTo(out);

        } else if (getDailyReport) {

            request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(date));
            request.setAttribute("reportDate", date);
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);

        } else if (getPreviousReport) {
            try {
                request.setAttribute("reportDate", dh.prevDate(date));
                request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(dh.prevDate(date)));

            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if (getNextDailyReport) {
            try {
                request.setAttribute("reportDate", dh.nextDate(date));
                request.setAttribute("dailyReportProductionList", dbops.getDailyReportProductionList(dh.nextDate(date)));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if (sortDailyReport) {
            String sortDailyReportBy = request.getParameter("sortDailyReportBy");
            String orderOfSortDailyReport = request.getParameter("orderOfSortDailyReport");
            
            request.setAttribute("reportDate", date);
//            request.setAttribute("dailyReportProductionList", dbops.getSortedList(date, sortDailyReportBy, orderOfSortDailyReport));
            request.getRequestDispatcher("/WEB-INF/reportDailyScreen.jsp").forward(request, response);
        } else if (getWeeklyReport) {
            try {
                request.setAttribute("startDate", dh.weekStartDate(startDate));
                request.setAttribute("endDate", dh.weekEndDate(endDate));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/reportWeeklyScreen.jsp").forward(request, response);
        } else if (getNextWeeklyReport) {
            try {
                request.setAttribute("startDate", dh.nextWeekStart(date));
                request.setAttribute("endDate", dh.nextWeekEnd(date));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/reportWeeklyScreen.jsp").forward(request, response);
        } else if (getPreviousWeeklyReport) {
            try {
                String weekStart = dh.prevWeekStart(date);
                String weekEnd = dh.prevWeekEnd(date);
                request.setAttribute("startDate", weekStart);
                request.setAttribute("endDate", weekEnd);
                ArrayList<ReportDay> list = new ArrayList<>();
                list.add(new ReportDay("reportDay", "reportDate", 5));
                list.add(new ReportDay("reportDay", "reportDate", 4));
                list.add(new ReportDay("reportDay", "reportDate", 3));
                request.setAttribute("weekReportProductionList", list);
                dbops.getCustomerWithOrdersByDateRange(dh.weekStartToDatabase(weekStart), dh.weekEndToDatabase(weekEnd));
            } catch (ParseException ex) {
                Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/reportWeeklyScreen.jsp").forward(request, response);
        } else if (getMonthlyReport) {
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            
            String monthOG = request.getParameter("month");
            String yearOG = request.getParameter("year");

            if (month == null || year == null) {
                Calendar calendar = Calendar.getInstance();
                month = Month.of(calendar.get(Calendar.MONTH)).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                year = String.valueOf(calendar.get(Calendar.YEAR));
            } else if (month.equals("") || year.equals("")) {
                Calendar calendar = Calendar.getInstance();
                month = Month.of(calendar.get(Calendar.MONTH)).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                year = String.valueOf(calendar.get(Calendar.YEAR));
            }
//            try {
                
//                monthOG.toUpperCase();
                
//                ArrayList<ReportDay> list = dbops.getReportMonthlyOrders(month, year);
//                request.setAttribute("monthReportProductionList", list);

                String reportMonthDate = month + ", " + year;
                request.setAttribute("reportMonthDate", reportMonthDate);

                request.getRequestDispatcher("/WEB-INF/reportMonthlyScreen.jsp").forward(request, response);
//            } catch (ParseException ex) {
//                request.getRequestDispatcher("/WEB-INF/500ErrorScreen.jsp").forward(request, response);
//            }
        } else if (getPreviousMonthlyReport) {
            String monthYearString = request.getParameter("monthYear");
            String[] monthYear = monthYearString.split(",");
            String month = monthYear[0];
            String year = monthYear[1];

            try {
                String[] prevMonth = dh.prevMonth(month, year);
                ArrayList<ReportDay> list = dbops.getReportMonthlyOrders(prevMonth[0], prevMonth[1]);
                request.setAttribute("monthReportProductionList", list);

                String reportMonthDate = prevMonth[0] + ", " + prevMonth[1];
                request.setAttribute("reportMonthDate", reportMonthDate);
                request.getRequestDispatcher("/WEB-INF/reportMonthlyScreen.jsp").forward(request, response);
            } catch (ParseException ex) {
                request.getRequestDispatcher("/WEB-INF/500ErrorScreen.jsp").forward(request, response);
            }

        } else if (getNextMonthlyReport) {
            String monthYearString = request.getParameter("monthYear");
            String[] monthYear = monthYearString.split(",");
            String month = monthYear[0];
            String year = monthYear[1];

            try {
                String[] nextMonth = dh.nextMonth(month, year);
                ArrayList<ReportDay> list = dbops.getReportMonthlyOrders(nextMonth[0], nextMonth[1]);
                request.setAttribute("monthReportProductionList", list);

                String reportMonthDate = nextMonth[0] + ", " + nextMonth[1];
                request.setAttribute("reportMonthDate", reportMonthDate);
                request.getRequestDispatcher("/WEB-INF/reportMonthlyScreen.jsp").forward(request, response);
            } catch (ParseException ex) {
                request.getRequestDispatcher("/WEB-INF/500ErrorScreen.jsp").forward(request, response);
            }
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
