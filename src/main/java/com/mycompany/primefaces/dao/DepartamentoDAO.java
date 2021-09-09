/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primefaces.dao;

import com.mycompany.primefaces.Conexion;
import com.mycompany.primefaces.entity.Departamento;
import com.mycompany.primefaces.inter.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class DepartamentoDAO implements IDAO<Departamento> {

    private Conexion conexion = Conexion.getInstance();
    private Connection con = conexion.getConnection();

    @Override
    public boolean insert(Departamento pojo) {
        PreparedStatement pst = null;
        boolean centinela = false;
        try {

            String sql = "insert into departamentos(clave, nombre) values(?, ?)";
            pst = con.prepareStatement(sql);

            pst.setString(1, pojo.getClave());
            pst.setString(2, pojo.getNombre());
            pst.execute();
            centinela = true;
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

    @Override
    public boolean update(Departamento pojo) {
        PreparedStatement pst = null;
        boolean centinela = false;

        try {

            String sql = "UPDATE departamentos set  nombre = ? where clave = ? ";
            pst = con.prepareStatement(sql);

            pst.setString(1, pojo.getNombre());
            pst.setString(2, pojo.getClave());
            pst.execute();
            centinela = true;
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

    @Override
    public Departamento searchById(String clave) {
        Departamento pojo = new Departamento();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            String sql = "select * from departamentos where clave = ?";

            pst = con.prepareStatement(sql);
            pst.setString(1, clave);
            rs = pst.executeQuery();
            while (rs.next()) {
                pojo.setClave(rs.getString(1));
                pojo.setNombre(rs.getString(2));
            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pojo;
    }

    @Override
    public List showAll() {
        List<Departamento> list = new ArrayList<Departamento>();

        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("SELECT * FROM departamentos");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setClave(rs.getString(1));
                dep.setNombre(rs.getString(2));
                list.add(dep);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    @Override
    public boolean delete(String clave) {

        PreparedStatement pst = null;
        boolean centinela = false;

        try {

            String sql = "delete from departamentos where clave = ?";
            pst = con.prepareStatement(sql);

            pst.setString(1, clave);
            pst.execute();
            centinela = true;

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

}
