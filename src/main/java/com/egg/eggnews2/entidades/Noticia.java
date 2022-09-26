package com.egg.eggnews2.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    @Id
    @GeneratedValue (generator = "uuid")
    @GenericGenerator (name = "uuid", strategy= "uuid2")
    private String id;
    private String titulo;
    private String cuerpo;
    private String foto;
    @Temporal(TemporalType.DATE)
    private Date alta; 
    
    @OneToOne
    private Periodista creador;

    public Noticia() {
    }

    public Periodista getCreador(){
        return creador;
    }    
    public void setCreador(Periodista creador){
     this.creador = creador;   
    }    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

        
}
