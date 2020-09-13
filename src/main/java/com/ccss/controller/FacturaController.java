/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.controller;

import com.ccss.ejb.FacturaSessionBeanLocal;
import com.ccss.model.DetalleFactura;
import com.ccss.model.Factura;
import com.ccss.model.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
public class FacturaController implements Serializable {

    @EJB
    private FacturaSessionBeanLocal facturaSessionBeanLocal;

    @Inject
    private BuscarProductoController buscarProductoController;

    private Factura factura;
    private Integer ID_FACTURA;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal MON_IMPUESTO = BigDecimal.ZERO;
    private BigDecimal MON_TOTAL = BigDecimal.ZERO;
    private List<DetalleFactura> detalleList = new ArrayList<DetalleFactura>();
    private Integer CAN_PRODUCTO;

    @PostConstruct
    public void init() {
        this.factura = new Factura();
        this.subTotal = new BigDecimal(BigInteger.ZERO);

    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void agregarDetalle() {
        System.out.println("agregarDetalle===========================>");

        try {
            Producto producto = buscarProductoController.getProducto();
            this.CAN_PRODUCTO = buscarProductoController.getCantidad();
            if(this.CAN_PRODUCTO.equals(0)){
                setCantidad();
            }

            if (producto != null) {
                System.out.println("CAN_PRODUCTO: " + this.CAN_PRODUCTO);

                if (this.CAN_PRODUCTO > 0) {
                    DetalleFactura detalle = new DetalleFactura();
                    detalle.setProducto(producto);
                    detalle.setCAN_PRODUCTO(this.CAN_PRODUCTO);
                    BigDecimal catidadProducto = new BigDecimal(detalle.getCAN_PRODUCTO());
                    BigDecimal montoProducto = producto.getMON_PRODUCTO().multiply(catidadProducto);
                    detalle.setMON_PRODUCTO(montoProducto);
                    this.sumarSubTotal(detalle.getMON_PRODUCTO());

                    this.detalleList.add(detalle);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));
                    
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Cantidad, debe mayor a cero!"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Debe buscar un producto!"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.FacturaController.buscarProducto() " + e.getMessage());
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public List<DetalleFactura> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<DetalleFactura> detalleList) {
        this.detalleList = detalleList;
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

    /**
     *
     * @param mon_producto
     */
    private void sumarSubTotal(BigDecimal mon_producto) {        
        if (mon_producto.compareTo(BigDecimal.ZERO) > 0) {
            this.subTotal = this.subTotal.add(mon_producto);
        }
        
        calcularImpuesto();
    }
    
    /**
     * 
     */
    public void calcularImpuesto(){
        this.MON_TOTAL = BigDecimal.ZERO;
        BigDecimal cien = new BigDecimal("100");
        if(this.MON_IMPUESTO.compareTo(BigDecimal.ZERO)>0){            
            BigDecimal impuesto = this.subTotal.multiply(this.MON_IMPUESTO).divide(cien);            
            this.MON_TOTAL = this.subTotal.add(impuesto);
                    
        }else{            
            this.MON_TOTAL = this.MON_TOTAL.add(this.subTotal);
        }
        
    }

    public void setCantidad() {
        this.CAN_PRODUCTO = buscarProductoController.getCantidad();
        System.out.println("CAN_PRODUCTO ===> : " + this.CAN_PRODUCTO);
    }
    
     /**
     *
     */
    public void facturar() {
        
        Date objDate = new Date(); 
        
        try {
            this.factura.setFEC_FACTURA(objDate);
            this.factura.setMON_IMPUESTO(this.getMON_IMPUESTO());
            this.factura.setMON_TOTAL(this.getMON_TOTAL());
            this.factura.setDetalleList(this.getDetalleList());
            
            Factura result = facturaSessionBeanLocal.create(factura);
            if(result != null){
                this.setFactura(result);
                this.setID_FACTURA(result.getID_FACTURA());
                this.setMON_IMPUESTO(result.getMON_IMPUESTO());
                this.setMON_TOTAL(result.getMON_TOTAL());
                
                List<DetalleFactura> resultDetalle = result.getDetalleList();
                if(resultDetalle != null && !resultDetalle.isEmpty()){
                    this.setDetalleList(resultDetalle);
                }                
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Correcto!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "InCorrecto!"));
            System.out.println("com.ccss.controller.FacturaController.create() " + e.getMessage());
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public Integer getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(Integer ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }
    
    
}
