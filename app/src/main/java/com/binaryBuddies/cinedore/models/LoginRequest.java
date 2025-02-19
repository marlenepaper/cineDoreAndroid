package com.binaryBuddies.cinedore.models;

public class LoginRequest {
    private String correoElectronico;
    private String contrasenia;

    public LoginRequest(String correoElectronico, String contrasenia) {
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }
}
