/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primefaces.factory;

import com.mycompany.primefaces.dao.DepartamentoDAO;
import com.mycompany.primefaces.dao.EmpleadoDAO;
import com.mycompany.primefaces.inter.IDAO;

/**
 *
 * @author gaddiel
 */
public class FactoryMethod {

    public enum TypeDAO {
        EMPLEADO, DEPARTAMENTO
    }

    public static IDAO create(TypeDAO t) {
        IDAO dao = null;

        switch (t) {
            case EMPLEADO:
                dao = new EmpleadoDAO();
                break;
            case DEPARTAMENTO:
                dao = new DepartamentoDAO();
                break;
        }

        return dao;
    }
}
