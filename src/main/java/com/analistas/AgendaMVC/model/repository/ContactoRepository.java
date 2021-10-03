/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Ciudad;
import com.analistas.AgendaMVC.model.domain.Contacto;
import com.analistas.AgendaMVC.model.dto.ContactoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class ContactoRepository implements ICrudRepository {

    String nombreBD = "agenda_2021";
    Connection cn;
    CiudadRepository ciudadRepo = new CiudadRepository();

    @Override
    public List<?> buscarTodos() {

        List<ContactoDTO> contactos = new ArrayList<>();

        try {
            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from contactos";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //Por cada registro creo una instancia de ciudad
                //utilizadn el ID para la b+usqueda
                Ciudad ciudad = (Ciudad) ciudadRepo.buscarPorId(rs.getInt(8));

                //Crear una instancia de Contacto:
                ContactoDTO contacto = new ContactoDTO(
                        rs.getInt(1),
                        rs.getString(3) + " " + rs.getString(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6) + " - " + ciudad.getNombre() + " - " + ciudad.getProvincia().getNombre(),
                        //rs.getString(6) + " - " + ciudad.getNombre() + " / " + ciudad.getProvincia().getNombre(),
                        rs.getString(7)
                );

                //Una vez creado, lo guardamos en la lista
                contactos.add(contacto);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return contactos;
    }

    @Override
    public List<?> buscarPor(String criterio) {
        List<ContactoDTO> contactos = new ArrayList<>();

        try {
            cn = new ConexionJDBC().getConnection(nombreBD);
            //Insegura > String sql = "select * from contactos where nom like '%" + criterio + "%' or ape like '%" + criterio + "%'";
            String sql = "select * from contactos where nom like ? or ape like ?";

            PreparedStatement st = cn.prepareStatement(sql);

            //Preparar parámetros
            criterio = "%" + criterio + "%";
            st.setString(1, criterio);
            st.setString(2, criterio);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //Por cada registro creo una instancia de ciudad
                //utilizadn el ID para la b+usqueda
                Ciudad ciudad = (Ciudad) ciudadRepo.buscarPorId(rs.getInt(8));

                //Crear una instancia de Contacto:
                ContactoDTO contacto = new ContactoDTO(
                        rs.getInt(1),
                        rs.getString(3) + " " + rs.getString(2),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6) + " - " + ciudad.getNombre() + " - " + ciudad.getProvincia().getNombre(),
                        //rs.getString(6) + " - " + ciudad.getNombre() + " / " + ciudad.getProvincia().getNombre(),
                        rs.getString(7)
                );

                //Una vez creado, lo guardamos en la lista
                contactos.add(contacto);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return contactos;
    }

    @Override
    public Object buscarPorId(int id) {
        Contacto contacto = null;
        try {
            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from contactos where id = " + id;
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ciudad ciudad = (Ciudad) ciudadRepo.buscarPorId(rs.getInt(8));

                contacto = new Contacto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        ciudad
                );
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return contacto;
    }

    @Override
    public void guardar(Object objeto) {
        try {
            Contacto c = (Contacto) objeto;
            cn = new ConexionJDBC().getConnection(nombreBD);

            String sql = "insert into contactos (ape, nom, cel, email, dir, obs, id_ciu) values(?, ?, ?, ?, ?, ?, ?);";

            //Para modificaciones
            if (c.getNumero() > 0) {
                sql = "update contactos "
                        + "set ape = ?, nom = ?, cel = ?, email = ?, dir = ?, obs = ?, id_ciu = ? "
                        + "where id = ?;";
            }

            PreparedStatement sentenciaPreparada = cn.prepareStatement(sql);

            sentenciaPreparada.setString(1, c.getApellido());
            sentenciaPreparada.setString(2, c.getNombre());
            sentenciaPreparada.setString(3, c.getTelefono());
            sentenciaPreparada.setString(4, c.getEmail());
            sentenciaPreparada.setString(5, c.getDomicilio());
            sentenciaPreparada.setString(6, c.getNotas());
            sentenciaPreparada.setInt(7, c.getCiudad().getNumero());

            //Para modificaciones
            if (c.getNumero() > 0) {
                sentenciaPreparada.setInt(8, c.getNumero());
            }

            sentenciaPreparada.execute();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void borrarPorId(int id) {
        try {
            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "delete from contactos where id = ?;";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
