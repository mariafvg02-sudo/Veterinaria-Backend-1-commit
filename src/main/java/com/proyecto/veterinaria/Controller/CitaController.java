package com.proyecto.veterinaria.Controller;

import com.proyecto.veterinaria.Model.Cita;
import com.proyecto.veterinaria.Service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    
    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> listar() {
        return citaService.listarTodas();
    }

    @PostMapping("/agendar")
    public ResponseEntity<Cita> agendar(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.agendarCita(cita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }
}
