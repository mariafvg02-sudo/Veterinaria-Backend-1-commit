package com.proyecto.veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
   // 🔹 Validar si ya existe un correo
    boolean existsByCorreo(String correo);
}