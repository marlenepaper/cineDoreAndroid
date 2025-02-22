package com.binaryBuddies.cinedore.models;

import java.time.LocalDateTime;
import java.util.List;

public class FuncionModel {
    private Long id;
    private String fechaHora;
    private String sala;

    public FuncionModel(Long id, String fechaHora, String sala) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getSala() {
        return sala;
    }
}
