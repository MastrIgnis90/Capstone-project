/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Sebastian Wild, Alexander Peluso
 * Update Feb 22, 2021
 * Section of code dedicated to the creation of the needed connection pools for 
 * the app, it will connect directly to the database, consists of 4 methods that 
 * control the different connections, from creation, connection, and termination.
 * There is one private constructor.
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    
    //private constructor, with an exception catch, in case there is a problem.
    private ConnectionPool() {
        
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/bridgelandbread"); 
        }
        catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    //The static instance creation
    public static synchronized ConnectionPool getInstance() {
        if (pool==null) {
            pool = new ConnectionPool();
        }
        
        return pool;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void freeConnection(Connection conn) {
        try {
            conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
