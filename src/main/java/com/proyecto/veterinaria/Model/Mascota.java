package com.proyecto.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mascota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cliente"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    private String nombre;
    private int edad;
    private double peso;
    private String especie;
    private String raza;
    private String vacunas;
    private String sexo;
    private String esterilizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User cliente;

    @Transient
    private Long usuarioId;

    @Transient
    private Long idCliente;

    @Transient
    private Long id_cliente;

    
}