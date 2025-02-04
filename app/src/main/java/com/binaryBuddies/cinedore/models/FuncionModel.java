package com.binaryBuddies.cinedore.models;

public class FuncionModel {
    private String fecha;
    private String hora;

    public FuncionModel(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
