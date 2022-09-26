package com.egg.eggnews2;

import com.egg.eggnews2.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{
    
    @Autowired
    public UsuarioServicio usuarioServicio;
    
    @Autowired //usamos esto para encriptar los passord
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                        .antMatchers("/css/*","/js/*","/img/*","/**")
                        .permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/logincheck") //para procesar el inicio de sesion, debe considerar con el Action form de logueo
                    .usernameParameter("nombre")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/noticia/")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
}
