/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.views.panels;

import com.censogeneradoresloja.controllers.GeneradorController;
import com.censogeneradoresloja.controllers.TransaccionController;
import com.censogeneradoresloja.models.Generador;
import com.censogeneradoresloja.views.dialogs.GeneradorDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class RegistroPanel extends JPanel {
    private final GeneradorController generadorController;
    private final TransaccionController transaccionController;
    private final JTable generadorTable;
    private final DefaultListModel<Generador> generadorListModel;

    public RegistroPanel(GeneradorController generadorController, TransaccionController transaccionController) {
        this.generadorController = generadorController;
        this.transaccionController = transaccionController;
        this.setLayout(new BorderLayout());
        this.generadorListModel = new DefaultListModel<>();

        // Crear tabla de generadores con DefaultTableModel
        String[] columnas = {"ID", "Marca", "Modelo", "Potencia (kW)", "Consumo (L/h)", "Precio", "Uso Principal"};
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);
        generadorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(generadorTable);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton nuevoButton = new JButton("Nuevo Generador");
        JButton editarButton = new JButton("Editar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton registrarCompraButton = new JButton("Registrar Compra");

        buttonPanel.add(nuevoButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(registrarCompraButton);

        // Agregar componentes
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurar eventos
        nuevoButton.addActionListener(e -> mostrarDialogoGenerador(null));
        editarButton.addActionListener(e -> editarGeneradorSeleccionado());
        eliminarButton.addActionListener(e -> eliminarGeneradorSeleccionado());
        registrarCompraButton.addActionListener(e -> registrarCompra());

        // Cargar datos iniciales
        cargarGeneradores();
    }

    private void cargarGeneradores() {
        try {
            var generadores = generadorController.obtenerTodosGeneradores();
            actualizarTablaGeneradores(generadores);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar generadores: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDialogoGenerador(Generador generador) {
        GeneradorDialog dialog = new GeneradorDialog(SwingUtilities.getWindowAncestor(this), generadorController);
        dialog.setVisible(true); // Muestra el diálogo

        // Usar isGuardadoExitoso para verificar si el guardado fue exitoso
        if (dialog.isGuardadoExitoso()) {
            cargarGeneradores(); // Recargar los generadores si se guardó uno nuevo
        }
    }

    private void editarGeneradorSeleccionado() {
        int row = generadorTable.getSelectedRow();
        if (row >= 0) {
            String id = generadorTable.getValueAt(row, 0).toString(); // Obtener el ID del generador seleccionado
            // Implementar lógica para editar generador usando el ID
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un generador para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarGeneradorSeleccionado() {
        int row = generadorTable.getSelectedRow();
        if (row >= 0) {
            String id = generadorTable.getValueAt(row, 0).toString(); // Obtener el ID del generador seleccionado
            // Implementar lógica para confirmar y eliminar el generador usando el ID
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un generador para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void registrarCompra() {
        int row = generadorTable.getSelectedRow();
        if (row >= 0) {
            String id = generadorTable.getValueAt(row, 0).toString(); // Obtener el ID del generador seleccionado
            // Implementar lógica para registrar la compra usando el ID
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un generador para registrar la compra.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTablaGeneradores(java.util.List<Generador> generadores) {
        DefaultTableModel model = (DefaultTableModel) generadorTable.getModel();
        model.setRowCount(0); // Limpiar la tabla
        for (Generador generador : generadores) {
            Object[] row = new Object[]{
                generador.getId(),
                generador.getMarca(),
                generador.getModelo(),
                generador.getPotencia(),
                generador.getConsumo(),
                generador.getPrecio(),
                generador.getUsoPrincipal()
            };
            model.addRow(row);
        }
    }
}