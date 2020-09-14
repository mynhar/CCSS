/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.dao;

import com.ccss.model.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold
 */
public class ConexionJDBCOracle {
    
    Connection con = null;
    
    public ConexionJDBCOracle() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
                //step2 create  the connection object
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "admin123");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionJDBCOracle.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
                Logger.getLogger(ConexionJDBCOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
