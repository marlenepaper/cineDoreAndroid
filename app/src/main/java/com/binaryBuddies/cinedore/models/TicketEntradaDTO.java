package com.binaryBuddies.cinedore.models;

public class TicketEntradaDTO {
    private String codigoQr;
    private Long estadoId;

    public TicketEntradaDTO(String codigoQr, Long estadoId) {
        this.codigoQr = codigoQr;
        this.estadoId = estadoId;
    }

    public String getCodigoQr() { return codigoQr; }
    public Long getEstadoId() { return estadoId; }
}
