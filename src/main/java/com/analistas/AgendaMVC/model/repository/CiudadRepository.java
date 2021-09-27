package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Ciudad;
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
public class CiudadRepository implements ICrudRepository {

    String nombreBD = "agenda_2021";
    Connection cn;
    ProvinciaRepository provinciaRepo = new ProvinciaRepository();

    @Override
    public List<?> buscarTodos() {
        List<Ciudad> ciudades = new ArrayList<>();

        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from ciudades";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Provincia prv = (Provincia) provinciaRepo.buscarPorId(rs.getInt(4));
                Ciudad ciudad = new Ciudad();
                ciudad.setNumero(rs.getInt(1));
                ciudad.setCodigoPostal(rs.getString(2));
                ciudad.setNombre(rs.getString(3));
                ciudad.setProvincia(prv);

                ciudades.add(ciudad);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ciudades;
    }

    @Override
    public List<?> buscarPor(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorId(int id) {
        Ciudad ciudad = new Ciudad();

        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from ciudades where id = " + id;
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Provincia prv = (Provincia) provinciaRepo.buscarPorId(rs.getInt(4));

                ciudad.setNumero(id);
                ciudad.setCodigoPostal(rs.getString(2));
                ciudad.setNombre(rs.getString(3));
                ciudad.setProvincia(prv);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return ciudad;
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
