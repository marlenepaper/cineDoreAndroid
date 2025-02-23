package com.binaryBuddies.cinedore.models;

import java.math.BigDecimal;
import java.util.List;

public class CompraDTO {
    private Long compraId;
    private Long usuarioId;
    private Long funcionId;
    private BigDecimal totalPago;
    private List<TicketEntradaDTO> tickets;

    public CompraDTO(Long compraId,Long usuarioId, Long funcionId, BigDecimal totalPago, List<TicketEntradaDTO> tickets) {
        this.compraId=compraId;
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.tickets = tickets;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getFuncionId() {
        return funcionId;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public List<TicketEntradaDTO> getTickets() {
        return tickets;
    }
}
