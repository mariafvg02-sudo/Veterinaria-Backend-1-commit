package com.proyecto.veterinaria.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.veterinaria.Model.User;
import com.proyecto.veterinaria.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // LOGIN (Corregido para coincidir con los campos de Angular)
    // =========================
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> credenciales) {

        try {
            // ERROR CORREGIDO: Angular ahora envía 'correo' y 'clave'
            // Antes intentabas obtener 'email' y 'password', lo cual llegaba nulo.
            User requestUser = new User();
            requestUser.setCorreo(credenciales.get("correo"));    
            requestUser.setClave(credenciales.get("clave"));  

            User user = userService.login(requestUser);

            Map<String, Object> response = new HashMap<>();
            response.put("usuario", user);
            response.put("token", "fake-jwt-token");
            response.put("mensaje", "Login exitoso");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Login fallido: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody User request) {

        try {
            String mensaje = userService.register(request);

            Map<String, Object> response = new HashMap<>();
            response.put("usuario", request);
            response.put("token", "fake-jwt-token");
            response.put("mensaje", mensaje);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Registro fallido: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    // =========================
    // ENVIAR CÓDIGO
    // =========================
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody Map<String, String> request) { // Cambiado a Map para mayor flexibilidad

        try {
            String correo = request.get("correo");
            String mensaje = userService.sendRecoveryCode(correo);

            return ResponseEntity.ok(mensaje);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    // =========================
    // VERIFICAR CÓDIGO
    // =========================
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(
            @RequestBody Map<String, String> request) {

        try {
            String correo = request.get("correo");
            String code = request.get("code");

            boolean valid = userService.verifyCode(correo, code);

            if (valid) {
                return ResponseEntity.ok("Código correcto");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Código incorrecto");
            }

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR CONTRASEÑA
    // =========================
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody Map<String, String> request) {

        try {
            String correo = request.get("correo");
            String nuevaClave = request.get("nuevaClave");

            String mensaje = userService.resetPassword(correo, nuevaClave);

            return ResponseEntity.ok(mensaje);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}
