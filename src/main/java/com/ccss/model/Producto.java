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

public class Producto implements Serializable{
    

    private Integer ID_PRODUCTO;
    

    private String NOM_PRODUCTO;
    

    private Integer CAN_PRODUCTO;
    

    private BigDecimal MON_PRODUCTO;

    public Integer getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(Integer ID_PRODUCTO) {        
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getNOM_PRODUCTO() {
        return NOM_PRODUCTO;
    }

    public void setNOM_PRODUCTO(String NOM_PRODUCTO) {
        this.NOM_PRODUCTO = NOM_PRODUCTO;
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

    @Override
    public String toString() {
        return "Producto{" + "ID_PRODUCTO=" + ID_PRODUCTO + ", NOM_PRODUCTO=" + NOM_PRODUCTO + ", CAN_PRODUCTO=" + CAN_PRODUCTO + ", MON_PRODUCTO=" + MON_PRODUCTO + '}';
    }    
    
}
