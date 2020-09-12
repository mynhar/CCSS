/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.controller.resources;

import com.ccss.model.Producto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold
 */
public class ProductoDao extends ConexionJDBCOracle {

    public ProductoDao() {
        super();
    }
    
    public List<Producto> findAll() {

        List<Producto> lista = new ArrayList<Producto>();

        try {

            //step3 create the statement object  
            Statement stmt = con.createStatement();

            //step4 execute query
            String sql = "SELECT * FROM HAROLD.TBL_PRODUCTO order by ID_PRODUCTO desc";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Producto obj = new Producto();
                obj.setID_PRODUCTO(rs.getInt(1));
                obj.setNOM_PRODUCTO(rs.getString(2));
                obj.setCAN_PRODUCTO(rs.getInt(3));
                obj.setMON_PRODUCTO(rs.getBigDecimal(4));
                lista.add(obj);
            }

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Producto find(Integer id) {        
        Producto obj = null;
        try {

            String sql = "SELECT producto.* FROM HAROLD.TBL_PRODUCTO producto WHERE producto.ID_PRODUCTO = ?";

            //step3 create the statement object  
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //step4 execute query            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj = new Producto();
                obj.setID_PRODUCTO(rs.getInt(1));
                obj.setNOM_PRODUCTO(rs.getString(2));
                obj.setCAN_PRODUCTO(rs.getInt(3));
                obj.setMON_PRODUCTO(rs.getBigDecimal(4));
            }

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public Integer delete(Integer id_producto) {        
        try {

           CallableStatement cs = null;           
            cs = this.con.prepareCall("{call Harold.deleteProducto(?)}");
            cs.setInt(1, id_producto);
            
            cs.executeQuery();
            
            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;
    }

    /**
     * 
     * @param producto
     * @return 
     */
    public Producto create(Producto producto) {
        try {

            CallableStatement cs = null;           
            cs = this.con.prepareCall("{call Harold.insertProducto(?,?,?,?)}");
            cs.setString(1, producto.getNOM_PRODUCTO());
            cs.setInt(2, producto.getCAN_PRODUCTO());
            cs.setBigDecimal(3, producto.getMON_PRODUCTO());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.executeQuery();
            
            Integer id = cs.getInt(4);
            producto.setID_PRODUCTO(id);
            
            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return producto;
    }
    
    public Producto edit(Producto producto){        
        try {

            CallableStatement cs = null;            
            cs = this.con.prepareCall("{call Harold.updateProducto(?,?,?,?)}");
            cs.setString(1, producto.getNOM_PRODUCTO());
            cs.setInt(2, producto.getCAN_PRODUCTO());
            cs.setBigDecimal(3, producto.getMON_PRODUCTO());
            cs.setInt(4, producto.getID_PRODUCTO());
            cs.executeQuery();
            
            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return producto;
    
    }
}
