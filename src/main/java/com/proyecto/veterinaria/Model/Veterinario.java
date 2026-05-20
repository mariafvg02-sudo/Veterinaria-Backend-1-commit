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
@Table(name="veterinario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVeterinario;

    @Column(unique = true)
    private long documentoIdentidad;

    private String nombre;

    @Column(unique = true)
    private String correo;

    private String telefono;

    private String especialidad;

    private String clave;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /* Relacion de veterinario con citas */
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cita> citas;

    /* Relación de veterinario con las mascotas que atiende habitualmente */
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mascota> mascotas;

    /* Relación: Un veterinario tiene múltiples entradas de historial registradas */
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EntradaHistorial> entradasHistorial;

    /* Relación: Un veterinario tiene varios historiales médicos bajo su cargo */
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistorialMedico> historialesMedicos;
}
