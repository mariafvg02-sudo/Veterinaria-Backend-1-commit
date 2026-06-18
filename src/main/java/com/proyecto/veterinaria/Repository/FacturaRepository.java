package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("SELECT f FROM Factura f " +
           "LEFT JOIN FETCH f.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota m LEFT JOIN FETCH m.cliente")
    List<Factura> findAllWithRelations();

    @Query("SELECT f FROM Factura f " +
           "LEFT JOIN FETCH f.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota m LEFT JOIN FETCH m.cliente " +
           "WHERE f.idFactura = :id")
    Optional<Factura> findByIdWithRelations(@Param("id") Long id);
}
