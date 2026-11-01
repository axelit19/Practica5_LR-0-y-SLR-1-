Pruebas.java

import java.util.ArrayList;
import java.util.List;

public class Pruebas {

    public static class CasoPrueba {
        public String descripcion;
        public List<String> tokens;
        public boolean esValidaEsperada;

        public CasoPrueba(String descripcion, List<String> tokens, boolean esValidaEsperada) {
            this.descripcion = descripcion;
            this.tokens = tokens;
            this.esValidaEsperada = esValidaEsperada;
        }
    }

    public static List<CasoPrueba> obtenerCasosPrueba() {
        // Especificar <CasoPrueba> explícitamente evita conflictos en JDK 1.8
        List<CasoPrueba> pruebas = new ArrayList<CasoPrueba>();

    
        List<String> v1 = new ArrayList<String>();
        v1.add("id"); v1.add("="); v1.add("id");
        pruebas.add(new CasoPrueba("VÁLIDA 1: Asignación directa (id = id)", v1, true));

        // 2. Apuntador a la izquierda: *id = id
        List<String> v2 = new ArrayList<String>();
        v2.add("*"); v2.add("id"); v2.add("="); v2.add("id");
        pruebas.add(new CasoPrueba("VÁLIDA 2: Apuntador a la izquierda (*id = id)", v2, true));

        // 3. Apuntador a la derecha: id = *id
        List<String> v3 = new ArrayList<String>();
        v3.add("id"); v3.add("="); v3.add("*"); v3.add("id");
        pruebas.add(new CasoPrueba("VÁLIDA 3: Apuntador a la derecha (id = *id)", v3, true));

        // 4. Apuntadores en ambos lados: *id = *id
        List<String> v4 = new ArrayList<String>();
        v4.add("*"); v4.add("id"); v4.add("="); v4.add("*"); v4.add("id");
        pruebas.add(new CasoPrueba("VÁLIDA 4: Apuntadores ambos lados (*id = *id)", v4, true));

        // 5. Apuntadores múltiples encadenados: **id = id
        List<String> v5 = new ArrayList<String>();
        v5.add("*"); v5.add("*"); v5.add("id"); v5.add("="); v5.add("id");
        pruebas.add(new CasoPrueba("VÁLIDA 5: Doble desreferencia (**id = id)", v5, true));

        // -------------------------------------------------------------
        // CADENAS INVÁLIDAS (5 Casos)
        // -------------------------------------------------------------
        
        // 1. Expresión incompleta tras igual: id =
        List<String> i1 = new ArrayList<String>();
        i1.add("id"); i1.add("=");
        pruebas.add(new CasoPrueba("INVÁLIDA 1: Asignación incompleta (id =)", i1, false));

        // 2. Inicio directo con operador de asignación: = id
        List<String> i2 = new ArrayList<String>();
        i2.add("="); i2.add("id");
        pruebas.add(new CasoPrueba("INVÁLIDA 2: Operador igual al inicio (= id)", i2, false));

        // 3. Operador unario huérfano: *
        List<String> i3 = new ArrayList<String>();
        i3.add("*");
        pruebas.add(new CasoPrueba("INVÁLIDA 3: Operador unario huérfano (*)", i3, false));

        // 4. Identificadores consecutivos sin operador: id id
        List<String> i4 = new ArrayList<String>();
        i4.add("id"); i4.add("id");
        pruebas.add(new CasoPrueba("INVÁLIDA 4: Tokens consecutivos sin operador (id id)", i4, false));

        // 5. Apuntador seguido inmediatamente de igual: *= id
        List<String> i5 = new ArrayList<String>();
        i5.add("*"); i5.add("="); i5.add("id");
        pruebas.add(new CasoPrueba("INVÁLIDA 5: Operador unario seguido de igual (*= id)", i5, false));

        return pruebas;
    }

    public static void ejecutarPruebas(AnalizadorLALR analizador) {
        System.out.println("====== EJECUCIÓN DE VECTORES DE PRUEBA AUTOMÁTICOS ======\n");
        List<CasoPrueba> listaPruebas = obtenerCasosPrueba();
        
        int pasadas = 0;
        int total = listaPruebas.size();

        for (int i = 0; i < total; i++) {
            CasoPrueba prueba = listaPruebas.get(i);
            System.out.println("------------------------------------------------------------------");
            System.out.println("Prueba [" + (i + 1) + "]: " + prueba.descripcion);
            System.out.println("Tokens de entrada: " + prueba.tokens);

            // Conversión segura del ArrayList a arreglo de Strings
            String[] arregloTokens = prueba.tokens.toArray(new String[0]);
            
            boolean resultado = analizador.analizar(arregloTokens);

            if (resultado == prueba.esValidaEsperada) {
                System.out.println("\n>>> Estado Final: PRUEBA PASÓ (Resultado esperado: " + (prueba.esValidaEsperada ? "Válida" : "Inválida") + ")");
                pasadas++;
            } else {
                System.out.println("\n>>> Estado Final: PRUEBA FALLÓ (Se obtuvo un resultado incorrecto)");
            }
        }

        System.out.println("\n==================================================================");
        System.out.println("Resumen General: " + pasadas + "/" + total + " pruebas pasadas con éxito.");
        System.out.println("==================================================================\n");
    }
}
