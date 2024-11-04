/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.models;

/**
 *
 * @author david
 */
import java.io.Serializable;
import java.time.LocalDateTime;

public class Generador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id; // Cambiado de int a Long
    private String marca; 
    private double precio;
    private double consumoPorHora; 
    private double capacidadGeneracion; 
    private String usoDestinado; 
    private String propietario;
    private LocalDateTime fechaRegistro;

    public Generador() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Generador(Long id, String marca, double precio, double consumoPorHora, 
                     double capacidadGeneracion, String usoDestinado, String propietario) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.consumoPorHora = consumoPorHora;
        this.capacidadGeneracion = capacidadGeneracion;
        this.usoDestinado = usoDestinado;
        this.propietario = propietario;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public double getConsumoPorHora() { return consumoPorHora; }
    public void setConsumoPorHora(double consumoPorHora) { this.consumoPorHora = consumoPorHora; }
    
    public double getCapacidadGeneracion() { return capacidadGeneracion; }
    public void setCapacidadGeneracion(double capacidadGeneracion) { this.capacidadGeneracion = capacidadGeneracion; }
    
    public String getUsoDestinado() { return usoDestinado; }
    public void setUsoDestinado(String usoDestinado) { this.usoDestinado = usoDestinado; }
    
    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return "Generador{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", consumoPorHora=" + consumoPorHora +
                ", capacidadGeneracion=" + capacidadGeneracion +
                ", usoDestinado='" + usoDestinado + '\'' +
                ", propietario='" + propietario + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}