package com.binaryBuddies.cinedore.models;

import java.time.LocalDateTime;
import java.util.List;

public class FuncionModel {

    private String fechaHora;
    private String sala;

    public FuncionModel(String fechaHora, String sala) {
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getSala() {
        return sala;
    }
}
