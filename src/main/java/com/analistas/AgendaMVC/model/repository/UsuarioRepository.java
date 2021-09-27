/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.repository;

import com.analistas.AgendaMVC.jdbc.ConexionJDBC;
import com.analistas.AgendaMVC.model.domain.Permiso;
import com.analistas.AgendaMVC.model.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ander
 */
public class UsuarioRepository implements ICrudRepository {

    String nombreBD = "agenda_2021";
    public Usuario usuario;

    PermisoRepository permisoRepo = new PermisoRepository();

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
      ===============================================
                       LOGIN de USUARIO
      ===============================================
     */
    public boolean esValido(String nombre, String clave) {
        boolean valido = false;

        usuario = new Usuario();
        Connection cn;
        try {

            cn = new ConexionJDBC().getConnection(nombreBD);
            String sql = "select * from usuarios where nombre = ? and clave = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuario.setNumero(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setPermiso((Permiso) permisoRepo.buscarPorId(rs.getInt(4)));

                valido = true;
            }

            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return valido;
    }

}
