package com.proyecto.veterinaria.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"citasComoCliente", "citasComoRecepcionista", "mascotas", "historialesMedicos", "inventariosMedicamento"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // --- Datos de Identificación y Cuenta ---
    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String clave;

    @Column(name = "documento_identidad", unique = true, nullable = false)
    private Long documentoIdentidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    // --- Datos de Contacto ---
    private String telefono;
  

    // --- Relaciones Específicas del Rol: CLIENTE ---
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cita> citasComoCliente;

    // --- Relaciones Específicas del Rol: RECEPCIONISTA ---
    @OneToMany(mappedBy = "recepcionista", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cita> citasComoRecepcionista;



    @OneToMany(mappedBy = "veterinario", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HistorialMedico> historialesMedicos;

    @OneToMany(mappedBy = "jefeInventario", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<InventarioMedicamento> inventariosMedicamento;

    // Método centralizado para verificar accesos
    public boolean tienePermiso(Permission permiso) {
        return rol != null && rol.tienePermiso(permiso);
    }
}