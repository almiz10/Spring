package com.egg.eggnews2.controladores;

import com.egg.eggnews2.entidades.Noticia;
import com.egg.eggnews2.excepciones.MiException;
import com.egg.eggnews2.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/noticia")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/")
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "noticia.html";
    }
    
    @GetMapping ("/crear")
    public String crearNoticia (String titulo, String cuerpo){
        return "noticias_form.html";
    }
    
    @PostMapping("/creacion")    
    public String creacion (@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo);
            
            modelo.put("exito", "El libro fue cargado correctamente");
            return "redirect:/";
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return ("noticias_form.html");
        }
        
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        // hay que crear el metodo getOne en los servicios
        modelo.put("noticiasmodificar", noticiaServicio.getOne(id));
        return "noticias_modificar.html";
    }    
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo, "");
            return "redirect:/";
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return("noticias_modificar.html");
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
      modelo.put ("eliminar", noticiaServicio.getOne(id));
        return "noticias_eliminar.html";
    }    
    
    @PostMapping("/eliminar/{id}")
    public String deliminar(@PathVariable String id, ModelMap modelo){
     try {
            noticiaServicio.eliminar(id);
            
            return "redirect:/";
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return("noticias_eliminar.html");
        }
    }
    
    
}
