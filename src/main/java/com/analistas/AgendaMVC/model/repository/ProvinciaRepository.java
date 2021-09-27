/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class ProvinciaRepository implements ICrudRepository {

    String nombreBD = "agenda_2021";
    Connection cn;

    @Override
    public List<?> buscarTodos() {

        List<Provincia> provincias = new ArrayList<>();

        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from provincias";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                provincia.setNumero(rs.getInt(1));
                provincia.setNombre(rs.getString(2));
                provincias.add(provincia);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return provincias;
    }

    @Override
    public List<?> buscarPor(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorId(int id) {
        Provincia provincia = new Provincia();

        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from provincias where id = " + id;
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                provincia.setNumero(rs.getInt(1));
                provincia.setNombre(rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return provincia;
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
