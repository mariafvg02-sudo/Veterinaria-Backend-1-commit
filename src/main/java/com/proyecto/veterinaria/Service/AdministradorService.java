package com.proyecto.veterinaria.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Administrador;
import com.proyecto.veterinaria.Repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    // 🔹 Obtener todos (aunque solo habrá uno)
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    // 🔹 Obtener administrador por ID
    public Administrador obtenerPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    // 🔹 Crear administrador (SOLO UNO en el sistema)
    public Administrador guardar(Administrador administrador) {

        // Validar que solo exista uno
        if (administradorRepository.count() > 0) {
            throw new RuntimeException("Ya existe un administrador en el sistema");
        }

        // Validaciones básicas
        if (administrador.getNombre() == null || administrador.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (administrador.getCorreo() == null || administrador.getCorreo().isEmpty()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (administrador.getClave() == null || administrador.getClave().length() < 6) {
            throw new RuntimeException("La contraseña debe tener mínimo 6 caracteres");
        }

        return administradorRepository.save(administrador);
    }

    // 🔹 Actualizar administrador
    public Administrador actualizar(Long id, Administrador detalles) {

        Administrador admin = obtenerPorId(id);

        admin.setNombre(detalles.getNombre());
        admin.setDireccion(detalles.getDireccion());
        admin.setTelefono(detalles.getTelefono());
        admin.setCorreo(detalles.getCorreo());
        admin.setClave(detalles.getClave());

        return administradorRepository.save(admin);
    }

    // 🔹 NO permitir eliminar administrador
    public void eliminar(Long id) {
        throw new RuntimeException("No se permite eliminar el administrador del sistema");
    }
}