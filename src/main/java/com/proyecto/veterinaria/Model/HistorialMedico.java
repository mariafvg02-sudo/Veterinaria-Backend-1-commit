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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hist_medi_id")
    private Long histMediId;

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
    @JoinColumn(name = "ent_hist_id")
    private EntradaHistorial entradaHistorial;

    /* Relación: Un historial médico está asociado a un veterinario */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Veterinario veterinario;

    @Column(name = "auxi_id")
    private Integer auxiId;
}
