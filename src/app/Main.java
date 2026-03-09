package app;

import service.AuthService;
import service.NotaService;
import utils.GestorFicheros;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static AuthService auth = new AuthService();
    private static NotaService notaService = new NotaService();

    public static void main(String[] args) {
        GestorFicheros.inicializarSistema();
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Introduce email: ");
                    String emailReg = sc.nextLine();
                    System.out.print("Introduce contraseña: ");
                    String passReg = sc.nextLine();
                    if(auth.registrar(emailReg, passReg)) {
                        System.out.println("¡Usuario registrado con éxito!");
                    }
                    break;
                case 2:
                    System.out.print("Email: ");
                    String emailLog = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String passLog = sc.nextLine();
                    
                    if (auth.login(emailLog, passLog)) {
                        System.out.println("¡Login correcto!");
                        menuUsuario(emailLog);
                    } else {
                        System.out.println("Credenciales incorrectas o usuario no existe.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        
        sc.close();
    }

    private static void menuUsuario(String email) {
        int opcion = -1;
        do {
            System.out.println("\n--- MENÚ DE USUARIO (" + email + ") ---");
            System.out.println("1. Crear nota");
            System.out.println("2. Listar notas");
            System.out.println("3. Ver nota por número");
            System.out.println("4. Eliminar nota (por número)");
            System.out.println("0. Cerrar sesión");
            System.out.print("Elige una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Título de la nota: ");
                    String titulo = sc.nextLine();
                    System.out.print("Contenido de la nota: ");
                    String contenido = sc.nextLine();
                    titulo = titulo.replace(";", ",");
                    contenido = contenido.replace(";", ",");
                    notaService.crearNota(email, titulo, contenido);
                    break;
                case 2:
                    notaService.listarNotas(email);
                    break;
                case 3:
                    notaService.listarNotas(email);
                    System.out.print("Introduce el número de la nota que quieres ver: ");
                    try {
                        int numVer = Integer.parseInt(sc.nextLine());
                        notaService.verNota(email, numVer);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                    }
                    break;
                case 4:
                    notaService.listarNotas(email);
                    System.out.print("Introduce el número de la nota a eliminar: ");
                    try {
                        int numBorrar = Integer.parseInt(sc.nextLine());
                        notaService.eliminarNota(email, numBorrar);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                    }
                    break;
                case 0:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}