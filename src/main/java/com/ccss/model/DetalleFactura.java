/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Harold
 */
public class DetalleFactura implements Serializable{
    
    private Integer ID_DETALLE;
    private Integer ID_FACTURA;
    private Integer ID_PRODUCTO;
    private Producto producto;
    private Integer CAN_PRODUCTO;
    private BigDecimal MON_PRODUCTO = BigDecimal.ZERO;;

    public Integer getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(Integer ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

    public Integer getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(Integer ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCAN_PRODUCTO() {
        return CAN_PRODUCTO;
    }

    public void setCAN_PRODUCTO(Integer CAN_PRODUCTO) {
        this.CAN_PRODUCTO = CAN_PRODUCTO;
    }

    public BigDecimal getMON_PRODUCTO() {
        return MON_PRODUCTO;
    }

    public void setMON_PRODUCTO(BigDecimal MON_PRODUCTO) {
        this.MON_PRODUCTO = MON_PRODUCTO;
    }

    public Integer getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(Integer ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }
    
    

    @Override
    public String toString() {
        return "DetalleFactura{" + "ID_DETALLE=" + ID_DETALLE + ", ID_FACTURA=" + ID_FACTURA + ", CAN_PRODUCTO=" + CAN_PRODUCTO + ", MON_PRODUCTO=" + MON_PRODUCTO + '}';
    }
    
}
