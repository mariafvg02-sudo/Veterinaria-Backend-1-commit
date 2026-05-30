# Scripts de configuración inicial

## Crear usuario Administrador

```bash
curl -X POST 'http://localhost:8080/api/auth/register' \
  -H 'Content-Type: application/json' \
  --data-raw '{
    "nombre": "Juan Carlos Osorio",
    "correo": "juancarlos@gmail.com",
    "clave": "juancarlos123",
    "documentoIdentidad": 1024567348,
    "rol": "ADMINISTRADOR",
    "telefono": "3125674638"
  }'
```

## Crear usuario Jefe de Inventario

```bash
curl -X POST 'http://localhost:8080/api/auth/register' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  --data-raw '{
    "nombre": "juan",
    "correo": "juan@correo.com",
    "clave": "12345678",
    "telefono": "12345678",
    "documentoIdentidad": 12345678,
    "direccion": "Calle Principal #123",
    "rol": "JEFEINVENTARIO"
  }'
```

## Crear usuario Veterinario

```bash
curl -X POST 'http://localhost:8080/api/auth/register' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  --data-raw '{
    "nombre": "andrea",
    "correo": "andrea@correo.com",
    "clave": "1234567",
    "telefono": "1234567",
    "documentoIdentidad": 1234567,
    "direccion": "Calle Principal #123",
    "rol": "VETERINARIO"
  }'
```

## Crear usuario Recepcionista

```bash
curl -X POST 'http://localhost:8080/api/auth/register' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  --data-raw '{
    "nombre": "Recepcionista",
    "correo": "recepcionista@correo.com",
    "clave": "12345612",
    "telefono": "12345612",
    "documentoIdentidad": 12345612,
    "direccion": "Calle Principal #123",
    "rol": "RECEPCIONISTA"
  }'
```

---

# Scripts para Windows CMD

## Crear usuario Administrador

```cmd
curl -X POST "http://localhost:8080/api/auth/register" -H "Content-Type: application/json" --data-raw "{\"nombre\": \"Juan Carlos Osorio\", \"correo\": \"juancarlos@gmail.com\", \"clave\": \"juancarlos123\", \"documentoIdentidad\": 1024567348, \"rol\": \"ADMINISTRADOR\", \"telefono\": \"3125674638\"}"
```

## Crear usuario Jefe de Inventario

```cmd
curl -X POST "http://localhost:8080/api/auth/register" -H "Content-Type: application/json" -H "Authorization: Bearer <token>" --data-raw "{\"nombre\": \"juan\", \"correo\": \"juan@correo.com\", \"clave\": \"12345678\", \"telefono\": \"12345678\", \"documentoIdentidad\": 12345678, \"direccion\": \"Calle Principal #123\", \"rol\": \"JEFEINVENTARIO\"}"
```

## Crear usuario Veterinario

```cmd
curl -X POST "http://localhost:8080/api/auth/register" -H "Content-Type: application/json" -H "Authorization: Bearer <token>" --data-raw "{\"nombre\": \"andrea\", \"correo\": \"andrea@correo.com\", \"clave\": \"1234567\", \"telefono\": \"1234567\", \"documentoIdentidad\": 1234567, \"direccion\": \"Calle Principal #123\", \"rol\": \"VETERINARIO\"}"
```

## Crear usuario Recepcionista

```cmd
curl -X POST "http://localhost:8080/api/auth/register" -H "Content-Type: application/json" -H "Authorization: Bearer <token>" --data-raw "{\"nombre\": \"maria\", \"correo\": \"maria@correo.com\", \"clave\": \"123456\", \"telefono\": \"123456\", \"documentoIdentidad\": 123456, \"direccion\": \"Calle Principal #123\", \"rol\": \"RECEPCIONISTA\"}"
```
