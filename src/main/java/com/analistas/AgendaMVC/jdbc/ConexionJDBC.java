/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pcc
 */

public class ConexionJDBC {
    public Connection getConnection(String nombreBD) {
        try {
            return DriverManager.
                    getConnection("jdbc:mysql://localhost/" 
                            + nombreBD, "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
