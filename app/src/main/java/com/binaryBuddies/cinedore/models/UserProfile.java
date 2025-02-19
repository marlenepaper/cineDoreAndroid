package com.binaryBuddies.cinedore.models;

public class UserProfile {
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String telefono;

    public UserProfile(String nombre, String apellidos, String correoElectronico, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }
}
