package com.proyecto.veterinaria.Service;

import com.proyecto.veterinaria.Model.Pago;
import com.proyecto.veterinaria.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    // Obtener pago por ID
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    // Guardar o actualizar pago
    public Pago guardar(Pago pago) {
        if (pago.getEstado() == null) {
            pago.setEstado("COMPLETADO");
        }
        return pagoRepository.save(pago);
    }

    // En pagos, usualmente solo permitimos actualizar el método si hubo error, 
    // pero rara vez el monto. Aquí restringimos un poco.
    public Pago actualizar(Long id, Pago detalles) {
        return pagoRepository.findById(id).map(pagoExistente -> {
            // Solo permitimos cambiar el método, no el monto por seguridad
            pagoExistente.setMetodo(detalles.getMetodo());
            return pagoRepository.save(pagoExistente);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
    }

    // Cambiamos eliminar por anular
    public Pago anular(Long id) {
        return pagoRepository.findById(id).map(pago -> {
            pago.setEstado("ANULADO");
            return pagoRepository.save(pago);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado para anular"));
    }
}