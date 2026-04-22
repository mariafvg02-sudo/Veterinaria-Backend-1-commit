package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Veterinario;
import com.proyecto.veterinaria.Repository.VeterinarioRepository;

@Service
public class VeterinarioService {
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Listar todos los veterinarios
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    // Obtener un veterinario por su ID
    public Optional<Veterinario> obtenerPorId(Long id) {
        return veterinarioRepository.findById(id);
    }

    // Crear un nuevo veterinario (usado por el Administrador)
    public Veterinario crearVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    // Actualizar datos del veterinario
    public Veterinario actualizarVeterinario(Long id, Veterinario detalles) {
        return veterinarioRepository.findById(id).map(v -> {
            v.setNombre(detalles.getNombre());
            v.setCorreo(detalles.getCorreo());
            v.setTelefono(detalles.getTelefono());
            v.setEspecialidad(detalles.getEspecialidad());
            v.setDocumentoIdentidad(detalles.getDocumentoIdentidad());
            return veterinarioRepository.save(v);
        }).orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + id));
    }

    // Eliminar veterinario
    public void eliminarVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }
}
