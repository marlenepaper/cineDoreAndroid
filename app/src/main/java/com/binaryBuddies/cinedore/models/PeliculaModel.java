package com.binaryBuddies.cinedore.models;

import java.util.List;

public class PeliculaModel {
    private int id;
    private String nombre;
    private int anio;
    private int duracion;
    private String sinopsis;
    private String imagenPoster;
    private String categoria;
    private String clasificacion;
    private String lenguaje;
    private String color;
    private String formato;
    private List<FuncionModel> funciones;

    public PeliculaModel(
            int id,
            String nombre,
            int anio,
            int duracion,
            String sinopsis,
            String imagenPoster,
            String categoria,
            String clasificacion,
            String lenguaje,
            String color,
            String formato,
            List<FuncionModel> funciones
    ) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.imagenPoster = imagenPoster;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.lenguaje = lenguaje;
        this.color = color;
        this.formato = formato;
        this.funciones = funciones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getImagenPoster() {
        return imagenPoster;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public String getColor() {
        return color;
    }

    public String getFormato() {
        return formato;
    }

    public List<FuncionModel> getFunciones() {
        return funciones;
    }
}
