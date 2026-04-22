package com.proyecto.veterinaria.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "ent_hist_id")
    private Integer entHistId;

    @Column(name = "vet_id")
    private Integer vetId;

    @Column(name = "auxi_id")
    private Integer auxiId;
}
