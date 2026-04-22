package com.proyecto.veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.HistorialMedico;

@Repository
public interface Historial_medicoRepository extends JpaRepository<HistorialMedico, Long> {
    // Métodos personalizados aquí
}
