/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Harold
 */
public class Factura implements Serializable{
    
    private Integer ID_FACTURA;
    private String NOM_CLIENTE;
    private Date FEC_FACTURA;
    private BigDecimal MON_IMPUESTO = BigDecimal.ZERO;
    private BigDecimal MON_TOTAL = BigDecimal.ZERO;
    
    private List<DetalleFactura> detalleList = new ArrayList<>();


    public Integer getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(Integer ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public String getNOM_CLIENTE() {
        return NOM_CLIENTE;
    }

    public void setNOM_CLIENTE(String NOM_CLIENTE) {
        this.NOM_CLIENTE = NOM_CLIENTE;
    }

    public Date getFEC_FACTURA() {
        return FEC_FACTURA;
    }

    public void setFEC_FACTURA(Date FEC_FACTURA) {
        this.FEC_FACTURA = FEC_FACTURA;
    }

    public BigDecimal getMON_IMPUESTO() {
        return MON_IMPUESTO;
    }

    public void setMON_IMPUESTO(BigDecimal MON_IMPUESTO) {
        this.MON_IMPUESTO = MON_IMPUESTO;
    }

    public BigDecimal getMON_TOTAL() {
        return MON_TOTAL;
    }

    public void setMON_TOTAL(BigDecimal MON_TOTAL) {
        this.MON_TOTAL = MON_TOTAL;
    }

    public List<DetalleFactura> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<DetalleFactura> detalleList) {
        this.detalleList = detalleList;
    }    

    @Override
    public String toString() {
        return "Factura{" + "ID_FACTURA=" + ID_FACTURA + ", NOM_CLIENTE=" + NOM_CLIENTE + ", FEC_FACTURA=" + FEC_FACTURA + ", MON_IMPUESTO=" + MON_IMPUESTO + ", MON_TOTAL=" + MON_TOTAL + '}';
    }
       
}
