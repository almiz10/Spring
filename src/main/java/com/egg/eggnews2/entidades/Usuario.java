package com.egg.eggnews2.entidades;

import com.egg.eggnews2.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
 
    @Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator (name = "uuid", strategy= "uuid2")
    protected String id;
    protected String nombreUsuario;
    //private String email;
    protected String password;
    
    @Temporal(TemporalType.DATE)
    protected Date fechaAlta;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;

    public Usuario() {
    }

    public Usuario(String id, String nombreUsuario, String password, Date fechaAlta, Rol rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.fechaAlta = fechaAlta;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
