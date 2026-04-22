package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Mascota;
import com.proyecto.veterinaria.Repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> obtenerPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    // Buscar mascota por nombre
    public Optional<Mascota> obtenerPorNombre(String nombre) {
        return mascotaRepository.findByNombre(nombre);
    }

    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizarMascota(Long id, Mascota detalles) {
        return mascotaRepository.findById(id).map(m -> {
            m.setNombre(detalles.getNombre());
            m.setEdad(detalles.getEdad());
            m.setPeso(detalles.getPeso());
            m.setEspecie(detalles.getEspecie());
            m.setRaza(detalles.getRaza());
            return mascotaRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }

    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }
        mascotaRepository.deleteById(id);
    }
}