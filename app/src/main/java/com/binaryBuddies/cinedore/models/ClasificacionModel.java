package com.binaryBuddies.cinedore.models;

public class ClasificacionModel {

    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ClasificacionModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;

    }
}
