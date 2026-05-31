package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.InventarioMedicamento;
import com.proyecto.veterinaria.Repository.InventarioMedicamentoRepository;
import com.proyecto.veterinaria.Repository.UserRepository;

@Service
public class InventarioMedicamentoService {

    @Autowired
    private InventarioMedicamentoRepository inventarioMedicamentoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<InventarioMedicamento> listarTodos() {
        return inventarioMedicamentoRepository.findAll();
    }

    public Optional<InventarioMedicamento> obtenerPorId(Long id) {
        return inventarioMedicamentoRepository.findById(id);
    }

    public InventarioMedicamento crearInventario(InventarioMedicamento inventario) {
        if (inventario.getJefeInventario() != null && inventario.getJefeInventario().getId() != null) {
            inventario.setJefeInventario(userRepository.getReferenceById(inventario.getJefeInventario().getId()));
        }
        return inventarioMedicamentoRepository.save(inventario);
    }

    public InventarioMedicamento actualizarInventario(Long id, InventarioMedicamento detalles) {
        return inventarioMedicamentoRepository.findById(id).map(i -> {
            i.setCategoria(detalles.getCategoria());
            i.setCantidad(detalles.getCantidad());
            i.setNombre(detalles.getNombre());
            i.setPrecio(detalles.getPrecio());
            if (detalles.getJefeInventario() != null && detalles.getJefeInventario().getId() != null) {
                i.setJefeInventario(userRepository.getReferenceById(detalles.getJefeInventario().getId()));
            }
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