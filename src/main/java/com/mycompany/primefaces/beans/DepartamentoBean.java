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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gaddiel
 */
@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private Departamento departamento = new Departamento();
    private DepartamentoBean departamentoBean;
    private IDAO daoDep = FactoryMethod.create(FactoryMethod.TypeDAO.DEPARTAMENTO);
    private List<Departamento> depas;

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {

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

    public List<Departamento> getDepas() {
        return depas = daoDep.showAll();
    }

    public void setDepas(List<Departamento> depas) {
        this.depas = depas;
    }

    public void guardar() {
        boolean res = daoDep.insert(getDepartamento());
        if (res) {
            addMessage(FacesMessage.SEVERITY_INFO, "Guardado", "El departamento se ha guardado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha podido guardar exitosamente");
        }
    }

    public void eliminar() {
        if (daoDep.delete(getDepartamento().getClave())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "El departamento se ha eliminado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha podido eliminar");
        }
    }

    public List mostrar() {
        return depas;
    }

    public Departamento buscar() {
        return departamento = (Departamento) daoDep.searchById(departamento.getClave());
    }

    public void actualizar() {
        if (daoDep.update(getDepartamento())) {
            addMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "El departamento se ha actualizado exitosamente");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El departamento no se ha encontrado");
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
