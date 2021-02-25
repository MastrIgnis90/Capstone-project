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
import java.util.ArrayList;

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
        
        String sql = "SELECT * FROM bridgelandbakery.order WHERE delivery_date = ? ";
        
         try {
            Connection conn = cp.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, date);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Order order = new Order(rs.getInt(1), rs.getString(8));
            }
            rs.close();
            st.close();
            cp.freeConnection(conn);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
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
}
