# Veterinaria Backend

API REST para sistema de gestión veterinaria construida con Spring Boot 3.3.4.

## Requisitos previos

| Herramienta | Versión | Instalación |
|---|---|---|
| Java JDK | 17 | `sudo apt install openjdk-17-jdk -y` |
| MySQL | 8.0 | Local o via Docker (ver opciones abajo) |

> Maven no es necesario instalarlo — el proyecto incluye el wrapper `mvnw`.

---

## Base de datos MySQL

Puedes configurar MySQL de dos formas, elige la que prefieras:

### Opción A — Instalación local

Instala MySQL directamente en tu equipo y crea la base de datos:

```sql
CREATE DATABASE veterinaria_db;
```

Asegúrate de que el usuario y contraseña coincidan con los del archivo `application.properties`:
- **Usuario:** `root`
- **Contraseña:** `maria123`
- **Puerto:** `3306`

### Opción B — Docker (sin instalación)

Si no quieres instalar MySQL en tu equipo, Docker levanta la base de datos lista para usar con un solo comando. Requiere tener [Docker](https://docs.docker.com/get-docker/) instalado.

```bash
docker compose up -d
```

Verificar que el contenedor está corriendo:

```bash
docker compose ps
```

#### Comandos útiles de Docker

```bash
# Ver logs de MySQL
docker compose logs -f mysql

# Detener el contenedor (conserva los datos)
docker compose stop

# Eliminar el contenedor y los datos
docker compose down -v

# Conectarse a MySQL desde terminal
docker exec -it veterinaria_db mysql -u root -pmaria123 veterinaria_db
```

---

## Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

El servidor arranca en `http://localhost:8080`.

---

## Documentación de la API (Swagger)

Una vez corriendo, abre en el navegador:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/proyecto/veterinaria/
│   │   ├── Config/        # Seguridad y CORS
│   │   ├── Controller/    # Endpoints REST
│   │   ├── Model/         # Entidades JPA
│   │   ├── Repository/    # Acceso a datos
│   │   └── Service/       # Lógica de negocio
│   └── resources/
│       └── application.properties
docker-compose.yml
pom.xml
```
