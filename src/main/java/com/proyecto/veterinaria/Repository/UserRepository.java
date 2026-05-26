package com.proyecto.veterinaria.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.veterinaria.Model.Rol;
import com.proyecto.veterinaria.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCorreo(String correo);
    List<User> findByRol(Rol rol);
}
