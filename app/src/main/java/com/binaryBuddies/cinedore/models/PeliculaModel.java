package com.binaryBuddies.cinedore.models;

import java.util.List;

public class PeliculaModel {

    private String nombre;
    private String anio;
    private String duracion;
    private String sinopsis;
    private String imagenPoster;
    private String categoria;
    private String clasificacion;
    private String lenguaje;
    private String color;
    private List<FormatoModel> formatos;
    private List<FuncionModel> funciones;

    public PeliculaModel(
            String nombre,
            String anio,
            String duracion,
            String sinopsis,
            String imagenPoster,
            String categoria,
            String clasificacion,
            String lenguaje,
            String color,
            List<FormatoModel> formatos,
            List<FuncionModel> funciones
    ) {
        this.nombre = nombre;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.imagenPoster = imagenPoster;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.lenguaje = lenguaje;
        this.color = color;
        this.formatos = formatos;
        this.funciones = funciones;
    }

    public String getNombre() {
        return nombre;
    }
    public String getAnio() {
        return anio;
    }
    public String getDuracion() {
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

    public List<FormatoModel> getFormato() {
        return formatos;
    }
    public List<FuncionModel> getFunciones() {
        return funciones;
    }
}
