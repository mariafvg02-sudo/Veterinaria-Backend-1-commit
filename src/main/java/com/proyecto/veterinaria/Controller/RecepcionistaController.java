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

import com.proyecto.veterinaria.Model.Recepcionista;
import com.proyecto.veterinaria.Service.RecepcionistaService;

@RestController
@RequestMapping("/api/recepcionistas")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping
    public List<Recepcionista> listar() {
        return recepcionistaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recepcionista> obtenerPorId(@PathVariable Long id) {
        return recepcionistaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recepcionista> crear(@RequestBody Recepcionista recepcionista) {
        return ResponseEntity.ok(recepcionistaService.crearRecepcionista(recepcionista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepcionista> actualizar(@PathVariable Long id, @RequestBody Recepcionista detalles) {
        try {
            return ResponseEntity.ok(recepcionistaService.actualizarRecepcionista(id, detalles));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            recepcionistaService.eliminarRecepcionista(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}