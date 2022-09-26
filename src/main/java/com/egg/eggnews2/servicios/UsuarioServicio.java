package com.egg.eggnews2.servicios;

import com.egg.eggnews2.entidades.Usuario;
import com.egg.eggnews2.enumeraciones.Rol;
import com.egg.eggnews2.excepciones.MiException;
import com.egg.eggnews2.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{ //se implemeta la interfaz UserDetailsService
    //para autenticar
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void registrar (String nombre, String password, String password2) throws MiException{
        validar (nombre, password, password2);
        
        Usuario usuario = new Usuario();
         
        usuario.setNombreUsuario(nombre);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setFechaAlta(new Date());
        usuario.setRol(Rol.USER); // por defecto asignamos el rol de usuario para cada regsitro
        
        usuarioRepositorio.save(usuario); //persistimos en la base
        
        
        
    }
    
    private void validar (String nombre, String password, String password2) throws MiException{
        if (nombre.isEmpty()||nombre==null){
            throw new MiException("El nombre no puede estar vacio");
        }
        if (password.isEmpty()||password==null || password.length()<=5){
            throw new MiException("la contraseña no puede estar vacia o tener menos de 5 caracteres");
        }
        
        if (!password.equals(password2)){
            throw new MiException("las contraseñas no coinciden");
        }
    }

    @Override //es para autenticar
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
         Usuario usuario = usuarioRepositorio.buscarPorNombre(nombreUsuario);
         if (usuario != null){
             //vamos a transformarlo en un unsuario del dominio spring
             List<GrantedAuthority> permisos = new ArrayList();
             GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString()); //ROLE_USER
             permisos.add(p);
             
             ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);
             return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
         } else {
             return null;
         }
    }
}
