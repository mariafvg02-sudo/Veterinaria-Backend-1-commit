package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import com.proyecto.veterinaria.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p " +
           "LEFT JOIN FETCH p.factura f " +
           "LEFT JOIN FETCH f.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota m LEFT JOIN FETCH m.cliente")
    List<Pago> findAllWithRelations();

    @Query("SELECT p FROM Pago p " +
           "LEFT JOIN FETCH p.factura f " +
           "LEFT JOIN FETCH f.cita c LEFT JOIN FETCH c.cliente " +
           "LEFT JOIN FETCH c.recepcionista LEFT JOIN FETCH c.veterinario " +
           "LEFT JOIN FETCH c.mascota m LEFT JOIN FETCH m.cliente " +
           "WHERE p.idPago = :id")
    Optional<Pago> findByIdWithRelations(@Param("id") Long id);
}