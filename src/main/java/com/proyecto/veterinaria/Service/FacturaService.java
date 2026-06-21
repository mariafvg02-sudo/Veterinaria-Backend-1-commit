package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.veterinaria.Model.Factura;
import com.proyecto.veterinaria.Repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional(readOnly = true)
    public List<Factura> obtenerTodos() {
        return facturaRepository.findAllWithRelations();
    }

    @Transactional(readOnly = true)
    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findByIdWithRelations(id);
    }

    @Transactional
    public Factura guardar(Factura factura) {
        Factura saved = facturaRepository.save(factura);
        return facturaRepository.findByIdWithRelations(saved.getIdFactura()).orElse(saved);
    }

    @Transactional
    public Factura actualizar(Long id, Factura detalles) {
        return facturaRepository.findById(id).map(f -> {
            f.setFechaHora(detalles.getFechaHora());
            f.setEstado(detalles.getEstado());
            f.setTotal(detalles.getTotal());
            f.setCita(detalles.getCita());
            Factura saved = facturaRepository.save(f);
            return facturaRepository.findByIdWithRelations(saved.getIdFactura()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    @Transactional(readOnly = true)
    public Optional<Factura> obtenerPorCitaId(Long citaId) {
        return facturaRepository.findByCitaIdWithRelations(citaId);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}
