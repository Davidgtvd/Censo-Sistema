/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.controllers;

/**
 *
 * @author david
 */
import com.censogeneradoresloja.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    // Lista para almacenar los usuarios (temporal, en lugar de una base de datos)
    private List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
    }

    // Crear un nuevo usuario
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Obtener un usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(int id) {
        return usuarios.stream()
                       .filter(usuario -> usuario.getId() == id)
                       .findFirst();
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios);
    }

    // Actualizar un usuario existente
    public boolean actualizarUsuario(int id, Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getId() == id) {
                usuarios.set(i, usuarioActualizado);
                return true;
            }
        }
        return false;
    }

    // Eliminar un usuario por ID
    public boolean eliminarUsuario(int id) {
        return usuarios.removeIf(usuario -> usuario.getId() == id);
    }
}