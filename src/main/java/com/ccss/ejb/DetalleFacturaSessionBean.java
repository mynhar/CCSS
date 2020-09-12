/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.controller.resources.DetalleFacturaDao;
import com.ccss.model.DetalleFactura;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Harold
 */
@Stateless
public class DetalleFacturaSessionBean implements DetalleFacturaSessionBeanLocal {

    @Override
    public DetalleFactura create(DetalleFactura detalleFactura) {
        System.out.println("DetalleFacturaSessionBean.create: " + detalleFactura.toString());
        DetalleFacturaDao dao = new DetalleFacturaDao();
        return dao.create(detalleFactura);
    }

    @Override
    public DetalleFactura edit(DetalleFactura detalleFactura) {
        System.out.println("DetalleFacturaSessionBean.adit: " + detalleFactura.toString());
        DetalleFacturaDao dao = new DetalleFacturaDao();
        return dao.edit(detalleFactura);
    }

    @Override
    public Integer delete(Integer id) {
        System.out.println("DetalleFacturaSessionBean.delete: " + id);
        DetalleFacturaDao dao = new DetalleFacturaDao();
        return dao.delete(id);
    }

    @Override
    public DetalleFactura find(Integer id) {
        System.out.println("DetalleFacturaSessionBean.find: " + id);
        DetalleFacturaDao dao = new DetalleFacturaDao();
        return dao.find(id);
    }

    @Override
    public List<DetalleFactura> findAll() {
        System.out.println("DetalleFacturaSessionBean.findAll: ");
        DetalleFacturaDao dao = new DetalleFacturaDao();
        return dao.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
