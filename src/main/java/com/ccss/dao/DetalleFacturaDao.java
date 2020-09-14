/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.dao;

import com.ccss.model.DetalleFactura;
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
public class DetalleFacturaDao extends ConexionJDBCOracle{
    
     public DetalleFacturaDao() {
        super();
    }

    /**
     * 
     * @return 
     */
    public List<DetalleFactura> findAll() {

        List<DetalleFactura> lista = new ArrayList<DetalleFactura>();

        try {

            //step3 create the statement object  
            Statement stmt = con.createStatement();

            //step4 execute query
            String sql = "SELECT * FROM HAROLD.TBL_DETALLE_FACTURA order by ID_DETALLE desc";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DetalleFactura obj = new DetalleFactura();
                obj.setID_DETALLE(rs.getInt(1));
                obj.setID_FACTURA(rs.getInt(2));
                obj.setID_PRODUCTO(rs.getInt(3));
                obj.setCAN_PRODUCTO(rs.getInt(4));
                obj.setMON_PRODUCTO(rs.getBigDecimal(5));
                lista.add(obj);
            }

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    /**
     * 
     * @param id
     * @return 
     */
    public DetalleFactura find(Integer id) {
        DetalleFactura obj = null;
        try {

            String sql = "SELECT detalle.* FROM HAROLD.TBL_DETALLE_FACTURA detalle WHERE detalle.ID_DETALLE = ?";

            //step3 create the statement object  
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //step4 execute query            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj = new DetalleFactura();
                obj.setID_DETALLE(rs.getInt(1));
                obj.setID_FACTURA(rs.getInt(2));
                obj.setID_PRODUCTO(rs.getInt(3));
                obj.setCAN_PRODUCTO(rs.getInt(4));
                obj.setMON_PRODUCTO(rs.getBigDecimal(5));
            }

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    /**
     * 
     * @param id_DetalleFactura
     * @return 
     */
    public Integer delete(Integer id_DetalleFactura) {
        String query = "{call Harold.deleteDetalleFactura(?)}";
        try {

            CallableStatement cs = null;
            cs = this.con.prepareCall(query);
            cs.setInt(1, id_DetalleFactura);

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
     * @param detalle
     * @return 
     */
    public DetalleFactura create(DetalleFactura detalle) {
        String query = "{call Harold.insertDetalleFactura(?,?,?,?,?)}";
        
        try {

            CallableStatement cs = null;
            cs = this.con.prepareCall(query);
            cs.setInt(1, detalle.getID_FACTURA());
            cs.setInt(2, detalle.getProducto().getID_PRODUCTO());
            cs.setInt(3, detalle.getCAN_PRODUCTO());
            cs.setBigDecimal(4, detalle.getMON_PRODUCTO());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.executeQuery();

            Integer id = cs.getInt(5);
            detalle.setID_DETALLE(id);
            System.out.println("ID_DETALLE: "+id);
            
            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return detalle;
    }

    /**
     * 
     * @param factura
     * @return 
     */
    public DetalleFactura edit(DetalleFactura detalle) {
        try {

            CallableStatement cs = null;
            cs = this.con.prepareCall("{call Harold.updateDetalleFactura(?,?,?,?,?)}");
            cs.setInt(1, detalle.getID_FACTURA());
            cs.setInt(2, detalle.getProducto().getID_PRODUCTO());
            cs.setInt(3, detalle.getCAN_PRODUCTO());
            cs.setBigDecimal(4, detalle.getMON_PRODUCTO());
            cs.setInt(5, detalle.getID_DETALLE());
            cs.executeQuery();

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return detalle;

    }
    
}
