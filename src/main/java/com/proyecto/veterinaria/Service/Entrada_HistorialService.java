package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.EntradaHistorial;
import com.proyecto.veterinaria.Repository.Entrada_HistorialRepository;

@Service
public class Entrada_HistorialService {

    @Autowired
    private Entrada_HistorialRepository repository;

    public List<EntradaHistorial> listarTodos() {
        return repository.findAll();
    }

    public Optional<EntradaHistorial> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public EntradaHistorial guardar(EntradaHistorial entrada) {
        return repository.save(entrada);
    }

    public EntradaHistorial actualizar(Long id, EntradaHistorial detalles) {
        return repository.findById(id).map(e -> {
            e.setFecha(detalles.getFecha());
            e.setDiagnostico(detalles.getDiagnostico());
            e.setTratamiento(detalles.getTratamiento());
            e.setNombre(detalles.getNombre());
            e.setMotivo(detalles.getMotivo());
            e.setSintomas(detalles.getSintomas());
            e.setObservaciones(detalles.getObservaciones());
            e.setVeterinario(detalles.getVeterinario());
            return repository.save(e);
        }).orElseThrow(() -> new RuntimeException("Entrada de historial no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entrada de historial no encontrada");
        }
        repository.deleteById(id);
    }
}