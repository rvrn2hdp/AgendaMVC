package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Ciudad;
import com.analistas.AgendaMVC.model.domain.Provincia;
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
            cn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ciudades;
    }

    @Override
    public List<?> buscarPor(String criterio) {
        List<Ciudad> ciudades = new ArrayList<>();

        try {
            cn = new ConexionJDBC().getConnection(nombreBD);
            //Insegura > String sql = "select * from ciudades where nom like '%" + criterio + "%' or cpa like '%" + criterio + "%'";
            String sql = "select * from ciudades where nom like ? or cpa like ?";

            PreparedStatement st = cn.prepareStatement(sql);

            //Preparar parámetros
            criterio = "%" + criterio + "%";
            st.setString(2, criterio);
            st.setString(1, criterio);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Provincia prv = (Provincia) provinciaRepo.buscarPorId(rs.getInt(4));
                Ciudad ciudad = new Ciudad();
                ciudad.setNumero(rs.getInt(1));
                ciudad.setCodigoPostal(rs.getString(2));
                ciudad.setNombre(rs.getString(3));
                ciudad.setProvincia(prv);

                ciudades.add(ciudad);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ciudades;
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
            cn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return ciudad;
    }

    @Override
    public void guardar(Object objeto) {
        try {
            Ciudad ciudad = (Ciudad) objeto;
            cn = new ConexionJDBC().getConnection(nombreBD);

            String sql = "insert into ciudades(cpa, nom, id_pro) values(?, ?, ?);";

            if (ciudad.getNumero() > 0) {
                sql = "update ciudades "
                        + "set cpa = ?, nom = ?, id_pro = ? where id = ?;";
            }

            PreparedStatement sentenciaPreparada = cn.prepareStatement(sql);

            sentenciaPreparada.setString(1, ciudad.getCodigoPostal());
            sentenciaPreparada.setString(2, ciudad.getNombre());
            sentenciaPreparada.setInt(3, ciudad.getProvincia().getNumero());
            if (ciudad.getNumero() > 0) {
                sentenciaPreparada.setInt(4, ciudad.getNumero());

            }

//            if (c.getNumero() > 0) {
//                ps.setInt(3, c.getNumero());
//            }
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
            String sql = "delete from ciudades where id = ?;";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
