package com.proyecto.veterinaria.Model;

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
@Table(name="medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "inventario")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;

    private String nombre;

    private String tipo;

    private String descripcion;

    private String dosis;

    /* Relacion de medicamento con inventario (Llave foranea) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario_medicamento", nullable = false)
    private InventarioMedicamento inventario;
}