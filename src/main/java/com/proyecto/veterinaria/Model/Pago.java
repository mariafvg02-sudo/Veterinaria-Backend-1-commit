package com.proyecto.veterinaria.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "factura")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    private LocalDateTime fechaHora;
    private String metodo;
    private BigDecimal monto; 
    private String estado; // Ejemplo: "COMPLETADO", "ANULADO"
  //Relacón bidireccional de pago con factura 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;
}