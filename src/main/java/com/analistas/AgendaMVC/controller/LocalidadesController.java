package com.analistas.AgendaMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
public class LocalidadesController {
    
    public String listar(Model model) {
        
        return "localidades";
    }
}
