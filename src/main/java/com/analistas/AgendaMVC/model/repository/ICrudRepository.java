/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.repository;

import java.util.List;

/**
 *
 * @author ander
 */
public interface ICrudRepository {
    
    public List<?> buscarTodos();
    
    public List<?> buscarPor(String criterio);
    
    public Object buscarPorId(int id);
    
    public void guardar(Object objeto);
    
    public void borrarPorId(int id);
    
}
