import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ArbolID3 {

    static Scanner sc = new Scanner(System.in);

    public static void ejecutar() {
        System.out.print("Número de atributos (sin incluir la clase): ");
        int numAtributos = sc.nextInt();
        sc.nextLine();

        List<String> atributos = new ArrayList<>();
        System.out.println("Ingrese los nombres de los atributos:");
        for (int i = 0; i < numAtributos; i++) {
            System.out.print("Atributo " + (i + 1) + ": ");
            atributos.add(sc.nextLine());
        }

        System.out.print("Nombre del atributo de clase (Ej: Jugar): ");
        String atributoClase = sc.nextLine();
        atributos.add(atributoClase);

        System.out.print("Número de ejemplos (filas): ");
        int numEjemplos = sc.nextInt();
        sc.nextLine();

        List<Map<String, String>> ejemplos = new ArrayList<>();

        System.out.println("Ingrese los valores de los ejemplos:");
        for (int i = 0; i < numEjemplos; i++) {
            Map<String, String> ejemplo = new HashMap<>();
            System.out.println("Ejemplo " + (i + 1) + ":");
            for (String atributo : atributos) {
                System.out.print("  " + atributo + ": ");
                ejemplo.put(atributo, sc.nextLine());
            }
            ejemplos.add(ejemplo);
        }

        NodoID3 raiz = construirArbol(ejemplos, atributos.subList(0, atributos.size() - 1), atributoClase);
        System.out.println("\nÁrbol ID3 generado:\n");
        imprimirArbol(raiz, "", "");

    }

    public static NodoID3 construirArbol(List<Map<String, String>> datos, List<String> atributos, String atributoClase) {
        Set<String> clases = new HashSet<>();
        for (Map<String, String> d : datos)
            clases.add(d.get(atributoClase));

        if (clases.size() == 1) {
            NodoID3 hoja = new NodoID3(null);
            hoja.setHoja(clases.iterator().next());
            return hoja;
        }

        if (atributos.isEmpty()) {
            String mayoritaria = claseMayoritaria(datos, atributoClase);
            NodoID3 hoja = new NodoID3(null);
            hoja.setHoja(mayoritaria);
            return hoja;
        }

        String mejorAtributo = seleccionarMejorAtributo(datos, atributos, atributoClase);
        NodoID3 nodo = new NodoID3(mejorAtributo);

        Set<String> valores = new HashSet<>();
        for (Map<String, String> d : datos)
            valores.add(d.get(mejorAtributo));

        for (String valor : valores) {
            List<Map<String, String>> subconjunto = new ArrayList<>();
            for (Map<String, String> d : datos) {
                if (d.get(mejorAtributo).equals(valor)) {
                    subconjunto.add(d);
                }
            }

            if (subconjunto.isEmpty()) {
                NodoID3 hoja = new NodoID3(null);
                hoja.setHoja(claseMayoritaria(datos, atributoClase));
                nodo.agregarHijo(valor, hoja);
            } else {
                List<String> nuevosAtributos = new ArrayList<>(atributos);
                nuevosAtributos.remove(mejorAtributo);
                NodoID3 hijo = construirArbol(subconjunto, nuevosAtributos, atributoClase);
                nodo.agregarHijo(valor, hijo);
            }
        }

        return nodo;
    }

    public static String claseMayoritaria(List<Map<String, String>> datos, String atributoClase) {
        Map<String, Integer> frecuencia = new HashMap<>();
        for (Map<String, String> d : datos) {
            String clase = d.get(atributoClase);
            frecuencia.put(clase, frecuencia.getOrDefault(clase, 0) + 1);
        }
        return Collections.max(frecuencia.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static String seleccionarMejorAtributo(List<Map<String, String>> datos, List<String> atributos, String atributoClase) {
        double mejorGanancia = -1;
        String mejorAtributo = atributos.get(0);

        for (String atributo : atributos) {
            double ganancia = calcularGanancia(datos, atributo, atributoClase);
            if (ganancia > mejorGanancia) {
                mejorGanancia = ganancia;
                mejorAtributo = atributo;
            }
        }

        return mejorAtributo;
    }

    public static double calcularGanancia(List<Map<String, String>> datos, String atributo, String atributoClase) {
        double entropiaTotal = calcularEntropia(datos, atributoClase);
        Map<String, List<Map<String, String>>> particiones = new HashMap<>();

        for (Map<String, String> d : datos) {
            String valor = d.get(atributo);
            particiones.putIfAbsent(valor, new ArrayList<>());
            particiones.get(valor).add(d);
        }

        double entropiaCondicional = 0;
        for (List<Map<String, String>> particion : particiones.values()) {
            double peso = (double) particion.size() / datos.size();
            entropiaCondicional += peso * calcularEntropia(particion, atributoClase);
        }

        return entropiaTotal - entropiaCondicional;
    }

    public static double calcularEntropia(List<Map<String, String>> datos, String atributoClase) {
        Map<String, Integer> frecuencia = new HashMap<>();
        for (Map<String, String> d : datos) {
            String clase = d.get(atributoClase);
            frecuencia.put(clase, frecuencia.getOrDefault(clase, 0) + 1);
        }

        double entropia = 0;
        for (int f : frecuencia.values()) {
            double p = (double) f / datos.size();
            entropia -= p * (Math.log(p) / Math.log(2));
        }

        return entropia;
    }

    public static void imprimirArbol(NodoID3 nodo, String prefijo, String conector) {
    if (nodo.esHoja) {
        System.out.println(prefijo + conector + "→ Clase: " + nodo.clase);
        return;
    }

    int totalHijos = nodo.hijos.size();
    int i = 0;

    for (Map.Entry<String, NodoID3> entrada : nodo.hijos.entrySet()) {
        boolean esUltimo = (++i == totalHijos);
        String nuevoConector = esUltimo ? "└── " : "├── ";
        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

        System.out.println(prefijo + conector + nodo.atributo + " = " + entrada.getKey());
        imprimirArbol(entrada.getValue(), nuevoPrefijo, nuevoConector);
    }
}

}
