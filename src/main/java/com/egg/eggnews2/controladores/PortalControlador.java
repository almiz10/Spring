package com.egg.eggnews2.controladores;

import com.egg.eggnews2.entidades.Noticia;
import com.egg.eggnews2.excepciones.MiException;
import com.egg.eggnews2.servicios.NoticiaServicio;
import com.egg.eggnews2.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index() {
        
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String password, @RequestParam String password2,
            ModelMap modelo) {
        try {
            usuarioServicio.registrar(nombre, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");

            return "redirect:/noticia/";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre); // por si hay un error, nos traiga el valo con el th:value
            return "registro.html";

        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required=false) String error, ModelMap modelo) {
        if (error != null){
            modelo.put("error", "Usuario o contraseña invalida");
        }
        return "login.html";
    }
    
  
}
