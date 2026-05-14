# TechStore API

API REST desarrollada con Spring Boot para la gestión de productos de TechStore Chile.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Spring Security
- JWT
- Docker
- Docker Compose
- Maven


CLONAR EL PROYECTO

Abrir una terminal y ejecutar:

git clone https://github.com/Konan619/techstore-api.git

Luego ingresar a la carpeta del proyecto:

cd techstore-api

EJECUTAR EL PROYECTO

Compilar el proyecto

mvn clean package -DskipTests

Levantar Docker Compose

docker compose up --build

Esto iniciará:

PostgreSQL
aplicación Spring Boot

PROBAR LA API

La aplicación quedará disponible en:

http://localhost:8080

LOGIN JWT

Endpoint:

POST /auth/login

Body:

{
"username": "admin",
"password": "admin123"
}

ENDPOINTS PRINCIPALES

GET /api/productos
Listar productos

GET /api/productos/activos
Listar productos activos

POST /api/productos
Crear producto

PUT /api/productos/{id}
Modificar producto

DELETE /api/productos/{id}
Eliminación lógica

POSTGRESQL

Entrar al contenedor:

docker exec -it techstore_db psql -U admin -d techstore

Ver productos:

SELECT * FROM productos;


- ## Integrantes

- Axel Blanlot
- Juan Ignacio Benavides
