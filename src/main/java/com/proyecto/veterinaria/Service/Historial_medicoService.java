package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.HistorialMedico;
import com.proyecto.veterinaria.Repository.Historial_medicoRepository;

@Service
public class Historial_medicoService {

    @Autowired
    private Historial_medicoRepository historialRepository;

    // Obtener todos los historiales
    public List<HistorialMedico> obtenerTodos() {
        return historialRepository.findAll();
    }

    // Obtener historial por ID
    public Optional<HistorialMedico> obtenerPorId(Long id) {
        return historialRepository.findById(id);
    }

    // Guardar o actualizar historial
    public HistorialMedico guardar(HistorialMedico historial) {
        return historialRepository.save(historial);
    }

    public HistorialMedico actualizar(Long id, HistorialMedico detalles) {
        return historialRepository.findById(id).map(h -> {
            h.setFecha(detalles.getFecha());
            h.setDiagnostico(detalles.getDiagnostico());
            h.setTratamiento(detalles.getTratamiento());
            h.setNombreVet(detalles.getNombreVet());
            return historialRepository.save(h);
        }).orElseThrow(() -> new RuntimeException("Historial no encontrado"));
    }

    // Eliminar historial
    public void eliminar(Long id) {
        if (!historialRepository.existsById(id)) {
            throw new RuntimeException("Historial médico no encontrado");
        }
        historialRepository.deleteById(id);
    }
}
