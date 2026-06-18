package com.proyecto.veterinaria.Model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cita", indexes = {
    @Index(name = "idx_cita_cliente", columnList = "id_cliente"),
    @Index(name = "idx_cita_veterinario", columnList = "id_veterinario"),
    @Index(name = "idx_cita_recepcionista", columnList = "id_recepcionista"),
    @Index(name = "idx_cita_estado", columnList = "estado")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Agregamos "veterinario" al ToString para evitar bucles de Lombok
@ToString(exclude = {"cliente", "recepcionista", "veterinario", "factura"})
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "motivo", length = 500)
    private String motivo;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(length = 500)
    private String observacionCancelacion;

    @Column(length = 1000)
    private String diagnostico;

    @Column(length = 1000)
    private String tratamiento;

    // --- Relaciones ---

    /* Relación con el Cliente (Dueño de la mascota) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_cliente", nullable = false)
    private User cliente;

    /* Relación con el Recepcionista que asignó la cita */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_recepcionista", nullable = true)
    private User recepcionista;

    /* Veterinario asignado — nullable hasta que se asigne */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_veterinario", nullable = true)
    private User veterinario;

    /* Mascota asociada a la cita */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cliente", "veterinario"})
    @JoinColumn(name = "id_mascota", nullable = true)
    private Mascota mascota;

    /* Relación bidireccional con Factura */
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Factura factura;
}