package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.HistorialMedico;

@Repository
public interface Historial_medicoRepository extends JpaRepository<HistorialMedico, Long> {

    @Query("SELECT h FROM HistorialMedico h " +
           "LEFT JOIN FETCH h.veterinario " +
           "LEFT JOIN FETCH h.cliente " +
           "LEFT JOIN FETCH h.mascota m LEFT JOIN FETCH m.cliente " +
           "LEFT JOIN FETCH h.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota cm LEFT JOIN FETCH cm.cliente")
    List<HistorialMedico> findAllWithRelations();

    @Query("SELECT h FROM HistorialMedico h " +
           "LEFT JOIN FETCH h.veterinario " +
           "LEFT JOIN FETCH h.cliente " +
           "LEFT JOIN FETCH h.mascota m LEFT JOIN FETCH m.cliente " +
           "LEFT JOIN FETCH h.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota cm LEFT JOIN FETCH cm.cliente " +
           "WHERE h.idHistorialMedico = :id")
    Optional<HistorialMedico> findByIdWithRelations(@Param("id") Long id);
}
