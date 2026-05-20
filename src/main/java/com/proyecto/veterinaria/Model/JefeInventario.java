package com.proyecto.veterinaria.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jefe_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JefeInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJefeInventario;
    @Column(unique = true)
    private long documentoIdentidad;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String telefono;
    private String clave;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /* Relacion de JefeInventario con InventarioMedicamento */
    @OneToOne(mappedBy = "jefeInventario", cascade = CascadeType.ALL)
    @JsonIgnore
    private InventarioMedicamento inventario;
}