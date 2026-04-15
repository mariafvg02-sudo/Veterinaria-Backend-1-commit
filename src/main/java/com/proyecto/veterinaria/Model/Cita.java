package com.proyecto.veterinaria.Model;

import java.time.LocalDateTime;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Cita")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCita;
    private LocalDateTime fecha;
    private String nombre;
    private String motivo;
    private String estado;

    /*Relacion de la foranea de cita con cliente*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    /*Relacion de la foranea de cita con veterinario*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVeterinario")
    private Veterinario veterinario;

}