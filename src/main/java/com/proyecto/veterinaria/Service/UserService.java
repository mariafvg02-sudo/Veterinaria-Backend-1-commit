package com.proyecto.veterinaria.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.veterinaria.Model.Rol;
import com.proyecto.veterinaria.Model.User;
import com.proyecto.veterinaria.Repository.UserRepository;

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
            return "El usuario ya existe";
        }

        // ENCRIPTAR PASSWORD
        user.setClave(passwordEncoder.encode(user.getClave()));

        // ROL AUTOMÁTICO CLIENTE
        user.setRol(Rol.CLIENTE);

        userRepository.save(user);

        return "Usuario registrado correctamente";
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