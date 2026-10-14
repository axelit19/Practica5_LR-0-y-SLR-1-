
import java.util.Scanner;

public class Main {

    private static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Analizador analizador = new Analizador();

        int opcion;

        do {
            System.out.println("\n===== ANALIZADOR SLR(1) =====");
            System.out.println("1. Analizar cadena");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opcion invalida.");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese los tokens separados por espacios: ");
                    String entrada = scanner.nextLine().trim();

                    if (entrada.isEmpty()) {
                        System.out.println("Error: la entrada esta vacia.");
                        break;
                    }

                    String[] tokens = entrada.split("\\s+");

                    boolean valido = analizador.analizar(tokens);

                    if (valido) {
                        System.out.println("\nCADENA VALIDA");
                    } else {
                        System.out.println("\nCADENA INVALIDA");
                    }
                    break;

                case 2:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 2);

        scanner.close();
    }
}
