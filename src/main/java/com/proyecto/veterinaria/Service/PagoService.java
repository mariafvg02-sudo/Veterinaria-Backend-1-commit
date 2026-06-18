package com.proyecto.veterinaria.Service;

import com.proyecto.veterinaria.Model.Pago;
import com.proyecto.veterinaria.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Transactional(readOnly = true)
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAllWithRelations();
    }

    @Transactional(readOnly = true)
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findByIdWithRelations(id);
    }

    @Transactional
    public Pago guardar(Pago pago) {
        if (pago.getEstado() == null) {
            pago.setEstado("COMPLETADO");
        }
        Pago saved = pagoRepository.save(pago);
        return pagoRepository.findByIdWithRelations(saved.getIdPago()).orElse(saved);
    }

    @Transactional
    public Pago actualizar(Long id, Pago detalles) {
        return pagoRepository.findById(id).map(pagoExistente -> {
            pagoExistente.setMetodo(detalles.getMetodo());
            Pago saved = pagoRepository.save(pagoExistente);
            return pagoRepository.findByIdWithRelations(saved.getIdPago()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
    }

    @Transactional
    public Pago anular(Long id) {
        return pagoRepository.findById(id).map(pago -> {
            pago.setEstado("ANULADO");
            Pago saved = pagoRepository.save(pago);
            return pagoRepository.findByIdWithRelations(saved.getIdPago()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado para anular"));
    }
}