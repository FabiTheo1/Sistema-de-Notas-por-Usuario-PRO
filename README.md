# 📝 Gestor de Notas por Consola - UT5

Aplicación de consola en Java para la gestión de usuarios y notas personales.

## 🚀 Características Principales

El programa simula un sistema de gestión de notas personales con persistencia de datos en ficheros de texto plano (`.txt`). 

* **Sistema de Autenticación:** * Registro de nuevos usuarios (evitando duplicados).
    * Inicio de sesión validando credenciales.
* **Gestión de Ficheros (NIO.2):**
    * Creación automática de la estructura de directorios (`data/usuarios/`).
    * Sanitización de nombres de usuario (emails) para la creación segura de carpetas en el sistema operativo.
* **Gestión de Notas (CRUD):**
    * Creación de notas asociadas al usuario activo.
    * Lectura de todas las notas.
    * Lectura detallada de una nota específica por índice.
    * Eliminación de notas mediante lectura y reescritura de ficheros.

## 📂 Estructura del Proyecto

El código está organizado siguiendo el patrón de arquitectura por capas (paquetes) para separar responsabilidades y facilitar su mantenimiento:

```text
src/
 ├── app/
 │    └── Main.java             # Clase principal que maneja los menús y la interacción (Scanner)
 ├── service/
 │    ├── AuthService.java      # Lógica de registro y verificación de login
 │    └── NotaService.java      # Lógica de negocio para crear, leer y borrar notas
 └── utils/
      └── GestorFicheros.java   # Utilidades para inicializar rutas y sanitizar nombres
```

## 🛠️ Tecnologías Utilizadas
Lenguaje: Java

Librerías principales: java.nio.file.*, java.io.*

Manejo de recursos: Bloques try-with-resources para garantizar el cierre seguro de flujos (BufferedReader, BufferedWriter).

Estructuras de datos: List<String> y Arrays básicos.

## ⚙️ Instalación y Ejecución
Clona este repositorio o descarga el código fuente.

Abre el proyecto en tu IDE favorito (Eclipse, IntelliJ IDEA, NetBeans, etc.).

Asegúrate de tener configurado el JDK correctamente.

Ejecuta la clase app.Main.

¡Importante! La carpeta data/ se generará automáticamente en la raíz del proyecto la primera vez que ejecutes la aplicación.

##✒️ Autor
Fabián González Olofsson - Alumno de 1º DAM
