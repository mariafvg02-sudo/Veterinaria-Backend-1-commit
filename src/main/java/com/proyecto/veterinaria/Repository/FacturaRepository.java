package com.proyecto.veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.veterinaria.Model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Métodos personalizados pueden ir aquí
}
