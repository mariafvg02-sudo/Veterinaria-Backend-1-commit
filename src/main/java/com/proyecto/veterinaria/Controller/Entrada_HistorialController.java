package com.proyecto.veterinaria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.veterinaria.Model.EntradaHistorial;
import com.proyecto.veterinaria.Service.Entrada_HistorialService;

@RestController
@RequestMapping("/api/entradas-historial")
public class Entrada_HistorialController {

    @Autowired
    private Entrada_HistorialService service;

    @GetMapping
    public List<EntradaHistorial> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaHistorial> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntradaHistorial> crear(@RequestBody EntradaHistorial entrada) {
        return ResponseEntity.ok(service.guardar(entrada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaHistorial> actualizar(@PathVariable Long id, @RequestBody EntradaHistorial detalles) {
        try {
            return ResponseEntity.ok(service.actualizar(id, detalles));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}