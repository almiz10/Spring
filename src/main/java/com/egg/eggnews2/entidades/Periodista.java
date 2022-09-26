package com.egg.eggnews2.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class Periodista extends Usuario {
    private ArrayList<Integer> cantidadNoticias;
    private Double sueldoMensual;

}
