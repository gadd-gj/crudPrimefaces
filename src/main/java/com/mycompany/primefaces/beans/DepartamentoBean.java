/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.primefaces.beans;

import com.mycompany.primefaces.entity.Departamento;
import com.mycompany.primefaces.factory.FactoryMethod;
import com.mycompany.primefaces.inter.IDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gaddiel
 */
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private Departamento departamento;
    private DepartamentoBean departamentoBean;

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new Departamento();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentoBean getDepartamentoBean() {
        return departamentoBean;
    }

    public void setDepartamentoBean(DepartamentoBean departamentoBean) {
        this.departamentoBean = departamentoBean;
    }

    public void guardar() {
        IDAO daoDep = FactoryMethod.create(FactoryMethod.TypeDAO.DEPARTAMENTO);

        boolean res = daoDep.insert(getDepartamento());
        if (res) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El departamento se ha guardado exitosamente");
        }

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
