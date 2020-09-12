/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.controller.resources.FacturaDao;
import com.ccss.model.DetalleFactura;
import com.ccss.model.Factura;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Harold
 */
@Stateless
public class FacturaSessionBean implements FacturaSessionBeanLocal {

    @EJB
    DetalleFacturaSessionBeanLocal detalleFacturaSessionBeanLocal;

    @Override
    public Factura create(Factura factura) {
        System.out.println("FacturaSessionBean.create:" + factura.toString());
        FacturaDao dao = new FacturaDao();

        Factura obj = dao.create(factura);
        if (obj != null) {
            Integer idFactura = obj.getID_FACTURA();
            if (idFactura != null) {
                List<DetalleFactura> lista = obj.getDetalleList();
                if (lista != null && !lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        lista.get(i).setID_FACTURA(idFactura);
                        DetalleFactura resultDF = detalleFacturaSessionBeanLocal.create(lista.get(i));
                        Integer idDetalle = resultDF.getID_DETALLE();
                        if (idDetalle != null) {
                            lista.get(i).setID_DETALLE(idDetalle);
                        }
                    }
                    factura.setDetalleList(lista);
                }
            }
        }

        return factura;
    }

    @Override
    public Factura edit(Factura factura) {
        System.out.println("FacturaSessionBean.edit:" + factura.toString());
        FacturaDao dao = new FacturaDao();

        Factura obj = dao.edit(factura);
        if (obj != null) {
            Integer idFactura = obj.getID_FACTURA();
            if (idFactura != null) {
                List<DetalleFactura> lista = obj.getDetalleList();
                if (lista != null && !lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        lista.get(i).setID_FACTURA(idFactura);
                        DetalleFactura resultDF = detalleFacturaSessionBeanLocal.edit(lista.get(i));
                        Integer idDetalle = resultDF.getID_DETALLE();
                        if (idDetalle != null) {
                            lista.get(i).setID_DETALLE(idDetalle);
                        }
                    }
                    factura.setDetalleList(lista);
                }
            }
        }

        return factura;
    }

    @Override
    public Integer delete(Integer id) {
        System.out.println("FacturaSessionBean.delete:" + id);
        FacturaDao dao = new FacturaDao();
        return dao.delete(id);
    }

    @Override
    public Factura find(Integer id) {
        System.out.println("FacturaSessionBean.find:" + id);
        FacturaDao dao = new FacturaDao();
        return dao.find(id);
    }

    @Override
    public List<Factura> findAll() {
        System.out.println("FacturaSessionBean.findAll:");
        FacturaDao dao = new FacturaDao();
        return dao.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
