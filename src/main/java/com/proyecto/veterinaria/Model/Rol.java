package com.proyecto.veterinaria.Model;

import java.util.EnumSet;
import java.util.Set;

public enum Rol {

    ADMINISTRADOR(EnumSet.of(
            Permission.GESTION_USUARIOS,
            Permission.GESTION_INVENTARIO,
            Permission.GESTION_CITAS,
            Permission.GESTION_FACTURAS)),
            
    CLIENTE(EnumSet.of(
            Permission.CONSULTA_MASCOTAS,
            Permission.GESTION_CITAS)),
            
    RECEPCIONISTA(EnumSet.of(
            Permission.GESTION_CITAS,
            Permission.GESTION_CLIENTES,
            Permission.GESTION_FACTURAS)),
            
    VETERINARIO(EnumSet.of(
            Permission.GESTION_HISTORIALES,
            Permission.CONSULTA_MASCOTAS,
            Permission.GESTION_CITAS)),
            
    JEFEINVENTARIO(EnumSet.of(
            Permission.GESTION_INVENTARIO,
            Permission.CONSULTA_MEDICAMENTOS));

    private final Set<Permission> permisos;

    Rol(Set<Permission> permisos) {
        this.permisos = permisos;
    }

    public Set<Permission> getPermisos() {
        return permisos;
    }

    public boolean tienePermiso(Permission permiso) {
        return permisos != null && permisos.contains(permiso);
    }
}