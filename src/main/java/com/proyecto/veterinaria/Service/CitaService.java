package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Cita;
import com.proyecto.veterinaria.Repository.CitaRepository;

@Service
public class CitaService {
    
    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    public Cita agendarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public void cancelarCita(Long id) {
        citaRepository.deleteById(id);
    }

    public Cita actualizarEstado(Long id, String nuevoEstado) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(nuevoEstado);
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public Cita actualizarCita(Long id, Cita citaNueva) {
    return citaRepository.findById(id).map(cita -> {
        cita.setFecha(citaNueva.getFecha());
        cita.setMotivo(citaNueva.getMotivo());
        cita.setEstado(citaNueva.getEstado());
        cita.setCliente(citaNueva.getCliente());
        return citaRepository.save(cita);
    }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
}
}
