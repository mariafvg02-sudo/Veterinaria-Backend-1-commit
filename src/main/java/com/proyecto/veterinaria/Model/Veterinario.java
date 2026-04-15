package com.proyecto.veterinaria.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="veterinario")
@Data
@Getter
@Setter
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

    /* Relacion de veterinario con citas */
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cita> citas;
}
