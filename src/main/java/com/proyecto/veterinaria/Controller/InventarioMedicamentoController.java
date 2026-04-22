package com.proyecto.veterinaria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.veterinaria.Model.InventarioMedicamento;
import com.proyecto.veterinaria.Service.InventarioMedicamentoService;

@RestController
@RequestMapping("/api/inventario")
// @CrossOrigin(origins = "*") // CORS ahora se maneja globalmente en WebConfig
public class InventarioMedicamentoController {

    @Autowired
    private InventarioMedicamentoService inventarioService;

    @GetMapping
    public List<InventarioMedicamento> listar() {
        return inventarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioMedicamento> obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventarioMedicamento> crear(@RequestBody InventarioMedicamento inventario) {
        return ResponseEntity.ok(inventarioService.crearInventario(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioMedicamento> actualizar(@PathVariable Long id, @RequestBody InventarioMedicamento detalles) {
        try {
            return ResponseEntity.ok(inventarioService.actualizarInventario(id, detalles));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            inventarioService.eliminarInventario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}