package com.proyecto.veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.veterinaria.Model.EntradaHistorial;

@Repository
public interface Entrada_HistorialRepository extends JpaRepository<EntradaHistorial, Long> {
}