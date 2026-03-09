package service;

import utils.GestorFicheros;
import java.nio.file.*;
import java.util.List;
import java.io.IOException;

public class NotaService {

    private Path obtenerRutaNotas(String email) {
        String emailSanitizado = GestorFicheros.sanitizarEmail(email);
        return Paths.get(GestorFicheros.RUTA_CARPETAS_USUARIOS + emailSanitizado + "/notas.txt");
    }

    public void crearNota(String email, String titulo, String contenido) {
        Path pathNotas = obtenerRutaNotas(email);
        String linea = titulo + ";" + contenido + System.lineSeparator();

        try (java.io.BufferedWriter bw = Files.newBufferedWriter(pathNotas, StandardOpenOption.APPEND)) {
            bw.write(linea);
            System.out.println("Nota guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la nota: " + e.getMessage());
        }
    }

    public void listarNotas(String email) {
        Path pathNotas = obtenerRutaNotas(email);
        try {
            List<String> lineas = Files.readAllLines(pathNotas);
            if (lineas.isEmpty()) {
                System.out.println("No tienes notas guardadas.");
                return;
            }
            System.out.println("\n--- TUS NOTAS ---");
            for (int i = 0; i < lineas.size(); i++) {
                String[] partes = lineas.get(i).split(";");
                System.out.println((i + 1) + ". " + partes[0]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer las notas.");
        }
    }

    public void verNota(String email, int numeroLinea) {
        Path pathNotas = obtenerRutaNotas(email);
        try {
            List<String> lineas = Files.readAllLines(pathNotas);
            int indice = numeroLinea - 1;
            if (indice >= 0 && indice < lineas.size()) {
                String[] partes = lineas.get(indice).split(";");
                System.out.println("\n Título: " + partes[0]);
                System.out.println("Contenido: " + (partes.length > 1 ? partes[1] : ""));
            } else {
                System.out.println("Número de nota incorrecto.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la nota.");
        }
    }

    public void eliminarNota(String email, int numeroLinea) {
        Path pathNotas = obtenerRutaNotas(email);
        try {
            List<String> lineas = Files.readAllLines(pathNotas);
            int indice = numeroLinea - 1;
            if (indice >= 0 && indice < lineas.size()) {
                lineas.remove(indice);
                Files.write(pathNotas, lineas);
                System.out.println("Nota eliminada correctamente.");
            } else {
                System.out.println("Número de nota incorrecto.");
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar la nota.");
        }
    }
}