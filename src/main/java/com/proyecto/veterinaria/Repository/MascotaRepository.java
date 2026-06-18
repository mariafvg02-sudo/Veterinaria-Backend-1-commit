package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    Optional<Mascota> findByNombre(String nombre);
    List<Mascota> findByCliente_Id(Long clienteId);

    @Query("SELECT m FROM Mascota m LEFT JOIN FETCH m.cliente")
    List<Mascota> findAllWithRelations();

    @Query("SELECT m FROM Mascota m LEFT JOIN FETCH m.cliente WHERE m.idMascota = :id")
    Optional<Mascota> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT m FROM Mascota m LEFT JOIN FETCH m.cliente WHERE m.cliente.id = :clienteId")
    List<Mascota> findByClienteIdWithRelations(@Param("clienteId") Long clienteId);
}