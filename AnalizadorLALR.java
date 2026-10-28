
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
        producciones.put(1, new Produccion("S'", 3)); // S -> L=R
        producciones.put(2, new Produccion("S", 1));  // S -> R
        //CAMBIAR AQUI POR  SI D ES PAR O IMPAR
        //producciones.put(3, new Produccion("L", 2));  // L -> *R
        //producciones.put(3, new Produccion("L", 2)); // L -> & R
        producciones.put(4, new Produccion("L", 1));  // L -> id
        producciones.put(5, new Produccion("R", 1));  // R -> L

       
    }

    private void inicializarTablas() {
        //Tabla de la Gramatica 
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
