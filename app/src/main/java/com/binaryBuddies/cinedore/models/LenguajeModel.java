package com.binaryBuddies.cinedore.models;

public class LenguajeModel {

    private String nombre;
    private String descripcion;

    public LenguajeModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
