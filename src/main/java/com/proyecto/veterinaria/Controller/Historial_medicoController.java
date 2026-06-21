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

import com.proyecto.veterinaria.Model.HistorialMedico;
import com.proyecto.veterinaria.Service.Historial_medicoService;

@RestController
@RequestMapping("/api/historial-medico")
public class Historial_medicoController {

    @Autowired
    private Historial_medicoService historialService;

    // Obtener todos los historiales
    @GetMapping
    public List<HistorialMedico> listarHistoriales() {
        return historialService.obtenerTodos();
    }

    // Obtener historial por ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> obtenerPorId(@PathVariable Long id) {
        return historialService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<HistorialMedico> obtenerPorCliente(@PathVariable Long clienteId) {
        return historialService.obtenerPorCliente(clienteId);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<HistorialMedico> obtenerPorMascota(@PathVariable Long mascotaId) {
        return historialService.obtenerPorMascota(mascotaId);
    }

    @PostMapping
    public ResponseEntity<HistorialMedico> guardar(@RequestBody HistorialMedico historial) {
        return ResponseEntity.ok(historialService.guardar(historial));
    }

    // Actualizar historial
    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(@PathVariable Long id, @RequestBody HistorialMedico historial) {
        try {
            return ResponseEntity.ok(historialService.actualizar(id, historial));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar historial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            historialService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
