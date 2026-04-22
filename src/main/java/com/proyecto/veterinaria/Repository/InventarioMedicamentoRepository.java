package com.proyecto.veterinaria.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.InventarioMedicamento;

@Repository
public interface InventarioMedicamentoRepository extends JpaRepository<InventarioMedicamento, Long> {
    // Permite buscar existencias por categoría (Ej: Vacunas, Antibióticos)
    Optional<InventarioMedicamento> findByCategoria(String categoria);
}