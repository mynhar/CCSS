/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.controller;

import com.ccss.ejb.ProductoSessionBeanLocal;
import com.ccss.model.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Harold
 */
@Named
@ViewScoped
public class BuscarProductoController implements Serializable {

    private UIInput textIdProducto;
    private UIInput textPrecioUnidad;
    private UIInput textCantidad;

    private Producto producto;

    @EJB
    private ProductoSessionBeanLocal productoSessionBeanLocal;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UIInput getTextIdProducto() {
        return textIdProducto;
    }

    public void setTextIdProducto(UIInput textIdProducto) {
        this.textIdProducto = textIdProducto;
    }

    public UIInput getTextPrecioUnidad() {
        return textPrecioUnidad;
    }

    public void setTextPrecioUnidad(UIInput textPrecioUnidad) {
        this.textPrecioUnidad = textPrecioUnidad;
    }

    public UIInput getTextCantidad() {
        return textCantidad;
    }

    public void setTextCantidad(UIInput textCantidad) {
        this.textCantidad = textCantidad;
    }

    /**
     *
     */
    public void buscarProducto() {

        Object idObj = textIdProducto.getSubmittedValue();

        try {
            if (idObj != null && !idObj.equals("")) {
                int idProducto = Integer.parseInt(idObj.toString().trim());
                this.producto = productoSessionBeanLocal.find(idProducto);

                if (this.producto != null) {

                    textIdProducto.setSubmittedValue(this.producto.getID_PRODUCTO());
                    textPrecioUnidad.setSubmittedValue(this.producto.getMON_PRODUCTO());
                    textCantidad.setSubmittedValue(this.producto.getCAN_PRODUCTO());

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));
                }
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.FacturaController.buscarProducto() " + e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public Integer getCantidad() {
        Object obj = textCantidad.getSubmittedValue();
        if (obj != null && !obj.toString().trim().equals("")) {            
            int cantidad = Integer.parseInt(obj.toString().trim());
            return cantidad;
        }

        return 0;
    }

    
}
