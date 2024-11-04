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
import java.time.LocalDateTime;
import java.util.List;

public class TransaccionController {
    private final TransaccionDAO transaccionDAO;

    public TransaccionController() {
        this.transaccionDAO = new TransaccionDAO();
    }

    public void registrarTransaccion(Transaccion transaccion) throws IOException {
        transaccion.setFecha(LocalDateTime.now());
        transaccionDAO.guardar(transaccion);
    }

    public List<Transaccion> obtenerHistorial() throws IOException {
        return transaccionDAO.obtenerTodas();
    }
}