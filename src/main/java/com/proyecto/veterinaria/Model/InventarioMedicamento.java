package com.proyecto.veterinaria.Model;

import java.util.List;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
import lombok.*;

@Entity
@Table(name="inventario_medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"jefeInventario", "medicamentos"})
public class InventarioMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventarioMedicamento;
    
    private String categoria;
    
    private int cantidad;

    // Nuevo: nombre del inventario o referencia breve
    private String nombre;

    // Nuevo: precio unitario aproximado asociado al inventario
    private BigDecimal precio;

    /* Relacion de InventarioMedicamento con JefeInventario */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jefe_inventario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User jefeInventario;

    /* Relacion bidireccional de InventarioMedicamento con Medicamento */
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Medicamento> medicamentos;
}