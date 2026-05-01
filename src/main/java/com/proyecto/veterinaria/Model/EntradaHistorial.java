package com.proyecto.veterinaria.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entrada_historial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String diagnostico;

    private String tratamiento;

    private String nombre;

    private String motivo;

    private String sintomas;

    private String observaciones;

    /* Relación: Muchas entradas de historial son registradas por un veterinario */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinario")
    private Veterinario veterinario;

    /* Relación bidireccional: Una entrada puede generar registros en el historial médico */
    @OneToMany(mappedBy = "entradaHistorial", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistorialMedico> historiales;
}