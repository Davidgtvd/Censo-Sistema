/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.controllers;

/**
 *
 * @author david
 */
import com.censogeneradoresloja.dao.TransaccionDAO;
import com.censogeneradoresloja.models.Transaccion;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstadisticasController {
    private final TransaccionDAO transaccionDAO;
    private GeneradorController generadorController;
    private UsuarioController usuarioController;

    // Constructor sin parámetros (existente)
    public EstadisticasController() {
        this.transaccionDAO = new TransaccionDAO();
    }

    // Nuevo constructor que recibe GeneradorController y UsuarioController
    public EstadisticasController(GeneradorController generadorController, UsuarioController usuarioController) {
        this.transaccionDAO = new TransaccionDAO();
        this.generadorController = generadorController;
        this.usuarioController = usuarioController;
    }

    public Map<String, Long> obtenerEstadisticasCompra() throws IOException {
        List<Transaccion> transacciones = transaccionDAO.obtenerTodas();
        
        return transacciones.stream()
            .filter(t -> "COMPRA".equals(t.getTipoOperacion()))
            .collect(Collectors.groupingBy(
                t -> t.getUsuario().getApellido(),
                Collectors.counting()
            ));
    }

    public double obtenerPromedioConsumo() throws IOException {
        List<Transaccion> transacciones = transaccionDAO.obtenerTodas();
        return transacciones.stream()
            .filter(t -> "COMPRA".equals(t.getTipoOperacion()))
            .mapToDouble(t -> t.getGenerador().getConsumoCombustible())
            .average()
            .orElse(0.0);
    }

    public double obtenerCostoTotalGeneradores() throws IOException {
        List<Transaccion> transacciones = transaccionDAO.obtenerTodas();
        return transacciones.stream()
            .filter(t -> "COMPRA".equals(t.getTipoOperacion()))
            .mapToDouble(Transaccion::getMontoTotal)
            .sum();
    }
}