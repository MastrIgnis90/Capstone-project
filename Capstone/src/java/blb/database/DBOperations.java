/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.database;

import blb.domain.orders.Order;
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
        
        String sql = "select order_id, order_notes from orders where order_date = ?";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, parseDate(date));
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Order order = new Order(rs.getInt(1), "---", rs.getString(3));
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
    
    private String parseDate(String date) throws ParseException {
            Date dateObj = new SimpleDateFormat("EEEE d, MMMM y").parse(date);
            date = new SimpleDateFormat("yyyy-MM-dd").format(dateObj);
        
        return date;
    }
}
