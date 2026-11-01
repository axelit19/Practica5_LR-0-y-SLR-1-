Main.java 

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AnalizadorLALR analizador = new AnalizadorLALR();

        int opcion = 0;

        do {
            System.out.println("\n===== ANALIZADOR LALR(1) - PRÁCTICA 6 =====");
            System.out.println("1. Analizar cadena manual");
            System.out.println("2. Ejecutar vectores de prueba automáticos (Pruebas.java)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opción inválida.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese los tokens separados por espacios (ej: id = id): ");
                    String entrada = scanner.nextLine().trim();

                    if (entrada.isEmpty()) {
                        System.out.println("Error: la entrada está vacía.");
                        break;
                    }

                    // Divide por espacios y envía directamente el arreglo de strings
                    String[] tokens = entrada.split("\\s+");

                    boolean valido = analizador.analizar(tokens);

                    if (valido) {
                        System.out.println("\n[ÉXITO] ¡CADENA VÁLIDA!");
                    } else {
                        System.out.println("\n[ERROR] CADENA INVÁLIDA");
                    }
                    break;

                case 2:
                    Pruebas.ejecutarPruebas(analizador);
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 3);

        scanner.close();
    }
}
