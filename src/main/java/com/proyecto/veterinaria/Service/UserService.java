package com.proyecto.veterinaria.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto.veterinaria.Model.User;
import com.proyecto.veterinaria.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.veterinaria.Model.Rol;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    // GUARDAR CÓDIGOS TEMPORALES
    private Map<String, String> recoveryCodes = new HashMap<>();


    // =========================
    // REGISTRO
    // =========================
    public String register(User user) {
        if (userRepository.findByCorreo(user.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
        // Asumiendo que el repositorio tiene findByDocumentoIdentidad
        // if (userRepository.findByDocumentoIdentidad(user.getDocumentoIdentidad()).isPresent()) {
        //     throw new RuntimeException("El documento de identidad ya está registrado");
        // }

        // ENCRIPTAR PASSWORD
        user.setClave(passwordEncoder.encode(user.getClave()));
        // ROL AUTOMÁTICO SÓLO SI NO VIENE DEFINIDO
        if (user.getRol() == null) {
            user.setRol(Rol.CLIENTE);
        }

        userRepository.save(user);

        return "Usuario registrado correctamente";
    }

    /**
     * Método unificado para crear usuarios desde la administración.
     * Permite asignar roles como JEFE_INVENTARIO, VETERINARIO, etc.
     */
    public User crearUsuario(User user) {
        if (userRepository.findByCorreo(user.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo " + user.getCorreo() + " ya está registrado.");
        }
        
        if (user.getDocumentoIdentidad() == null) {
            throw new RuntimeException("El documento de identidad es obligatorio.");
        }

        // Encriptar clave
        user.setClave(passwordEncoder.encode(user.getClave()));

        // Rol por defecto si no viene especificado
        if (user.getRol() == null) {
            user.setRol(Rol.CLIENTE);
        }

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> listarPorRol(Rol rol) {
        return userRepository.findByRol(rol);
    }

    @Transactional(readOnly = true)
    public Optional<User> obtenerPorId(Long id) {
        return userRepository.findById(id);
    }

    public User actualizarUsuario(Long id, User detalles) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (detalles.getNombre() != null) {
            user.setNombre(detalles.getNombre());
        }
        if (detalles.getCorreo() != null) {
            user.setCorreo(detalles.getCorreo());
        }
        if (detalles.getTelefono() != null) {
            user.setTelefono(detalles.getTelefono());
        }
        if (detalles.getDocumentoIdentidad() != null) {
            user.setDocumentoIdentidad(detalles.getDocumentoIdentidad());
        }
     
        if (detalles.getClave() != null && !detalles.getClave().isEmpty()) {
            user.setClave(passwordEncoder.encode(detalles.getClave()));
        }
        if (detalles.getRol() != null) {
            user.setRol(detalles.getRol());
        }

        return userRepository.save(user);
    }

    public void eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }


    // =========================
    // LOGIN
    // =========================
    public User login(User user) {

        User existingUser = userRepository.findByCorreo(user.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // VALIDAR PASSWORD ENCRIPTADA
        boolean passwordCorrecta = passwordEncoder.matches(
                user.getClave(),
                existingUser.getClave());

        if (!passwordCorrecta) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return existingUser;
    }


    // =========================
    // ENVIAR CÓDIGO RECUPERACIÓN
    // =========================
    public String sendRecoveryCode(String correo) {

        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // GENERAR CÓDIGO DE 6 DÍGITOS
        String code = String.valueOf(
                (int) ((Math.random() * 900000) + 100000));

        // GUARDAR CÓDIGO TEMPORAL
        recoveryCodes.put(
                correo.trim().toLowerCase(),
                code.trim()
        );

        // ENVIAR CORREO
        mailService.sendRecoveryCode(correo, code);

        return "Código enviado al correo";
    }


    // =========================
    // VERIFICAR CÓDIGO
    // =========================
    public boolean verifyCode(String correo, String code) {

        System.out.println("========== VERIFY CODE ==========");

        System.out.println("Correo recibido: " + correo);
        System.out.println("Code recibido: " + code);

        String savedCode = recoveryCodes.get(
                correo.trim().toLowerCase()
        );

        System.out.println("Code guardado: " + savedCode);

        if (savedCode == null) {
            return false;
        }

        return savedCode.trim().equals(code.trim());
    }


    // =========================
    // CAMBIAR CONTRASEÑA
    // =========================
    public String resetPassword(String correo, String nuevaClave) {

        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // ENCRIPTAR NUEVA PASSWORD
        user.setClave(passwordEncoder.encode(nuevaClave));

        userRepository.save(user);

        // ELIMINAR CÓDIGO USADO
        recoveryCodes.remove(
                correo.trim().toLowerCase()
        );

        return "Contraseña actualizada correctamente";
    }
}