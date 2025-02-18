package com.binaryBuddies.cinedore.models;

public class CategoriaModel {

    private int id;
    private String categoria;

    public CategoriaModel(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getCategoria() {
        return categoria;
    }
}
