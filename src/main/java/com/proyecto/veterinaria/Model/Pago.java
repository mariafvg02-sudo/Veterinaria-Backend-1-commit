package com.proyecto.veterinaria.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    private LocalDateTime fechaHora;
    private String metodo;
    private Double monto; // Usamos Double para el monto, podrías considerar BigDecimal para mayor precisión en aplicaciones financieras.
    private String estado; // Ejemplo: "COMPLETADO", "ANULADO"
  //Relacón bidireccional de pago con factura 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factu_id")
    private Factura factura;
}