package com.proyecto.veterinaria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.veterinaria.Model.Administrador;
import com.proyecto.veterinaria.Service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // 🔹 Obtener todos (aunque solo habrá uno)
    @GetMapping
    public ResponseEntity<List<Administrador>> listarAdministradores() {
        return ResponseEntity.ok(administradorService.obtenerTodos());
    }

    // 🔹 Obtener administrador por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Administrador admin = administradorService.obtenerPorId(id);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 🔹 Crear administrador (solo uno permitido)
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Administrador administrador) {
        try {
            Administrador nuevo = administradorService.guardar(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Actualizar administrador
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Administrador administrador) {
        try {
            Administrador actualizado = administradorService.actualizar(id, administrador);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 🔹 NO se permite eliminar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            administradorService.eliminar(id);
            return ResponseEntity.ok("Operación no permitida");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}