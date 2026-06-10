package com.proyecto.veterinaria.Model;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historial_medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// ... getter, setter, etc
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_medico")
    private Long idHistorialMedico;

    @Column(name = "fecha")
    private LocalDate fecha;

    // CAMBIO 1: Usar columnDefinition = "TEXT" para que no haya límite de caracteres
    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnostico;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "tratamiento", columnDefinition = "TEXT")
    private String tratamiento;

    // CAMBIO 2: Agregar @JsonIgnoreProperties para evitar el error de serialización
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User veterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cita cita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMascota", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Mascota mascota;
}
