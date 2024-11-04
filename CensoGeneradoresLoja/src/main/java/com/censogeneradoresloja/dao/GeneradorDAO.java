/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.censogeneradoresloja.dao;

/**
 *
 * @author david
 */
import com.censogeneradoresloja.models.Generador;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDAO {
    private final String ARCHIVO_GENERADORES = "generadores.json";
    private final ObjectMapper objectMapper;

    public GeneradorDAO() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Generador> obtenerTodos() throws IOException {
        File archivo = new File(ARCHIVO_GENERADORES);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        CollectionType listType = objectMapper.getTypeFactory()
            .constructCollectionType(ArrayList.class, Generador.class);
        return objectMapper.readValue(archivo, listType);
    }

    public void guardar(Generador generador) throws IOException {
        List<Generador> generadores = obtenerTodos();
        // Aseguramos que el ID sea único y autogenerado si no está asignado
        if (generador.getId() == null) {  // Si el ID es null, se asigna uno nuevo
            Long nuevoId = generadores.stream()
                .mapToLong(Generador::getId)
                .max()
                .orElse(0L) + 1;
            generador.setId(nuevoId);
        }
        // Eliminamos cualquier generador con el mismo ID antes de agregar el nuevo
        generadores.removeIf(g -> g.getId().equals(generador.getId()));
        generadores.add(generador);
        objectMapper.writeValue(new File(ARCHIVO_GENERADORES), generadores);
    }

    public void eliminar(Long id) throws IOException {
        List<Generador> generadores = obtenerTodos();
        // Removemos el generador que tenga el ID especificado
        generadores.removeIf(g -> g.getId().equals(id));
        objectMapper.writeValue(new File(ARCHIVO_GENERADORES), generadores);
    }
}
