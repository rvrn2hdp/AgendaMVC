package com.analistas.AgendaMVC.controller;

import com.analistas.AgendaMVC.model.domain.Ciudad;
import com.analistas.AgendaMVC.model.domain.Contacto;
import com.analistas.AgendaMVC.model.domain.Provincia;
import com.analistas.AgendaMVC.model.repository.CiudadRepository;
import com.analistas.AgendaMVC.model.repository.ProvinciaRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String listarCiudad(Model model) {

        model.addAttribute("subtitulo", "Lista de Localidades");
        model.addAttribute("localidades", CiudadRepo.buscarTodos());

        return "localidades";
    }

    @RequestMapping(value = "/localidadform", method = RequestMethod.GET)
    public String nuevaCiudad(Model model) {

        model.addAttribute("subtitulo", "Nueva Localidad");
        model.addAttribute("localidades", new Ciudad());

        return "localidadform";
    }

    @RequestMapping(value = "/localidadform/{id}", method = RequestMethod.GET)
    public String editarCiudad(@PathVariable("id") int id, Model model) {

        Ciudad ciudad = (Ciudad) CiudadRepo.buscarPorId(id);

        model.addAttribute("subtitulo", "Editar Localidad");
        model.addAttribute("localidades", ciudad);

        return "localidadform";
    }

    @RequestMapping(value = "/localidadform", method = RequestMethod.POST)
    public String guardarCiudad(Ciudad ciudad, @RequestParam(name = "pro") int idPro, Model model) {

        if (ciudad.getCodigoPostal().isEmpty()) {
            model.addAttribute("subtitulo", "Corrija los errores");
            model.addAttribute("localidades", ProvinciaRepo.buscarTodos());
            model.addAttribute("errorCPa", true);
        }

        Provincia provincia = (Provincia) ProvinciaRepo.buscarPorId(idPro);
        ciudad.setProvincia(provincia);
        CiudadRepo.guardar(ciudad);

        return "redirect:/localidades";
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String borrarCiudad(@PathVariable("id") int id) {
        Ciudad ciudad = (Ciudad) CiudadRepo.buscarPorId(id);
        CiudadRepo.borrarPorId(id);
        
        return "redirect:/localidades";
    }
    
    @RequestMapping(value = "/buscarCiudad", method = RequestMethod.GET)
    public String buscarCiudad(@RequestParam(name = "criterio") String criterio, Model model) {

        model.addAttribute("subtitulo", !criterio.isEmpty()
                ? "Resultado de busqueda de \"" + criterio + "\""
                : "Lista de Localidades");
        model.addAttribute("localidades", CiudadRepo.buscarPor(criterio));

        return "localidades";
    }
    
    @ModelAttribute("provincias")
    public List<Provincia> getProvincias() {
        return (List<Provincia>) ProvinciaRepo.buscarTodos();
    }
}
