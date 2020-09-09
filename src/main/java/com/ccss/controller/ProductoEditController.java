/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.controller;

import com.ccss.ejb.ProductoSessionBeanLocal;
import com.ccss.model.Producto;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Harold
 */
@Named
@ViewScoped
public class ProductoEditController implements Serializable {

    @EJB
    private ProductoSessionBeanLocal productoSessionBeanLocal;

    @Inject
    private ProductoController productoController;

    private Producto producto;

    @PostConstruct
    public void init() {
        this.producto = this.productoController.getProducto();
        System.out.println("init(): "+this.producto);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void edit() {
        System.out.println("com.ccss.controller.ProductoEditController.edit()");
        System.out.println("edit(): " + this.producto);

        try {

            productoSessionBeanLocal.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.ProductoEditController.edit() " + e.getMessage());
        } finally {
            //FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
}
