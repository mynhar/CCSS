/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccss.dao;

import com.ccss.model.Factura;
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
public class FacturaDao extends ConexionJDBCOracle {

    public FacturaDao() {
        super();
    }

    /**
     * 
     * @return 
     */
    public List<Factura> findAll() {

        List<Factura> lista = new ArrayList<Factura>();

        try {

            //step3 create the statement object  
            Statement stmt = con.createStatement();

            //step4 execute query
            String sql = "SELECT * FROM HAROLD.TBL_FACTURA order by ID_FACTURA desc";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Factura obj = new Factura();
                obj.setID_FACTURA(rs.getInt(1));
                obj.setNOM_CLIENTE(rs.getString(2));
                obj.setFEC_FACTURA(rs.getDate(3));
                obj.setMON_IMPUESTO(rs.getBigDecimal(4));
                obj.setMON_TOTAL(rs.getBigDecimal(5));
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
    public Factura find(Integer id) {
        Factura obj = null;
        try {

            String sql = "SELECT factura.* FROM HAROLD.TBL_FACTURA factura WHERE factura.ID_FACTURA = ?";

            //step3 create the statement object  
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //step4 execute query            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj = new Factura();
                obj.setID_FACTURA(rs.getInt(1));
                obj.setNOM_CLIENTE(rs.getString(2));
                obj.setFEC_FACTURA(rs.getDate(3));
                obj.setMON_IMPUESTO(rs.getBigDecimal(4));
                obj.setMON_TOTAL(rs.getBigDecimal(5));
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
     * @param id_factura
     * @return 
     */
    public Integer delete(Integer id_factura) {
        try {

            CallableStatement cs = null;
            cs = this.con.prepareCall("{call Harold.deleteFactura(?)}");
            cs.setInt(1, id_factura);

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
     * @param factura
     * @return
     */
    public Factura create(Factura factura) {
        try {

            java.sql.Date FEC_FACTURA = new java.sql.Date(factura.getFEC_FACTURA().getTime());
            
            CallableStatement cs = null;
            cs = this.con.prepareCall("{call Harold.insertFactura(?,?,?,?,?)}");
            cs.setString(1, factura.getNOM_CLIENTE());
            cs.setDate(2, FEC_FACTURA);
            cs.setBigDecimal(3, factura.getMON_IMPUESTO());
            cs.setBigDecimal(4, factura.getMON_TOTAL());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.executeQuery();

            Integer id = cs.getInt(5);
            factura.setID_FACTURA(id);

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return factura;
    }

    /**
     * 
     * @param factura
     * @return 
     */
    public Factura edit(Factura factura) {
        try {
            java.sql.Date FEC_FACTURA = new java.sql.Date(factura.getFEC_FACTURA().getTime());

            CallableStatement cs = null;
            cs = this.con.prepareCall("{call Harold.updateFactura(?,?,?,?,?)}");
            cs.setString(1, factura.getNOM_CLIENTE());
            cs.setDate(2, FEC_FACTURA);
            cs.setBigDecimal(3, factura.getMON_IMPUESTO());
            cs.setBigDecimal(4, factura.getMON_TOTAL());
            cs.setInt(5, factura.getID_FACTURA());
            cs.executeQuery();

            //step5 close the connection object  
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return factura;

    }
}
