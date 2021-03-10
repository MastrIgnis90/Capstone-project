/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Sebastian Wild
 */
public class DateHelper {
    public DateHelper() {}
    
    public String nextDate(String date) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d, MMMM y");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);
       
        return sdf.format(c.getTime());
    }
    
    public String prevDate(String date) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d, MMMM y");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -1);
       
        return sdf.format(c.getTime());
    }
    
    public String prevWeekStart(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -7);
       
        return sdf.format(c.getTime());
    }
    
    public String prevWeekEnd(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d, y");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -7);
       
        return sdf.format(c.getTime());
    }
    
    public String weekStartDate(String date) throws ParseException {
        Date startDate = new SimpleDateFormat("EEE MMM dd yyyy '00:00:00' 'GMT'Z '('zzzz')'", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE MMM d").format(startDate);
    }
    public String weekEndDate(String date) throws ParseException {
        Date endDate = new SimpleDateFormat("EEE MMM dd yyyy '00:00:00' 'GMT'Z '('zzzz')'", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE MMM d, y").format(endDate);
    }
    
}