package com.egg.eggnews2.servicios;

import com.egg.eggnews2.entidades.Noticia;
import com.egg.eggnews2.excepciones.MiException;
import com.egg.eggnews2.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class NoticiaServicio {
   // permitir crear, modificar y eliminar noticias
@Autowired
private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
        validar (titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFoto("klk");
        
        noticia.setAlta(new Date());
        
        noticiaRepositorio.save(noticia);
    }
     public List<Noticia> listarNoticias(){
         List<Noticia> noticias = new ArrayList();
        
         noticias = noticiaRepositorio.findAll();
         return noticias;
    }
     
     @Transactional
     public void modificarNoticia(String id, String titulo, String cuerpo, String foto) throws MiException{
         
         validar (titulo, cuerpo);
         Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
         
         if (respuesta.isPresent()){
             Noticia noticia = respuesta.get();
             noticia.setTitulo(titulo);
             noticia.setCuerpo(cuerpo);
             noticia.setFoto(foto);
             
             noticiaRepositorio.save(noticia);
         }
     }
     
     public Noticia getOne(String id){
         return noticiaRepositorio.getOne(id);
     }
     
      @Transactional
     public void eliminar(String id) throws MiException{
              
             noticiaRepositorio.deleteById(id);
         
     }
     
     private void validar(String titulo, String cuerpo) throws MiException{
         if (titulo.isEmpty() || titulo == null){
             throw new MiException("El titulo no puede estar vacio");
         }
         if (cuerpo.isEmpty() || cuerpo == null){
             throw new MiException("El Cuerpo no puede estar vacio");
         }
     }
     
     
}
