package com.binaryBuddies.cinedore.models;

public class AuthResponse {
    private String token;
    private String correoElectronico;
    private String nombre;

    public AuthResponse(String token, String correoElectronico, String nombre) {
        this.token = token;
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

