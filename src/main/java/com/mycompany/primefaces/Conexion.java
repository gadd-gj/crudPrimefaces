/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primefaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class Conexion {

    private static Conexion con = null;

    public static Conexion getInstance() {
        if (con == null) {
            con = new Conexion();
        }
        return con;
    }

    protected static Connection conex;
    protected ResultSet rs;
    private final String jdbc_drive = "org.postgresql.Driver";
    private final String db_url = "jdbc:postgresql://127.0.0.1:5432/prime";
    private final String user = "wisp";
    private final String pass = "wisp2020";

    private Conexion() {
        try {
            Class.forName(jdbc_drive);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conex = DriverManager.getConnection(db_url, user, pass);
            System.out.println("Conexion Exitosa :D");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return conex;
    }

    public boolean execute(String sql) {
        boolean res = false;
        try {
            Statement st = conex.createStatement();
            st.execute(sql);
            res = true;
        } catch (Exception e) {

        }

        return res;
    }

}
