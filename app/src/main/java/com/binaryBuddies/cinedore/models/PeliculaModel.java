package com.binaryBuddies.cinedore.models;

public class PeliculaModel {

    private String nombre;
    private String anio;
    private String duracion;
    private String sinopsis;
    private String imagenPoster;
    private String categoria;
    private String clasificacion;
    private String formato;
    private String lenguaje;

    public PeliculaModel(String nombre, String anio, String duracion, String sinopsis, String imagenPoster,
                         String categoria, String clasificacion, String formato, String lenguaje) {
        this.nombre = nombre;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;  // Corregido el error tipográfico
        this.imagenPoster = imagenPoster;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.formato = formato;
        this.lenguaje = lenguaje;
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

    public String getFormato() {
        return formato;
    }

    public String getLenguaje() {
        return lenguaje;
    }
}
