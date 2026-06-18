package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.veterinaria.Model.Cita;
import com.proyecto.veterinaria.Model.Mascota;
import com.proyecto.veterinaria.Model.User;
import com.proyecto.veterinaria.Repository.CitaRepository;
import com.proyecto.veterinaria.Repository.MascotaRepository;
import com.proyecto.veterinaria.Repository.UserRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Transactional(readOnly = true)
    public List<Cita> listarTodas() {
        return citaRepository.findAllWithRelations();
    }

    @Transactional(readOnly = true)
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findByIdWithRelations(id);
    }

    @Transactional(readOnly = true)
    public List<Cita> obtenerPorCliente(Long clienteId) {
        return citaRepository.findByClienteIdWithRelations(clienteId);
    }

    @Transactional(readOnly = true)
    public List<Cita> obtenerPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioIdWithRelations(veterinarioId);
    }

    @Transactional
    public Cita agendarCita(Cita cita) {
        if (cita.getCliente() != null && cita.getCliente().getId() != null) {
            User cliente = userRepository.findById(cita.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + cita.getCliente().getId()));
            cita.setCliente(cliente);
        }
        if (cita.getMascota() != null && cita.getMascota().getIdMascota() != null) {
            Mascota mascota = mascotaRepository.findById(cita.getMascota().getIdMascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + cita.getMascota().getIdMascota()));
            cita.setMascota(mascota);
        }
        if (cita.getVeterinario() != null && cita.getVeterinario().getId() != null) {
            User veterinario = userRepository.findById(cita.getVeterinario().getId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + cita.getVeterinario().getId()));
            cita.setVeterinario(veterinario);
        }
        if (cita.getRecepcionista() != null && cita.getRecepcionista().getId() != null) {
            User recepcionista = userRepository.findById(cita.getRecepcionista().getId())
                .orElseThrow(() -> new RuntimeException("Recepcionista no encontrado con id: " + cita.getRecepcionista().getId()));
            cita.setRecepcionista(recepcionista);
        }
        cita.setEstado(cita.getVeterinario() != null ? "asignada" : "pendiente");
        Cita saved = citaRepository.save(cita);
        return citaRepository.findByIdWithRelations(saved.getIdCita()).orElse(saved);
    }

    @Transactional
    public Cita actualizarCita(Long id, Cita citaData) {
        return citaRepository.findById(id).map(cita -> {
            if (citaData.getEstado() != null) {
                cita.setEstado(citaData.getEstado());
            }
            if (citaData.getFecha() != null) {
                cita.setFecha(citaData.getFecha());
            }
            if (citaData.getMotivo() != null) {
                cita.setMotivo(citaData.getMotivo());
            }
            if (citaData.getObservacionCancelacion() != null) {
                cita.setObservacionCancelacion(citaData.getObservacionCancelacion());
            }
            if (citaData.getDiagnostico() != null) {
                cita.setDiagnostico(citaData.getDiagnostico());
            }
            if (citaData.getTratamiento() != null) {
                cita.setTratamiento(citaData.getTratamiento());
            }
            if (citaData.getVeterinario() != null && citaData.getVeterinario().getId() != null) {
                userRepository.findById(citaData.getVeterinario().getId())
                    .ifPresent(cita::setVeterinario);
            }
            if (citaData.getRecepcionista() != null && citaData.getRecepcionista().getId() != null) {
                userRepository.findById(citaData.getRecepcionista().getId())
                    .ifPresent(cita::setRecepcionista);
            }
            Cita saved = citaRepository.save(cita);
            return citaRepository.findByIdWithRelations(saved.getIdCita()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
    }

    @Transactional
    public void cancelarCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Transactional
    public Cita actualizarEstado(Long id, String nuevoEstado) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(nuevoEstado);
            Cita saved = citaRepository.save(cita);
            return citaRepository.findByIdWithRelations(saved.getIdCita()).orElse(saved);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

}
