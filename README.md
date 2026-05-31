# Veterinaria Backend

API REST para la gestión integral de una clínica veterinaria, construida con Spring Boot 3.3.4.

---

## Objetivo del proyecto

Proveer el backend de un sistema de información veterinaria que permita administrar mascotas, propietarios, citas médicas, historial clínico, medicamentos, inventario, facturación y pagos. Expone endpoints RESTful consumidos por el frontend y cuenta con seguridad integrada, documentación interactiva vía Swagger y envío de correos electrónicos automáticos.

---

## Herramientas y tecnologías utilizadas

| Herramienta / Librería | Versión | Propósito |
|---|---|---|
| Java JDK | 17 | Lenguaje principal |
| Spring Boot | 3.3.4 | Framework base |
| Spring Web | — | Exposición de endpoints REST |
| Spring Security | — | Autenticación y control de acceso |
| Spring Data JPA | — | Acceso y gestión de datos con Hibernate |
| Spring Mail | — | Envío de correos electrónicos (SMTP Gmail) |
| Spring Validation | — | Validación de datos de entrada |
| MySQL Connector | 8.0 | Driver JDBC para MySQL |
| Lombok | 1.18.26 | Reducción de código boilerplate |
| SpringDoc OpenAPI (Swagger UI) | 2.6.0 | Documentación interactiva de la API |
| Maven Wrapper (`mvnw`) | — | Gestión de dependencias y build (no requiere instalación) |
| Docker / Docker Compose | — | Contenedor opcional para MySQL |

**Base de datos:** MySQL 8.0

---

## Módulos del sistema

| Módulo | Descripción |
|---|---|
| **Autenticación** (`Auth`) | Registro e inicio de sesión de usuarios con roles y permisos |
| **Usuarios** (`User`) | Gestión de cuentas de usuarios del sistema |
| **Mascotas** (`Mascota`) | CRUD de mascotas asociadas a propietarios |
| **Citas** (`Cita`) | Programación y seguimiento de citas veterinarias |
| **Historial Médico** (`HistorialMedico`) | Registro del historial clínico por mascota |
| **Entradas de Historial** (`EntradaHistorial`) | Notas y registros individuales dentro del historial médico |
| **Medicamentos** (`Medicamento`) | Catálogo de medicamentos disponibles |
| **Inventario de Medicamentos** (`InventarioMedicamento`) | Control de stock y disponibilidad de medicamentos |
| **Facturas** (`Factura`) | Generación y consulta de facturas por servicios |
| **Pagos** (`Pago`) | Registro y seguimiento de pagos asociados a facturas |
| **Correo** (`MailService`) | Envío de notificaciones por correo electrónico |
| **Seguridad y CORS** (`Config`) | Configuración de Spring Security y políticas de acceso cruzado |

---

## Requisitos del dispositivo para implementación

| Componente | Mínimo recomendado |
|---|---|
| **Sistema operativo** | Windows 10/11, Ubuntu 20.04+ o macOS 12+ |
| **Procesador** | 2 núcleos (x86-64) |
| **RAM** | 4 GB (8 GB recomendado con Docker activo) |
| **Almacenamiento** | 2 GB libres |
| **Java JDK** | 17 o superior |
| **MySQL** | 8.0 (local o via Docker) |
| **Docker** (opcional) | Docker Desktop 4.x o Docker Engine 24.x |
| **Conexión a internet** | Requerida para descarga de dependencias Maven en el primer build |

---

## Instalación y configuración

### 1. Clonar el repositorio

```bash
git clone <URL-del-repositorio>
cd Veterinaria-Backend-1-commit
```

### 2. Configurar la base de datos MySQL

Elige una de las dos opciones:

**Opción A — Instalación local de MySQL**

Crea la base de datos manualmente:

```sql
CREATE DATABASE veterinaria_db;
```

Credenciales por defecto en `application.properties`:
- **Usuario:** `root`
- **Contraseña:** `maria123`
- **Puerto:** `3306`

**Opción B — Docker (sin instalación de MySQL)**

Requiere tener [Docker](https://docs.docker.com/get-docker/) instalado.

```bash
docker compose up -d
```

Verificar que el contenedor está corriendo:

```bash
docker compose ps
```

Comandos adicionales de Docker:

```bash
# Ver logs de MySQL
docker compose logs -f mysql

# Detener el contenedor (conserva los datos)
docker compose stop

# Eliminar el contenedor y sus datos
docker compose down -v

# Conectarse a MySQL desde la terminal
docker exec -it veterinaria_db mysql -u root -pmaria123 veterinaria_db
```

### 3. Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

El servidor arranca en `http://localhost:8080`.

> En Windows usa `mvnw.cmd spring-boot:run` si el script bash no es reconocido.

---

## Documentación interactiva de la API (Swagger)

Una vez que el servidor esté corriendo, abre en el navegador:

```
http://localhost:8080/swagger-ui/index.html
```

Desde ahí puedes explorar y probar todos los endpoints disponibles sin necesidad de herramientas externas.

---

## Estructura del proyecto

```
Veterinaria-Backend-1-commit/
├── src/
│   ├── main/
│   │   ├── java/com/proyecto/veterinaria/
│   │   │   ├── Config/               # Seguridad (Spring Security) y CORS
│   │   │   ├── Controller/           # Endpoints REST de cada módulo
│   │   │   ├── Model/                # Entidades JPA (tablas de la base de datos)
│   │   │   ├── Repository/           # Interfaces de acceso a datos (Spring Data)
│   │   │   └── Service/              # Lógica de negocio de cada módulo
│   │   └── resources/
│   │       └── application.properties  # Configuración de BD, correo y logging
├── docker-compose.yml                # Servicio MySQL en contenedor
├── pom.xml                           # Dependencias y configuración Maven
└── mvnw / mvnw.cmd                   # Maven Wrapper (no requiere instalación de Maven)
```