package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.JefeInventario;
import com.proyecto.veterinaria.Repository.JefeInventarioRepository;

@Service
public class JefeInventarioService {

    @Autowired
    private JefeInventarioRepository jefeInventarioRepository;

    // Listar todos los jefes de inventario
    public List<JefeInventario> listarTodos() {
        return jefeInventarioRepository.findAll();
    }

    // Obtener un jefe de inventario por su ID
    public Optional<JefeInventario> obtenerPorId(Long id) {
        return jefeInventarioRepository.findById(id);
    }

    // Crear un nuevo jefe de inventario
    public JefeInventario crearJefeInventario(JefeInventario jefe) {
        return jefeInventarioRepository.save(jefe);
    }

    // Actualizar un jefe de inventario existente
    public JefeInventario actualizarJefeInventario(Long id, JefeInventario detalles) {
        return jefeInventarioRepository.findById(id).map(j -> {
            j.setNombre(detalles.getNombre());
            j.setCorreo(detalles.getCorreo());
            j.setTelefono(detalles.getTelefono());
            j.setDocumentoIdentidad(detalles.getDocumentoIdentidad());
            // Solo actualiza la clave si se proporciona en los detalles, o si se desea permitir su actualización
            // Si la clave no debe actualizarse a través de este método, se podría omitir esta línea o añadir una verificación.
            // Por ahora, la mantenemos para reflejar la actualización completa.
            j.setClave(detalles.getClave()); 
            return jefeInventarioRepository.save(j);
        }).orElseThrow(() -> new RuntimeException("Jefe de Inventario no encontrado con ID: " + id));
    }

    // Eliminar un jefe de inventario
    public void eliminarJefeInventario(Long id) {
        if (!jefeInventarioRepository.existsById(id)) {
            throw new RuntimeException("Jefe de Inventario no encontrado");
        }
        jefeInventarioRepository.deleteById(id);
    }
}