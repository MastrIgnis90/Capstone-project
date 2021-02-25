/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.database;
import java.sql.*;
/**
 *
 * @author Sebastian Wild
 */
public class DBOperations {
    public void test() {
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            Connection conn = cp.getConnection();
            PreparedStatement ps = conn.prepareStatement("Select * from customers");
            ResultSet rs  = ps.executeQuery();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
