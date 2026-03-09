package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class GestorFicheros {
    
    public static final String RUTA_USERS = "data/users.txt";
    public static final String RUTA_CARPETAS_USUARIOS = "data/usuarios/";

    public static void inicializarSistema() {
        try {
            Files.createDirectories(Paths.get(RUTA_CARPETAS_USUARIOS));
            Path archivoUsuarios = Paths.get(RUTA_USERS);
            if (!Files.exists(archivoUsuarios)) {
                Files.createFile(archivoUsuarios);
            }
        } catch (IOException e) {
            System.out.println("Error al inicializar el sistema: " + e.getMessage());
        }
    }

    public static String sanitizarEmail(String email) {
        return email.replace("@", "_").replace(".", "_");
    }
}