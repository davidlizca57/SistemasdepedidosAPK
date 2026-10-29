# Sistema de Pedidos - Módulo móvil (APK)

Módulo móvil en Android (Kotlin) que consume la API del Sistema de Pedidos y muestra el listado de productos.

## Tecnología
Android Studio, Kotlin, Retrofit, Gson, RecyclerView.

## Endpoint consumido
`GET /api/productos` (sin parámetros de entrada). Respuesta: lista JSON con id, nombre, descripcion, precio, categoria y stock.

## Requisitos para probarlo
1. API Node.js encendida (`node src/app.js`, puerto 3000) y MySQL activo.
2. Celular y PC en la misma red WiFi.
3. En `MainActivity.kt`, cambiar la IP de `baseUrl` por la IPv4 del PC (`ipconfig`).
4. Instalar el APK (permitir instalación de fuentes desconocidas) o ejecutar desde Android Studio.

## Estados manejados
Cargando, éxito (lista de productos) y error (con botón Reintentar).
