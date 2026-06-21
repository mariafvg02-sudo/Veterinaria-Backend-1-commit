package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.veterinaria.Model.HistorialMedico;
import com.proyecto.veterinaria.Repository.Historial_medicoRepository;

@Service
public class Historial_medicoService {
// Asegúrate de que el nombre de la clase tenga el guion bajo 
// para que coincida con lo que el Controller busca.

    @Autowired
    private Historial_medicoRepository historialRepository;

    @Transactional(readOnly = true)
    public List<HistorialMedico> obtenerTodos() {
        return historialRepository.findAllWithRelations();
    }

    @Transactional(readOnly = true)
    public Optional<HistorialMedico> obtenerPorId(Long id) {
        return historialRepository.findByIdWithRelations(id);
    }

    @Transactional(readOnly = true)
    public List<HistorialMedico> obtenerPorCliente(Long clienteId) {
        return historialRepository.findByClienteIdWithRelations(clienteId);
    }

    @Transactional(readOnly = true)
    public List<HistorialMedico> obtenerPorMascota(Long mascotaId) {
        return historialRepository.findByMascotaIdWithRelations(mascotaId);
    }

    @Transactional
    public HistorialMedico guardar(HistorialMedico historial) {
        HistorialMedico saved = historialRepository.save(historial);
        return historialRepository.findByIdWithRelations(saved.getIdHistorialMedico()).orElse(saved);
    }

    @Transactional
    public HistorialMedico actualizar(Long id, HistorialMedico detalles) {
        return historialRepository.findById(id).map(h -> {
            h.setFecha(detalles.getFecha());
            h.setDiagnostico(detalles.getDiagnostico());
            h.setTratamiento(detalles.getTratamiento());
            h.setMotivo(detalles.getMotivo());

            h.setVeterinario(detalles.getVeterinario());
            h.setCita(detalles.getCita());
            h.setCliente(detalles.getCliente());
            h.setMascota(detalles.getMascota());
            HistorialMedico saved = historialRepository.save(h);
            return historialRepository.findByIdWithRelations(saved.getIdHistorialMedico()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Historial no encontrado"));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!historialRepository.existsById(id)) {
            throw new RuntimeException("Historial médico no encontrado");
        }
        historialRepository.deleteById(id);
    }
}
