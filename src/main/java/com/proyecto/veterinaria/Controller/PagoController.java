package com.proyecto.veterinaria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.veterinaria.Model.Pago;
import com.proyecto.veterinaria.Service.PagoService;

@RestController
@RequestMapping("/api/pagos")
// @CrossOrigin(origins = "*") // CORS ahora se maneja globalmente en WebConfig
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Obtener todos los pagos
    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.obtenerTodos();
    }

    // Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<Pago> crear(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.guardar(pago));
    }

    // Actualizar pago
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        try {
            return ResponseEntity.ok(pagoService.actualizar(id, pago));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Cambiamos el Delete por un Patch o Put de anulación
    @PatchMapping("/{id}/anular")
    public ResponseEntity<Pago> anular(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.anular(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
