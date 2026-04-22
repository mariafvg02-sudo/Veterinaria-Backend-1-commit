package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Administrador;
import com.proyecto.veterinaria.Repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    // Obtener todos los administradores
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    // Obtener administrador por ID
    public Optional<Administrador> obtenerPorId(Long id) {
        return administradorRepository.findById(id);
    }

    // Guardar o actualizar administrador
    public Administrador guardar(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Administrador actualizar(Long id, Administrador detalles) {
        return administradorRepository.findById(id).map(a -> {
            a.setNombre(detalles.getNombre());
            a.setDireccion(detalles.getDireccion());
            a.setTelefono(detalles.getTelefono());
            a.setCorreo(detalles.getCorreo());
            a.setClave(detalles.getClave());
            return administradorRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
    }

    // Eliminar administrador
    public void eliminar(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado");
        }
        administradorRepository.deleteById(id);
    }
}
