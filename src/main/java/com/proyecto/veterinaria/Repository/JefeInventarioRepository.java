package com.proyecto.veterinaria.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.JefeInventario;

@Repository
public interface JefeInventarioRepository extends JpaRepository<JefeInventario, Long> {
    // Puedes agregar métodos de búsqueda personalizados aquí si es necesario
    Optional<JefeInventario> findByDocumentoIdentidad(long documentoIdentidad);
    Optional<JefeInventario> findByCorreo(String correo);
}