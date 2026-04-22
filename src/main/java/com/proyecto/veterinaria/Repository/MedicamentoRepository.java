package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    // Buscar un medicamento exacto por su nombre
    Optional<Medicamento> findByNombre(String nombre);

    // Listar medicamentos filtrados por tipo (ej: Antibiótico, Analgésico)
    List<Medicamento> findByTipo(String tipo);
}