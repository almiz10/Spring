package com.egg.eggnews2.repositorios;

import com.egg.eggnews2.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
    
    @Query("SELECT n FROM Noticia n where n.titulo =:titulo")
    public Noticia buscarPorTitulo (@Param ("titulo") String titulo);
}
