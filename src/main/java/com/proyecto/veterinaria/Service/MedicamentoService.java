package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Medicamento;
import com.proyecto.veterinaria.Repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // Listar todos los medicamentos
    public List<Medicamento> listarTodos() {
        return medicamentoRepository.findAll();
    }

    // Obtener un medicamento por su ID
    public Optional<Medicamento> obtenerPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    // Crear un nuevo medicamento
    public Medicamento crearMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    // Actualizar un medicamento existente
    public Medicamento actualizarMedicamento(Long id, Medicamento detalles) {
        return medicamentoRepository.findById(id).map(m -> {
            m.setNombre(detalles.getNombre());
            m.setTipo(detalles.getTipo());
            m.setDescripcion(detalles.getDescripcion());
            m.setDosis(detalles.getDosis());
            m.setInventario(detalles.getInventario());
            return medicamentoRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));
    }

    // Eliminar un medicamento
    public void eliminarMedicamento(Long id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RuntimeException("Medicamento no encontrado");
        }
        medicamentoRepository.deleteById(id);
    }
}