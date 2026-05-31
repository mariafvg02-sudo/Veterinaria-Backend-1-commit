package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> obtenerPorCliente(Long clienteId) {
        return citaRepository.findByCliente_Id(clienteId);
    }

    public Cita agendarCita(Cita cita) {
        // Resolver el cliente desde la BD para evitar entidad no gestionada
        if (cita.getCliente() != null && cita.getCliente().getId() != null) {
            User cliente = userRepository.findById(cita.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + cita.getCliente().getId()));
            cita.setCliente(cliente);
        }
        // Resolver mascota desde la BD
        if (cita.getMascota() != null && cita.getMascota().getIdMascota() != null) {
            Mascota mascota = mascotaRepository.findById(cita.getMascota().getIdMascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + cita.getMascota().getIdMascota()));
            cita.setMascota(mascota);
        }
        cita.setEstado("pendiente");
        cita.setVeterinario(null);
        cita.setRecepcionista(null);
        return citaRepository.save(cita);
    }

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
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
    }

    public void cancelarCita(Long id) {
        citaRepository.deleteById(id);
    }

    public List<Cita> obtenerPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinario_Id(veterinarioId);
    }

    public Cita actualizarEstado(Long id, String nuevoEstado) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(nuevoEstado);
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

}
