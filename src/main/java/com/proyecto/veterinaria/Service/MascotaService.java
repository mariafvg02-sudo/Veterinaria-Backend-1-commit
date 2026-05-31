
package com.proyecto.veterinaria.Service;

import java.util.List;
import java.util.Optional;

import com.proyecto.veterinaria.Model.Mascota;
import com.proyecto.veterinaria.Model.User;
import com.proyecto.veterinaria.Repository.MascotaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> obtenerPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public List<Mascota> listarPorUsuario(Long usuarioId) {
        return mascotaRepository.findByCliente_Id(usuarioId);
    }

    public Mascota crearMascota(Mascota mascota) {
        asignarRelaciones(mascota);
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizarMascota(Long id, Mascota detalles) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        mascota.setNombre(detalles.getNombre());
        mascota.setEdad(detalles.getEdad());
        mascota.setPeso(detalles.getPeso());
        mascota.setEspecie(detalles.getEspecie());
        mascota.setRaza(detalles.getRaza());
        mascota.setVacunas(detalles.getVacunas());
        mascota.setSexo(detalles.getSexo());
        mascota.setEsterilizado(detalles.getEsterilizado());

        mascota.setUsuarioId(detalles.getUsuarioId());
        mascota.setIdCliente(detalles.getIdCliente());
        mascota.setId_cliente(detalles.getId_cliente());
        asignarRelaciones(mascota);

        return mascotaRepository.save(mascota);
    }

    public void eliminarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }
        mascotaRepository.deleteById(id);
    }

    private void asignarRelaciones(Mascota mascota) {
        Long clienteId = obtenerIdCliente(mascota);
        if (clienteId == null) {
            throw new RuntimeException("La mascota debe tener un cliente asociado");
        }

        User cliente = entityManager.find(User.class, clienteId);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        mascota.setCliente(cliente);

        // No se asigna veterinario aquí: mascota puede crearse sin veterinario
    }

    private Long obtenerIdCliente(Mascota mascota) {
        if (mascota.getUsuarioId() != null) return mascota.getUsuarioId();
        if (mascota.getIdCliente() != null) return mascota.getIdCliente();
        if (mascota.getId_cliente() != null) return mascota.getId_cliente();
        return null;
    }

    
}