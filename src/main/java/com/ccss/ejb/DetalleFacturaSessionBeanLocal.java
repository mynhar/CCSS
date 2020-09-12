/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.model.DetalleFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Harold
 */
@Local
public interface DetalleFacturaSessionBeanLocal {

    public DetalleFactura create(DetalleFactura detalleFactura);

    public DetalleFactura edit(DetalleFactura detalleFactura);

    public Integer delete(Integer id);

    public DetalleFactura find(Integer id);

    public List<DetalleFactura> findAll();
}
