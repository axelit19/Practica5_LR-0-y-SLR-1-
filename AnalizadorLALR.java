
import java.util.*;

public class AnalizadorLALR {
    // ACTION[estado][terminal] = accion
    private final Map<Integer, Map<String, String>> ACTION = new HashMap<>();

    // GOTO[estado][noTerminal] = estado siguiente
    private final Map<Integer, Map<String, Integer>> GOTO = new HashMap<>();

    // Producciones numeradas de la gramatica
    private final Map<Integer, Produccion> producciones = new HashMap<>();

    private static class Produccion {
        String izquierda;
        int longitudDerecha;

        public Produccion(String izquierda, int longitudDerecha) {
            this.izquierda = izquierda;
            this.longitudDerecha = longitudDerecha;
        }
    }

    public AnalizadorLALR(){
        inicializarProducciones();
        inicializarTablas();
    }

    private void inicializarProducciones() {
        producciones.put(0, new Produccion("S'", 1)); // S' -> S
        producciones.put(1, new Produccion("S", 3)); // S -> L=R
        producciones.put(2, new Produccion("S", 1));  // S -> R
        producciones.put(3, new Produccion("L", 2));  // L -> *R
        producciones.put(4, new Produccion("L", 1));  // L -> id
        producciones.put(5, new Produccion("R", 1));  // R -> L

       
    }

