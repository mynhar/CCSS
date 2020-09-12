/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.controller.resources.ProductoDao;
import com.ccss.model.Producto;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Harold
 */
@Stateless
public class ProductoSessionBean implements ProductoSessionBeanLocal {

    @Override
    public Producto create(Producto producto) {
        ProductoDao dao = new ProductoDao();
        Producto obj = dao.create(producto);
        return obj;

    }

    @Override
    public Producto edit(Producto producto) {
        ProductoDao dao = new ProductoDao();
        Producto obj = dao.edit(producto);
        return obj;
    }

    @Override
    public Integer delete(Integer id) {
        ProductoDao dao = new ProductoDao();
        return dao.delete(id);
    }

    @Override
    public Producto find(Integer id) {        
        ProductoDao dao = new ProductoDao();
        return dao.find(id);
    }

    @Override
    public List<Producto> findAll() {
        ProductoDao dao = new ProductoDao();
        return dao.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
