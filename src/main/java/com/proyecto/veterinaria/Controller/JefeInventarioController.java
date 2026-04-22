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

import com.proyecto.veterinaria.Model.JefeInventario;
import com.proyecto.veterinaria.Service.JefeInventarioService;

@RestController
@RequestMapping("/api/jefe-inventario")
// @CrossOrigin(origins = "*") // CORS ahora se maneja globalmente en WebConfig
public class JefeInventarioController {

    @Autowired
    private JefeInventarioService jefeInventarioService;

    @GetMapping
    public List<JefeInventario> listar() {
        return jefeInventarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JefeInventario> obtenerPorId(@PathVariable Long id) {
        return jefeInventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JefeInventario> crear(@RequestBody JefeInventario jefe) {
        return ResponseEntity.ok(jefeInventarioService.crearJefeInventario(jefe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JefeInventario> actualizar(@PathVariable Long id, @RequestBody JefeInventario jefe) {
        try {
            return ResponseEntity.ok(jefeInventarioService.actualizarJefeInventario(id, jefe));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            jefeInventarioService.eliminarJefeInventario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}