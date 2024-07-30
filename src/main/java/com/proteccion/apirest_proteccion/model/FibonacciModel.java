package com.proteccion.apirest_proteccion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fibonacci")
public class FibonacciModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String HoraGeneracion;
    private String SerieCalcula;
    private String MensajePersonalizado;

    public FibonacciModel(String horaGeneracion, String serieCalcula, String mensajePersonalizado) {
        HoraGeneracion = horaGeneracion;
        SerieCalcula = serieCalcula;
        MensajePersonalizado = mensajePersonalizado;
    }

    public FibonacciModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraGeneracion() {
        return HoraGeneracion;
    }

    public void setHoraGeneracion(String horaGeneracion) {
        HoraGeneracion = horaGeneracion;
    }

    public String getSerieCalcula() {
        return SerieCalcula;
    }

    public void setSerieCalcula(String serieCalcula) {
        SerieCalcula = serieCalcula;
    }

    public String getMensajePersonalizado() {
        return MensajePersonalizado;
    }

    public void setMensajePersonalizado(String mensajePersonalizado) {
        MensajePersonalizado = mensajePersonalizado;
    }
}
