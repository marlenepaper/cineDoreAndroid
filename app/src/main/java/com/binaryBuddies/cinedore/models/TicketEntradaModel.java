package com.binaryBuddies.cinedore.models;

public class TicketEntradaModel {
    private String codigoQr;
    private Long estadoId;

    public TicketEntradaModel(String codigoQr, Long estadoId) {
        this.codigoQr = codigoQr;
        this.estadoId = estadoId;
    }

    public String getCodigoQr() { return codigoQr; }
    public Long getEstadoId() { return estadoId; }
}
