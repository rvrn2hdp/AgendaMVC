/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.domain;

/**
 *
 * @author ander
 */
public class Usuario {
    
    private int numero;
    private String nombre;
    private String clave;
    private Permiso permiso;

    public Usuario() {
    }

    public Usuario(int numero, String nombre, String clave, Permiso permiso) {
        this.numero = numero;
        this.nombre = nombre;
        this.clave = clave;
        this.permiso = permiso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (" + permiso.getPermiso() + ')';
    }
    
    
    
}
