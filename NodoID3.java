import java.util.HashMap;
import java.util.Map;

public class NodoID3 {
    String atributo; // Nombre del atributo, o valor de clase si es hoja
    boolean esHoja;
    String clase; // Solo si es hoja
    Map<String, NodoID3> hijos;

    public NodoID3(String atributo) {
        this.atributo = atributo;
        this.esHoja = false;
        this.hijos = new HashMap<>();
    }

    public void setHoja(String clase) {
        this.clase = clase;
        this.esHoja = true;
    }

    public void agregarHijo(String valor, NodoID3 hijo) {
        hijos.put(valor, hijo);
    }
}
