package com.proyecto.veterinaria.Model;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventario_medicamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInventarioMedicamento;
    
    private String categoria;
    
    private int cantidad;

    /* Relacion de InventarioMedicamento con JefeInventario */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJefeInventario")
    private JefeInventario jefeInventario;

    /* Relacion bidireccional de InventarioMedicamento con Medicamento */
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Medicamento> medicamentos;
}