public class NodoAST {

    public class Nodo {
        String simbolo;
        Nodo[] hijos;

        public Nodo(String simbolo, Nodo[] hijos) {
            this.simbolo = simbolo;
            this.hijos = hijos;
        }
    }

    // Mostrar el arbol sintactico
    private void mostrar(Nodo nodo, int nivel) {

        if (nodo == null) {
            return;
        }

        // Imprimir espacios segun el nivel
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        // Mostrar el simbolo
        System.out.println(nodo.simbolo);

        // Mostrar los hijos
        if (nodo.hijos != null) {
            for (Nodo hijo : nodo.hijos) {
                mostrar(hijo, nivel + 1);
            }
        }
    }

    // Metodo publico para imprimir el arbol
    public void imprimir(Nodo raiz) {
        mostrar(raiz, 0);
    }
}

