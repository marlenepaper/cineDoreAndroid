package com.binaryBuddies.cinedore.models;

public class ColorModel {

    private int id;
    private String color;

    public ColorModel(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }
    public String getColor() {
        return color;
    }
}
