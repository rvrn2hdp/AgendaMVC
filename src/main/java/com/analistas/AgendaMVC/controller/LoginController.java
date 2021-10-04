package com.analistas.AgendaMVC.controller;

import com.analistas.AgendaMVC.model.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pcc
 */



@Controller
public class LoginController {
    
    UsuarioRepository usuarioRepo = new UsuarioRepository();
    public static int logged = 0;
    
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping({"/", "/login"}) //Forma abreviada
    public String login() {
        
        return "login";
    }
    
    @GetMapping("/ingresar")
    public String ingresar(
            @RequestParam("nombre") String nombre,
            @RequestParam("clave") String clave,
            Model model) {
        if(nombre.isEmpty() || clave.isEmpty()) {
                model.addAttribute("error", "ingrese el usuario y la clave");
            } else {
            if(usuarioRepo.esValido(nombre, clave)){
                logged = 1;
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Usuario o clave incorrectos");
            }
        }
        
        return "login";
    }
}
