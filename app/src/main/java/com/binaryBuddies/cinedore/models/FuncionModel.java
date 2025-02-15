package com.binaryBuddies.cinedore.models;

import java.time.LocalDateTime;

public class FuncionModel {

    private LocalDateTime fechaHora;
    private String sala;

    public FuncionModel(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        this.sala = "";
    }

    public FuncionModel(LocalDateTime fechaHora, String sala) {
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getSala() {
        return sala;
    }
}
