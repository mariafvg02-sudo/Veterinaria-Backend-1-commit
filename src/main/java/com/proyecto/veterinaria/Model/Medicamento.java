package com.proyecto.veterinaria.Model;

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
@Table(name="medicamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedicamento;

    private String nombre;

    private String tipo;

    private String descripcion;

    private String dosis;

    /* Relacion de medicamento con inventario (Llave foranea) */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInventarioMedicamento")
    private InventarioMedicamento inventario;
}