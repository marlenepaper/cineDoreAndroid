package com.binaryBuddies.cinedore.models;

public class FormatoModel {

    private String nombre;
    private String detalle;

    public FormatoModel(String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDetalle() {
        return detalle;
    }
}
