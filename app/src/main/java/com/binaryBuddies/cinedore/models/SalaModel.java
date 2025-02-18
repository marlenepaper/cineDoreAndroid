package com.binaryBuddies.cinedore.models;

public class SalaModel {
    private int id;
    private String nombre;

    public SalaModel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
