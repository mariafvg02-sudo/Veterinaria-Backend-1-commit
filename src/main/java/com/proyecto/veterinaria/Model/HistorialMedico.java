package com.proyecto.veterinaria.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "historial_medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"entradaHistorial", "veterinario"})
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_medico")
    private Long idHistorialMedico;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "diagnostico", length = 400)
    private String diagnostico;

    @Column(name = "tratamiento", length = 400)
    private String tratamiento;

    @Column(name = "nombreVet", length = 30)
    private String nombreVet;

    /* Relación: Un historial médico pertenece a una entrada de historial */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrada_historial", nullable = false)
    private EntradaHistorial entradaHistorial;

    /* Relación: Un historial médico está asociado a un veterinario (usuario con rol VETERINARIO) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinario", nullable = false)
    private User veterinario;

}
