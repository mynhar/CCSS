/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.controller;

import com.ccss.ejb.ProductoSessionBeanLocal;
import com.ccss.model.Producto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Harold
 */
@Named
@RequestScoped
public class ProductoController implements Serializable {
    
    private Producto producto;
    private List<Producto> listar;

    @EJB
    private ProductoSessionBeanLocal productoSessionBeanLocal;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        findAll();        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListar() {
        return listar;
    }

    public void setListar(List<Producto> listar) {
        this.listar = listar;
    }

    /**
     *
     */
    public void create() {        
        try {

            productoSessionBeanLocal.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.ProductoController.create() " + e.getMessage());
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    /**
     *
     */
    public void findAll() {
        try {
            this.listar = productoSessionBeanLocal.findAll();
        } catch (Exception e) {
            System.out.println("com.ccss.controller.ProductoController.findAll() " + e.getMessage());
        }
    }

    public void asignar(Producto obj) {        
        this.producto = obj;        
    }

    /**
     * 
     * @param obj 
     */
    public void delete(Producto obj) {        
        try {

            Integer result = productoSessionBeanLocal.delete(obj.getID_PRODUCTO());
            findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.ProductoController.seleccionar() " + e.getMessage());
        }
    }
    
}
