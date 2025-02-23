package com.binaryBuddies.cinedore.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class TicketDisplayDTO implements Serializable {
    private Long funcionId;
    private BigDecimal totalPago;
    private String codigoQr;
    private String fechaFuncion;
    private String tituloPelicula;
    private String imagenPelicula;
    private String clasificacion;
    private String lenguaje;
    private int duracion;

    public TicketDisplayDTO() {}

    public TicketDisplayDTO(
            Long funcionId,
            BigDecimal totalPago,
            String codigoQr,
            String fechaFuncion,
            String tituloPelicula,
            String imagenPelicula,
            String clasificacion,
            String lenguaje,
            int duracion)
    {
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.codigoQr = codigoQr;
        this.fechaFuncion = fechaFuncion;
        this.tituloPelicula = tituloPelicula;
        this.imagenPelicula = imagenPelicula;
        this.clasificacion = clasificacion;
        this.lenguaje = lenguaje;
        this.duracion = duracion;
    }

    public Long getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(Long funcionId) {
        this.funcionId = funcionId;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public String getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(String fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public String getImagenPelicula() {
        return imagenPelicula;
    }

    public void setImagenPelicula(String imagenPelicula) {
        this.imagenPelicula = imagenPelicula;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
