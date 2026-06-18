package com.proyecto.veterinaria.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<Cita> findByFecha(LocalDateTime fecha);
    Optional<Cita> findByMotivo(String motivo);

    @Query("SELECT c FROM Cita c LEFT JOIN FETCH c.cliente LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario LEFT JOIN FETCH c.mascota")
    List<Cita> findAllWithRelations();

    @Query("SELECT c FROM Cita c LEFT JOIN FETCH c.cliente LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario LEFT JOIN FETCH c.mascota WHERE c.cliente.id = :clienteId")
    List<Cita> findByClienteIdWithRelations(@Param("clienteId") Long clienteId);

    @Query("SELECT c FROM Cita c LEFT JOIN FETCH c.cliente LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario LEFT JOIN FETCH c.mascota WHERE c.veterinario.id = :veterinarioId")
    List<Cita> findByVeterinarioIdWithRelations(@Param("veterinarioId") Long veterinarioId);

    @Query("SELECT c FROM Cita c LEFT JOIN FETCH c.cliente LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario LEFT JOIN FETCH c.mascota WHERE c.idCita = :id")
    Optional<Cita> findByIdWithRelations(@Param("id") Long id);

    List<Cita> findByCliente_Id(Long clienteId);
    List<Cita> findByVeterinario_Id(Long veterinarioId);
}