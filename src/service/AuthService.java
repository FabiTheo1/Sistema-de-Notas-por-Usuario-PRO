package service;

import utils.GestorFicheros;
import java.nio.file.*;
import java.io.*;

public class AuthService {

    public boolean registrar(String email, String password) {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("El email y la contraseña no pueden estar vacíos.");
            return false;
        }

        if (existeUsuario(email)) {
            System.out.println("Ya existe un usuario con ese email.");
            return false;
        }

        Path pathUsers = Paths.get(GestorFicheros.RUTA_USERS);
        String linea = email + ";" + password + System.lineSeparator();

        try (BufferedWriter bw = Files.newBufferedWriter(pathUsers, StandardOpenOption.APPEND)) {
            bw.write(linea);
            
            String emailSanitizado = GestorFicheros.sanitizarEmail(email);
            Path carpetaUsuario = Paths.get(GestorFicheros.RUTA_CARPETAS_USUARIOS + emailSanitizado);
            Files.createDirectories(carpetaUsuario);
            
            Path archivoNotas = carpetaUsuario.resolve("notas.txt");
            if (!Files.exists(archivoNotas)) {
                Files.createFile(archivoNotas);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String email, String password) {
        Path pathUsers = Paths.get(GestorFicheros.RUTA_USERS);
        try (BufferedReader br = Files.newBufferedReader(pathUsers)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2 && partes[0].equals(email) && partes[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }
        return false;
    }

    private boolean existeUsuario(String email) {
        Path pathUsers = Paths.get(GestorFicheros.RUTA_USERS);
        try (BufferedReader br = Files.newBufferedReader(pathUsers)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.split(";")[0].equals(email)) return true;
            }
        } catch (IOException e) {
            // Si hay un error de lectura asumo que no hay texto
        }
        return false;
    }
}