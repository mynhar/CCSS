/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.model.Factura;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author Harold
 */
@Local
public interface FacturaSessionBeanLocal {

    public Factura create(Factura factura);

    public Factura edit(Factura factura);

    public Integer delete(Integer id);

    public Factura find(Integer id);

    public List<Factura> findAll();
}
