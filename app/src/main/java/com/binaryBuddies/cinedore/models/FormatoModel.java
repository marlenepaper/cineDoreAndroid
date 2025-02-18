package com.binaryBuddies.cinedore.models;

public class FormatoModel {

    private int id;
    private String nombre;
    private String detalle;

    public FormatoModel(int id, String nombre, String detalle) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDetalle() {
        return detalle;
    }
}
