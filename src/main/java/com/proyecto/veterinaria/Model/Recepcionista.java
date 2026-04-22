package com.proyecto.veterinaria.Model;

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
@Table(name="recepcionista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepcionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRecepcionista;
    @Column(unique = true)
    private long documentoIdentidad;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String telefono;
    private String clave;
}