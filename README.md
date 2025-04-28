
# 📄 README.md - Currency Exchange App

# 🏛️ Proyecto: Currency Exchange App (Reactive, JWT, WebFlux)

Este proyecto es una aplicación Spring Boot que permite realizar cambios de moneda entre divisas, registrando auditorías de las operaciones.

- **Backend reactivo** usando **Spring WebFlux**
- **Seguridad** JWT
- **Base de datos** H2 reactiva (R2DBC)
- **Documentación API** Swagger
- **Pruebas unitarias** (`WebFluxTest`, `Mockito`)
- **Programación reactiva** en todo el flujo

# ⚙️ Tecnologías usadas

- Java 17
- Spring Boot 3.1+
- Spring WebFlux
- Spring Security + JWT
- Spring Data R2DBC (H2 Database)
- WebClient
- Swagger (OpenAPI 3)
- JUnit 5, Mockito

# 🚀 ¿Cómo levantar el proyecto?

1. **Clona o descarga el proyecto.**
2. **Importa el proyecto** en tu IDE favorito.
3. **Ejecuta el proyecto**:
   
   Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Accede a Swagger UI**:
   
   👉 http://localhost:8080/swagger-ui.html

# 🔐 Cómo autenticarse

1. **Login**:

   - **Endpoint**: `POST /api/v1/auth/login`
   - **Body**:
     ```json
     {
       "username": "admin",
       "password": "admin"
     }
     ```
   - Te devolverá un **token JWT**.

2. **Usa el token en el Authorization header**:

   ```
   Authorization: Bearer <TOKEN>
   ```

# 💱 ¿Cómo hacer un cambio de divisas?

1. **Llama a** `POST /api/v1/exchange`
   **Body ejemplo**:

   ```json
   {
     "userId": 1234,
     "currencyFrom": "USD",
     "currencyTo": "PEN",
     "amount": 100
   }
   ```

2. El sistema:
   - Valida que el **usuario** exista en **gorest.co.in**.
   - Busca el **tipo de cambio** en SupportAPI.
   - Calcula el monto convertido.
   - Registra la **auditoría** de la operación.

# 📦 Endpoints principales

| Método | URL | Descripción |
|:---|:---|:---|
| POST | `/api/v1/auth/login` | Login para obtener JWT |
| POST | `/api/v1/exchange` | Realizar cambio de moneda |
| POST | `/api/v1/rates` | Crear tipo de cambio |
| PUT | `/api/v1/rates/{id}` | Actualizar tipo de cambio |
| GET | `/api/v1/rates` | Listar tipos de cambio |

# 📋 Notas

- Base de datos H2 embebida.
- Tipos de cambio deben cargarse vía `/api/v1/rates`.
- Servicio externo para validar usuarios: `https://gorest.co.in/public/v2/users/{id}`.
- Seguridad JWT obligatoria en endpoints.

# 🛡️ Seguridad

- JWT obligatorio.
- Expiración del token: 1 hora.

- 
 # 👨‍💻 Autores
 
 - Desarrollado por Melvin Saavedra apoyado por ChatGPT Spring Boot Pro 🚀 el mejor Spring Dev 🤖🔨 
 
 # 🔥 ¡Correlo, pruébalo y rompe el mercado de divisas! 😎
