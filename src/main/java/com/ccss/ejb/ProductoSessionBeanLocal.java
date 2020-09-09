/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.ejb;

import com.ccss.model.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Harold
 */
@Local
public interface ProductoSessionBeanLocal {
         
    public Producto create(Producto producto);
    
    public Producto edit(Producto producto);
    
    public Integer delete(Integer id);
    
    public Producto find(Integer id);
    
    public List<Producto> findAll();    
    
}
