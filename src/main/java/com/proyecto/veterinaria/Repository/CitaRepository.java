package com.proyecto.veterinaria.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<Cita> findByFecha(LocalDateTime fecha);
    Optional<Cita> findByMotivo(String motivo);
    List<Cita> findByCliente_Id(Long clienteId);
    List<Cita> findByVeterinario_Id(Long veterinarioId);
}