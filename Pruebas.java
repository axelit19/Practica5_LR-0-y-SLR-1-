public class Pruebas {

    public static void ejecutar() {

        Analizador analizador = new Analizador();

        String[][] pruebas = {

            // Válidas
            {"num", ";;"},
            {"num", "+", "num", ";;"},
            {"num", "*", "num", ";;"},
            {"if", "bool", "then", "num", "else", "num", ";;"},
            {"let", "id", "=", "num", "in", "id", ";;"},

            // Inválidas
            {"num", "+", ";;"},
            {"if", "bool", "num", "else", "num", ";;"},
            {"(", "num", "+", "num", ";;"},
            {"let", "id", "=", ";;"},
            {"num", "*", "*", "num", ";;"}
        };

        String[] tipo = {
            "VALIDA 1",
            "VALIDA 2",
            "VALIDA 3",
            "VALIDA 4",
            "VALIDA 5",
            "INVALIDA 1",
            "INVALIDA 2",
            "INVALIDA 3",
            "INVALIDA 4",
            "INVALIDA 5"
        };

        for (int i = 0; i < pruebas.length; i++) {

            System.out.println("\n==============================");
            System.out.println("PRUEBA: " + tipo[i]);

            System.out.print("Cadena: ");
            for (String token : pruebas[i]) {
                System.out.print(token + " ");
            }
            System.out.println();

            boolean resultado = analizador.analizar(pruebas[i]);

            if (resultado) {
                System.out.println("RESULTADO: CADENA ACEPTADA");
            } else {
                System.out.println("RESULTADO: CADENA RECHAZADA");
            }
        }
    }
}
