/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.domain.products;

/**
 *
 * @author 798382
 */
public class ReportDay {
    
    private String reportDay;
    private String reportDate;
    private int totalOrderNumber;

    public ReportDay() {
    }

    public ReportDay(String reportDay, String reportDate, int totalOrderNumber) {
        this.reportDay = reportDay;
        this.reportDate = reportDate;
        this.totalOrderNumber = totalOrderNumber;
    }

    public String getReportDay() {
        return reportDay;
    }

    public void setReportDay(String reportDay) {
        this.reportDay = reportDay;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getTotalOrderNumber() {
        return totalOrderNumber;
    }

    public void setTotalOrderNumber(int totalOrderNumber) {
        this.totalOrderNumber = totalOrderNumber;
    }
    
    
}
