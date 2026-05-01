package com.proyecto.veterinaria.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByNombre(String nombre);
    Optional<Veterinario> findByIdVeterinario(long idVeterinario);
    Optional<Veterinario> findByDocumentoIdentidad(long documentoIdentidad);
    boolean existsByCorreo(String correo);
}
