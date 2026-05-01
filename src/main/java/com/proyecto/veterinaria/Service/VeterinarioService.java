package com.proyecto.veterinaria.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Veterinario;
import com.proyecto.veterinaria.Repository.VeterinarioRepository;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Obtener todos
    public List<Veterinario> obtenerTodos() {
        return veterinarioRepository.findAll();
    }

    // Obtener por ID
    public Veterinario obtenerPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    // Guardar (lo crea el admin)
    public Veterinario guardar(Veterinario veterinario) {C

        if (veterinario.getNombre() == null || veterinario.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (veterinarioRepository.existsByCorreo(veterinario.getCorreo())) {
            throw new RuntimeException("Correo ya registrado");
        }

        return veterinarioRepository.save(veterinario);
    }

    // Actualizar
    public Veterinario actualizar(Long id, Veterinario detalles) {
        Veterinario v = obtenerPorId(id);

        v.setNombre(detalles.getNombre());
        v.setCorreo(detalles.getCorreo());
        v.setTelefono(detalles.getTelefono());

        return veterinarioRepository.save(v);
    }

    // Eliminar
     public void eliminar(Long id) {
        throw new RuntimeException("No se permite eliminar el veterinario del sistema");
    }
}