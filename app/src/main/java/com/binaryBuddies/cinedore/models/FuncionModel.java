package com.binaryBuddies.cinedore.models;

import java.time.LocalDateTime;
import java.util.List;

public class FuncionModel {

    private LocalDateTime fechaHora;
    private SalaModel sala;


    public FuncionModel(LocalDateTime fechaHora, SalaModel sala) {
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public SalaModel getSala() {
        return sala;
    }
}
