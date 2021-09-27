/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Permiso;
import com.analistas.AgendaMVC.model.domain.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ander
 */
public class PermisoRepository implements ICrudRepository {
    
     String nombreBD = "agenda_2021";

    @Override
    public List<?> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> buscarPor(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorId(int id) {
        Permiso permiso = new Permiso();
        Connection cn;
        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from permisos where id = " + id;
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                permiso.setNumero(rs.getInt(1));
                permiso.setPermiso(rs.getString(2));
                permiso.setDescripcion(rs.getString(3));
            }

            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } 
     
        return permiso;
    }

    @Override
    public void guardar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
