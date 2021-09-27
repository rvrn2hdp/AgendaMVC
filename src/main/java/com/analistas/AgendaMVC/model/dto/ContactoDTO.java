/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.AgendaMVC.model.dto;

/**
 *
 * @author ander
 */
public class ContactoDTO {
    
    private int numero;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private String notas;

    public ContactoDTO(int numero, String contacto, String telefono, String email, String direccion, String notas) {
        this.numero = numero;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.notas = notas;
    }

    public int getNumero() {
        return numero;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNotas() {
        return notas;
    }
    
    
}
