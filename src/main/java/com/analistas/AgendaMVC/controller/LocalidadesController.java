package com.analistas.AgendaMVC.controller;

import com.analistas.AgendaMVC.model.repository.CiudadRepository;
import com.analistas.AgendaMVC.model.repository.ProvinciaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("localidades")
public class LocalidadesController {
    
    ProvinciaRepository ProvinciaRepo = new ProvinciaRepository();
    CiudadRepository CiudadRepo = new CiudadRepository();
    
    //@RequestMapping(value = {"/localidades"}, method = RequestMethod.GET)
    @GetMapping("/localidades")
    public String listar(Model model) {
        
        model.addAttribute("subtitulo", "Lista de Localidades");
        //model.addAttribute("ciudades", model)
        
        return "localidades";
    }
}
