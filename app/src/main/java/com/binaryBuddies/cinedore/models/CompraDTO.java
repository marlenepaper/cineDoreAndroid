package com.binaryBuddies.cinedore.models;

import java.math.BigDecimal;
import java.util.List;

public class CompraDTO {

    private Long usuarioId;
    private Long funcionId;
    private BigDecimal totalPago;
    private List<TicketEntradaDTO> tickets;

    public CompraDTO(Long usuarioId, Long funcionId, BigDecimal totalPago, List<TicketEntradaDTO> tickets) {
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.tickets = tickets;
    }

    public Long getUsuarioId() { return usuarioId; }
    public Long getFuncionId() { return funcionId; }
    public BigDecimal getTotalPago() { return totalPago; }
    public List<TicketEntradaDTO> getTickets() { return tickets; }
}
