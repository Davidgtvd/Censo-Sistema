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
import java.util.Objects;

public class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;  // Cambiado a Long para permitir valores nulos
    private String tipoOperacion; 
    private String descripcion;
    private LocalDateTime fecha;
    private int generadorId;
    private String usuarioId;

    // Constructor sin argumentos, inicializa la fecha a la fecha y hora actual
    public Transaccion() {
        this.fecha = LocalDateTime.now();
    }

    // Constructor con todos los argumentos excepto 'fecha', que se inicializa automáticamente
    public Transaccion(Long id, String tipoOperacion, String descripcion, int generadorId, String usuarioId) {
        this.id = id;
        setTipoOperacion(tipoOperacion);
        setDescripcion(descripcion);
        this.generadorId = generadorId;
        this.usuarioId = usuarioId;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(String tipoOperacion) {
        if (tipoOperacion == null || tipoOperacion.isBlank()) {
            throw new IllegalArgumentException("Tipo de operación no puede ser nulo o vacío.");
        }
        this.tipoOperacion = tipoOperacion;
    }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("Descripción no puede ser nula o vacía.");
        }
        this.descripcion = descripcion;
    }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = Objects.requireNonNullElse(fecha, LocalDateTime.now());
    }
    
    public int getGeneradorId() { return generadorId; }
    public void setGeneradorId(int generadorId) { this.generadorId = generadorId; }
    
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) {
        if (usuarioId == null || usuarioId.isBlank()) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo o vacío.");
        }
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", tipoOperacion='" + tipoOperacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", generadorId=" + generadorId +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}