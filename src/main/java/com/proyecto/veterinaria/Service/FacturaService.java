package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Factura;
import com.proyecto.veterinaria.Repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // Listar todas las facturas
    public List<Factura> obtenerTodos() {
        return facturaRepository.findAll();
    }

    // Obtener factura por ID
    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }

    // Guardar o actualizar factura
    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura actualizar(Long id, Factura detalles) {
        return facturaRepository.findById(id).map(f -> {
            f.setFechaHora(detalles.getFechaHora());
            f.setEstado(detalles.getEstado());
            f.setTotal(detalles.getTotal());
            f.setCita(detalles.getCita());
            return facturaRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    // Eliminar factura
    public void eliminar(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}