    private void inicializarTablas() {
        //Tabla de la Gramatica 
        //Estado 0
        Map<String, String> action0 = new HashMap<>();
        Map<String, Integer> goto0 = new HashMap<>();
        action0.put("*", "s4");
        action0.put("id", "s5");
        goto0.put("S", 1);
        goto0.put("L", 2);
        goto0.put("R", 3);
        ACTION.put(0, action0);
        GOTO.put(0, goto0);

        // Estado 1
        Map<String, String> action1 = new HashMap<>();
        Map<String, Integer> goto1 = new HashMap<>();
        action1.put("$", "ACC");
        ACTION.put(1, action1);
        GOTO.put(1, goto1);

        //Estado 2
        Map<String, String> action2 = new HashMap<>();
        Map<String, Integer> goto2 = new HashMap<>();
        action2.put("=", "s6");
        action2.put("$", "r5");
        ACTION.put(2, action2);
        GOTO.put(2, goto2);

        //Estado 3
        Map<String, String> action3 = new HashMap<>();
        Map<String, Integer> goto3 = new HashMap<>();
        action3.put("$", "r2");
        ACTION.put(3, action3);
        GOTO.put(3, goto3);

        //Estado 4
        Map<String, String> action4 = new HashMap<>();
        Map<String, Integer> goto4 = new HashMap<>();
        action4.put("*", "s4");
        action4.put("id", "s5");
        goto4.put("L", 8);
        goto4.put("R", 7);
        ACTION.put(4, action4);
        GOTO.put(4, goto4);

        //Estado 5
        Map<String, String> action5 = new HashMap<>();
        Map<String, Integer> goto5 = new HashMap<>();
        action5.put("$", "r4");
        action5.put("=", "r4");
        ACTION.put(5, action5);
        GOTO.put(5, goto5);

        //Estado 6
        Map<String, String> action6 = new HashMap<>();
        Map<String, Integer> goto6 = new HashMap<>();
        action6.put("*", "s4");
        action6.put("id", "s5");
        goto6.put("L", 8);
        goto6.put("R", 9);
        ACTION.put(6, action6);
        GOTO.put(6, goto6);

        //Estado 7
        Map<String, String> action7 = new HashMap<>();
        Map<String, Integer> goto7 = new HashMap<>();
        action7.put("$", "r3");
        action7.put("=", "r3");
        ACTION.put(7, action7);
        GOTO.put(7, goto7);

        //Estado 8
        Map<String, String> action8 = new HashMap<>();
        Map<String, Integer> goto8 = new HashMap<>();
        action8.put("$", "r5");
        action8.put("=", "r5");
        ACTION.put(8, action8);
        GOTO.put(8, goto8);

        //Estado 9
        Map<String, String> action9 = new HashMap<>();
        Map<String, Integer> goto9 = new HashMap<>();
        action9.put("$", "r1");
        ACTION.put(9, action9);
        GOTO.put(9, goto9);

    }


    
    // Algoritmo Principal SLR(1)
    public boolean analizar(String[] tokens) {
        Stack<Integer> pilaEstados = new Stack<>();
        Stack<String> pilaSimbolos = new Stack<>();

        // Pila para construir el arbol sintactico
        NodoAST arbol = new NodoAST();
        Stack<NodoAST.Nodo> pilaAST = new Stack<>();

        // Estado inicial
        pilaEstados.push(0);

        // Agregar marcador de fin de cadena
        List<String> entrada = new ArrayList<>();

        for(String token : tokens) {
            if(token == null || token.isEmpty() || token.equals("$")) {
                System.out.println("Error: token invalido.");
                return false;
            }
            entrada.add(token);
        }

        entrada.add("$");

        int posicion = 0;

        // Ciclo Shift / Reduce
        while (true) {
            int estado = pilaEstados.peek();
            String tokenActual = entrada.get(posicion);

            // Consultar tabla ACTION
            String accion = obtenerAccion(estado, tokenActual);

            System.out.println("\nEstado: " + estado);
            System.out.println("Token: " + tokenActual);
            System.out.println("Accion: " + accion);

            // Error sintactico de token
            if (accion == null || accion.isEmpty()) {
                System.out.println("Error sintactico en el token: " + tokenActual);
                return false;
            }

            // Shift
            if (accion.startsWith("s")) {
                int siguienteEstado;

                try {
                    siguienteEstado = Integer.parseInt(accion.substring(1));
                } catch (NumberFormatException e) {
                    System.out.println("Accion SHIFT invalida.");
                    return false;
                }

                if (tokenActual.equals("$")) {
                    System.out.println("Error: SHIFT sobre $.");
                    return false;
                }

                pilaSimbolos.push(tokenActual);
                pilaEstados.push(siguienteEstado);

                // Crear nodo terminal
                NodoAST.Nodo nodo = arbol.new Nodo(tokenActual, new NodoAST.Nodo[0]);
                pilaAST.push(nodo);
                posicion++;
                System.out.println("SHIFT al estado " + siguienteEstado);
            }

            // REDUCE
            else if (accion.startsWith("r")) {
                int numeroProduccion;

                try {
                    numeroProduccion = Integer.parseInt(accion.substring(1));
                } catch (NumberFormatException e) {
                    System.out.println("Accion REDUCE invalida.");
                    return false;
                }

                Produccion produccion = producciones.get(numeroProduccion);

                if (produccion == null) {
                    System.out.println("Error: produccion no encontrada: " + numeroProduccion);
                    return false;
                }

                int cantidad = produccion.longitudDerecha;

                // Verificar que existan elementos suficientes para reducir
                if(cantidad < 0 || cantidad > pilaSimbolos.size() || cantidad > pilaAST.size() || cantidad >= pilaEstados.size()) {
                    System.out.println("Error: reduccion invalida.");
                    return false;
                }

                // Hijos de la nueva produccion
                NodoAST.Nodo[] hijos = new NodoAST.Nodo[cantidad];

                // Desapilar simbolos, estados y nodos
                for (int i = cantidad - 1; i >= 0; i--) {
                    pilaSimbolos.pop();
                    pilaEstados.pop();

                    // Conservar el orden original
                    hijos[i] = pilaAST.pop();
                }

                // Crear nuevo nodo no terminal
                NodoAST.Nodo nuevoNodo = arbol.new Nodo(produccion.izquierda, hijos);

                // Consultar GOTO
                int estadoActual = pilaEstados.peek();

                Integer estadoGoto = obtenerGoto(estadoActual, produccion.izquierda);

                if (estadoGoto == null) {
                    System.out.println("Error: GOTO no encontrado.");
                    return false;
                }

                // Apilar el no terminal reducido
                pilaSimbolos.push(produccion.izquierda);
                pilaEstados.push(estadoGoto);
                pilaAST.push(nuevoNodo);

                System.out.println("REDUCE por regla " + numeroProduccion + ": " + produccion.izquierda);
                System.out.println("GOTO al estado " + estadoGoto);
            }

            else if (accion.equalsIgnoreCase("acc")) {
                if (pilaAST.size() == 1) {
                    System.out.println("\n===== ARBOL SINTACTICO =====");
                    arbol.imprimir(pilaAST.peek());
                }
                return true;
            }
            // Accion Desconocida
            else {
                System.out.println("Error: accion desconocida: " + accion);
                return false;
            }
        }
    }

    // Consultar Tabla ACTION
    private String obtenerAccion(int estado, String token) {
        Map<String, String> fila = ACTION.get(estado);

        if (fila == null) {
            return null;
        }

        return fila.get(token);
    }

    // Consultar Tabla GOTO
    private Integer obtenerGoto(int estado, String noTerminal) {

        Map<String, Integer> fila = GOTO.get(estado);

        if (fila == null) {
            return null;
        }

        return fila.get(noTerminal);
    }
}
