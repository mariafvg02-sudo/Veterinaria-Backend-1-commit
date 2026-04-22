package com.proyecto.veterinaria.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica esta configuración a todas las rutas de tu API
                .allowedOriginPatterns("*") // Permite peticiones desde cualquier origen de forma segura con allowCredentials
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los encabezados en las peticiones
                .allowCredentials(true) // Permite el envío de cookies y credenciales (ej. tokens de autorización)
                .maxAge(3600); // Tiempo en segundos que los resultados de la pre-flight request pueden ser cacheados
    }
}