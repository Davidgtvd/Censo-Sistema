/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.views.dialogs;

/**
 *
 * @author david
 */
import com.censogeneradoresloja.models.Generador;
import com.censogeneradoresloja.models.Usuario;
import javax.swing.*;
import java.awt.*;

public class GeneradorCompraDialog extends JDialog {
    private final JTextField nombreField;
    private final JTextField apellidoField;
    private final JTextField direccionField;
    private final JTextField telefonoField;
    private final JTextField emailField;
    private boolean aceptado;
    private final Generador generador;
    private Usuario usuario;

    public GeneradorCompraDialog(Window owner, Generador generador) {
        super(owner, "Registrar Compra", ModalityType.APPLICATION_MODAL);
        this.generador = generador;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Crear campos
        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        emailField = new JTextField(20);

        // Agregar campos
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        add(nombreField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        add(apellidoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        add(direccionField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        add(telefonoField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Información del generador
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(new JLabel("Generador: " + generador.getMarca() + " " + generador.getModelo()), gbc);
        gbc.gridy = 6;
        add(new JLabel("Precio: $" + generador.getPrecio()), gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton aceptarButton = new JButton("Registrar Compra");
        JButton cancelarButton = new JButton("Cancelar");

        aceptarButton.addActionListener(e -> aceptar());
        cancelarButton.addActionListener(e -> cancelar());

        buttonPanel.add(aceptarButton);
        buttonPanel.add(cancelarButton);

        gbc.gridy = 7;
        add(buttonPanel, gbc);

        pack();
        setLocationRelativeTo(owner);
    }

    private void aceptar() {
        try {
            // Validar campos
            if (nombreField.getText().trim().isEmpty() ||
                apellidoField.getText().trim().isEmpty() ||
                direccionField.getText().trim().isEmpty() ||
                telefonoField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son requeridos");
            }

            // Crear usuario
            usuario = new Usuario();
            usuario.setNombre(nombreField.getText().trim());
            usuario.setApellido(apellidoField.getText().trim());
            usuario.setDireccion(direccionField.getText().trim());
            usuario.setTelefono(telefonoField.getText().trim());
            usuario.setEmail(emailField.getText().trim());

            aceptado = true;
            dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar() {
        aceptado = false;
        dispose();
    }

    public boolean mostrar() {
        setVisible(true);
        return aceptado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Generador getGenerador() {
        return generador;
    }
}


