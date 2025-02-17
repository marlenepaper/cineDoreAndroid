package com.binaryBuddies.cinedore.models;

import java.util.List;

public class PeliculaModel {

    private String nombre;
    private String anio;
    private String duracion;
    private String sinopsis;
    private String imagenPoster;
    private List<CategoriaModel> categorias;
    private List<ClasificacionModel> clasificaciones;
    private List<LenguajeModel> lenguajes;
    private List<ColorModel> colores;
    private List<FormatoModel> formatos;
    private List<FuncionModel> funciones;

    public PeliculaModel(
            String nombre,
            String anio,
            String duracion,
            String sinopsis,
            String imagenPoster,
            List<CategoriaModel> categorias,
            List<ClasificacionModel> clasificaciones,
            List<LenguajeModel> lenguajes,
            List<ColorModel> colores,
            List<FormatoModel> formatos,
            List<FuncionModel> funciones
    ) {
        this.nombre = nombre;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.imagenPoster = imagenPoster;
        this.categorias = categorias;
        this.clasificaciones = clasificaciones;
        this.lenguajes = lenguajes;
        this.colores = colores;
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


    public List<CategoriaModel> getCategoria() {
        return categorias;
    }
    public List<ClasificacionModel> getClasificacion() {
        return clasificaciones;
    }
    public List<LenguajeModel> getLenguaje() {
        return lenguajes;
    }
    public List<ColorModel> getColor() {
        return colores;
    }

    public List<FormatoModel> getFormato() {
        return formatos;
    }
    public List<FuncionModel> getFunciones() {
        return funciones;
    }
}
