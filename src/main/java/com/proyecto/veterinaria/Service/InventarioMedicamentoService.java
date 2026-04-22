package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.InventarioMedicamento;
import com.proyecto.veterinaria.Repository.InventarioMedicamentoRepository;

@Service
public class InventarioMedicamentoService {

    @Autowired
    private InventarioMedicamentoRepository inventarioMedicamentoRepository;

    public List<InventarioMedicamento> listarTodos() {
        return inventarioMedicamentoRepository.findAll();
    }

    public Optional<InventarioMedicamento> obtenerPorId(Long id) {
        return inventarioMedicamentoRepository.findById(id);
    }

    public InventarioMedicamento crearInventario(InventarioMedicamento inventario) {
        return inventarioMedicamentoRepository.save(inventario);
    }

    public InventarioMedicamento actualizarInventario(Long id, InventarioMedicamento detalles) {
        return inventarioMedicamentoRepository.findById(id).map(i -> {
            i.setCategoria(detalles.getCategoria());
            i.setCantidad(detalles.getCantidad());
            return inventarioMedicamentoRepository.save(i);
        }).orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
    }

    public void eliminarInventario(Long id) {
        if (!inventarioMedicamentoRepository.existsById(id)) {
            throw new RuntimeException("Inventario no encontrado");
        }
        inventarioMedicamentoRepository.deleteById(id);
    }
}