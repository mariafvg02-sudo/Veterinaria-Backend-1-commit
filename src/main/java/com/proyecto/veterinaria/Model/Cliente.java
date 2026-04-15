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
@Table(name="cliente")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente{
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;
  

    @Column(unique = true)
    private long documentoIdentidad;

    private String nombre;

    @Column(unique = true)
    private String correo;
    
    private String telefono;
   

    /*Relacion de cliente con cita*/
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cita> cita;

}