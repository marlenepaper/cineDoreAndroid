package com.binaryBuddies.cinedore.models;

import java.math.BigDecimal;
import java.util.List;

public class CompraModel {

    private Long usuarioId;
    private Long funcionId;
    private BigDecimal totalPago;
    private List<TicketEntradaModel> tickets;

    public CompraModel(Long usuarioId, Long funcionId, BigDecimal totalPago, List<TicketEntradaModel> tickets) {
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.tickets = tickets;
    }

    public Long getUsuarioId() { return usuarioId; }
    public Long getFuncionId() { return funcionId; }
    public BigDecimal getTotalPago() { return totalPago; }
    public List<TicketEntradaModel> getTickets() { return tickets; }
}
