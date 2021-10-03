/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.agendamvc.controller;

import com.analistas.AgendaMVC.model.domain.Ciudad;
import com.analistas.AgendaMVC.model.domain.Contacto;
import com.analistas.AgendaMVC.model.repository.CiudadRepository;
import com.analistas.AgendaMVC.model.repository.ContactoRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author ander
 */
@Controller
@SessionAttributes("contacto")
public class ContactoController {

    ContactoRepository contactoRepo = new ContactoRepository();
    CiudadRepository ciudadRepo = new CiudadRepository();

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String listar(Model model) {

        model.addAttribute("subtitulo", "Lista de Contactos");
        model.addAttribute("contactos", contactoRepo.buscarTodos());

        return "home";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String nuevo(Model model) {

        model.addAttribute("subtitulo", "Nuevo Contacto");
        model.addAttribute("contacto", new Contacto());

        return "form";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") int id, Model model) {

        Contacto contacto = (Contacto) contactoRepo.buscarPorId(id);

        model.addAttribute("subtitulo", "Editar Contacto");
        model.addAttribute("contacto", contacto);

        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(Contacto contacto, @RequestParam(name = "ciu") int idCiu, Model model) {

        if (contacto.getApellido().isEmpty()) {
            model.addAttribute("subtitulo", "Corrija los errores");
            model.addAttribute("localidades", ciudadRepo.buscarTodos());
            model.addAttribute("errorApe", true);
        }

        Ciudad localidad = (Ciudad) ciudadRepo.buscarPorId(idCiu);
        contacto.setCiudad(localidad);
        contactoRepo.guardar(contacto);

        return "redirect:/home";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String borrar(@PathVariable("id") int id) {

        Contacto contacto = (Contacto) contactoRepo.buscarPorId(id);
        contactoRepo.borrarPorId(id);

        return "redirect:/home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String buscar(@RequestParam(name = "criterio") String criterio, Model model) {

        model.addAttribute("subtitulo", !criterio.isEmpty()
                ? "Resultado de busqueda de \"" + criterio + "\""
                : "Lista de Contactos");
        model.addAttribute("contactos", contactoRepo.buscarPor(criterio));

        return "home";
    }
    
    @ModelAttribute("localidades")
    public List<Ciudad> getLocalidades() {
        return (List<Ciudad>) ciudadRepo.buscarTodos();
    }
}
