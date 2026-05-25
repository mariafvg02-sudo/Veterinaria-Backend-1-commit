package com.proyecto.veterinaria.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "entrada_historial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"veterinario", "historiales"})
public class EntradaHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada_historial")
    private Long idEntradaHistorial;

    private LocalDate fecha;

    private String diagnostico;

    private String tratamiento;

    private String nombre;

    private String motivo;

    private String sintomas;

    private String observaciones;

    /* Relación: Muchas entradas de historial son registradas por un veterinario (usuario con rol VETERINARIO) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinario", nullable = false)
    private User veterinario;

    /* Relación bidireccional: Una entrada puede generar registros en el historial médico */
    @OneToMany(mappedBy = "entradaHistorial", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistorialMedico> historiales;
}