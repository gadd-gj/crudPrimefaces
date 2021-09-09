package com.mycompany.primefaces.dao;

import com.mycompany.primefaces.Conexion;
import com.mycompany.primefaces.entity.Empleado;
import com.mycompany.primefaces.inter.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class EmpleadoDAO implements IDAO<Empleado> {

    private Conexion conexion = Conexion.getInstance();
    private Connection conn = conexion.getConnection();

    @Override
    public boolean insert(Empleado pojo) {
        PreparedStatement pst = null;
        boolean centinela = false;

        try {

            String sql = "INSERT INTO empleados(clave, nombre, direccion, telefono) VALUES(?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);

            pst.setString(1, pojo.getClave());
            pst.setString(2, pojo.getNombre());
            pst.setString(3, pojo.getDireccion());
            pst.setString(4, pojo.getTelefono());
            pst.execute();
            centinela = true;

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

    @Override
    public boolean update(Empleado pojo) {
        PreparedStatement pst = null;
        boolean centinela = false;

        try {

            String sql = "UPDATE empleados SET nombre = ?, direccion = ?, telefono = ? WHERE clave = ? ";
            pst = conn.prepareStatement(sql);

            pst.setString(1, pojo.getNombre());
            pst.setString(2, pojo.getDireccion());
            pst.setString(3, pojo.getTelefono());
            pst.setString(4, pojo.getClave());
            pst.execute();
            centinela = true;
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

    @Override
    public Empleado searchById(String clave) {
        PreparedStatement pst = null;
        Empleado pojo = new Empleado();
        ResultSet rs;

        try {

            String sql = "SELECT * FROM empleados WHERE clave = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, clave);
            rs = pst.executeQuery();
            while (rs.next()) {
                pojo.setClave(rs.getString(1));
                pojo.setNombre(rs.getString(2));
                pojo.setDireccion(rs.getString(3));
                pojo.setTelefono(rs.getString(4));

            }

            rs.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pojo;
    }

    @Override
    public ArrayList showAll() {
        ArrayList<Empleado> list = new ArrayList<Empleado>();

        Statement stm = null;

        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM empleados;");
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setClave(rs.getString("clave"));
                emp.setNombre(rs.getString("nombre"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setTelefono(rs.getString("telefono"));
                list.add(emp);
            }
            stm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public boolean delete(String clave) {
        PreparedStatement pst = null;
        boolean centinela = false;

        try {

            String sql = "DELETE FROM empleados WHERE clave = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, clave);
            pst.execute();
            centinela = true;

            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centinela;
    }

}
