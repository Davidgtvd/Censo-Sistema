/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.views.dialogs;

/**
 *
 * @author david
 */

import com.censogeneradoresloja.controllers.GeneradorController;
import com.censogeneradoresloja.models.Generador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GeneradorDialog extends JDialog {
    private final GeneradorController generadorController;
    private final JTextField txtId;
    private final JTextField txtMarca;
    private final JTextField txtPotencia;
    private final JTextField txtConsumoCombustible;
    private final JTextField txtPrecio;
    private final JTextField txtUso;
    private boolean guardadoExitoso;
    private Generador generador;

    public GeneradorDialog(Window parent, GeneradorController generadorController) {
        super(parent, "Nuevo Generador", ModalityType.APPLICATION_MODAL);
        this.generadorController = generadorController;

        // Inicializar componentes
        txtId = new JTextField(20);
        txtMarca = new JTextField(20);
        txtPotencia = new JTextField(20);
        txtConsumoCombustible = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtUso = new JTextField(20);

        // Configurar el layout
        setLayout(new BorderLayout());

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID:"));
        panelCampos.add(txtId);
        panelCampos.add(new JLabel("Marca:"));
        panelCampos.add(txtMarca);
        panelCampos.add(new JLabel("Potencia (W):"));
        panelCampos.add(txtPotencia);
        panelCampos.add(new JLabel("Consumo Combustible (L/h):"));
        panelCampos.add(txtConsumoCombustible);
        panelCampos.add(new JLabel("Precio ($):"));
        panelCampos.add(txtPrecio);
        panelCampos.add(new JLabel("Uso:"));
        panelCampos.add(txtUso);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(this::guardarGenerador);
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al diálogo
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        guardadoExitoso = false;
    }

    private void guardarGenerador(ActionEvent e) {
        // Validar campos vacíos
        if (txtId.getText().trim().isEmpty() || txtMarca.getText().trim().isEmpty() || 
            txtPotencia.getText().trim().isEmpty() || txtConsumoCombustible.getText().trim().isEmpty() || 
            txtPrecio.getText().trim().isEmpty() || txtUso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Todos los campos son obligatorios.",
                "Error de validación",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String marca = txtMarca.getText().trim();
            double potencia = parseDouble(txtPotencia.getText().trim(), "Potencia");
            double consumo = parseDouble(txtConsumoCombustible.getText().trim(), "Consumo Combustible");
            double precio = parseDouble(txtPrecio.getText().trim(), "Precio");
            String uso = txtUso.getText().trim();

            // Crear y guardar el generador
            generador = new Generador(id, marca, precio, consumo, potencia, uso, ""); // Ajusta si tienes propietario
            generadorController.agregarGenerador(generador);

            guardadoExitoso = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese valores numéricos válidos.",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar el generador: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseDouble(String text, String fieldName) throws NumberFormatException {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El campo '" + fieldName + "' debe ser un número.");
        }
    }

    public boolean isGuardadoExitoso() {
        return guardadoExitoso;
    }

    public Generador getGenerador() {
        return generador;
    }
}