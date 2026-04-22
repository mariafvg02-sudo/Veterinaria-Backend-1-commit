package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Recepcionista;
import com.proyecto.veterinaria.Repository.RecepcionistaRepository;

@Service
public class RecepcionistaService {
    
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    // Listar todos los recepcionistas
    public List<Recepcionista> listarTodos() {
        return recepcionistaRepository.findAll();
    }

    // Obtener un recepcionista por su ID
    public Optional<Recepcionista> obtenerPorId(Long id) {
        return recepcionistaRepository.findById(id);
    }

    // Crear un nuevo recepcionista
    public Recepcionista crearRecepcionista(Recepcionista recepcionista) {
        return recepcionistaRepository.save(recepcionista);
    }

    // Actualizar datos del recepcionista
    public Recepcionista actualizarRecepcionista(Long id, Recepcionista detalles) {
        return recepcionistaRepository.findById(id).map(r -> {
            r.setNombre(detalles.getNombre());
            r.setCorreo(detalles.getCorreo());
            r.setTelefono(detalles.getTelefono());
            r.setDocumentoIdentidad(detalles.getDocumentoIdentidad());
            return recepcionistaRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Recepcionista no encontrado con ID: " + id));
    }

    // Eliminar recepcionista
    public void eliminarRecepcionista(Long id) {
        if (!recepcionistaRepository.existsById(id)) {
            throw new RuntimeException("Recepcionista no encontrado");
        }
        recepcionistaRepository.deleteById(id);
    }
}