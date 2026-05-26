package com.proyecto.veterinaria.Model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Agregamos "veterinario" al ToString para evitar bucles de Lombok
@ToString(exclude = {"cliente", "recepcionista", "veterinario", "factura"})
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(length = 100)
    private String nombrePropetario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 500)
    private String motivo;

    @Column(nullable = false, length = 50)
    private String estado;

    // --- Relaciones ---

    /* Relación con el Cliente (Dueño de la mascota) */
    // Usamos EAGER y evitamos problemas de serialización con las propiedades de Hibernate
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_cliente", nullable = false)
    private User cliente;

    /* Relación con el Recepcionista que agendó */
    // Lo cambié a nullable = true por si el cliente agenda solo desde la web en el futuro
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_recepcionista", nullable = true)
    private User recepcionista;

    /* NUEVA: Relación con el Veterinario asignado (Usando la clase User) */
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_veterinario", nullable = false)
    private User veterinario;

    /* Relación bidireccional con Factura */
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Factura factura;
}