import java.util.ArrayList;
import java.util.List;

public class ArbolBinario
{
    Nodo raiz;

    public ArbolBinario(Nodo raiz){
        this.raiz=raiz;
    }

    public void imprimirArbol()
    {
        int altura = calcularAltura(raiz);
        List<Nodo> nivel = new ArrayList<>();
        
        nivel.add(raiz);

        int anchoTotal = (int)Math.pow(2, altura + 2);

        for(int i = 0; i < altura ; i++)
        {
            int espacios = anchoTotal / (int)Math.pow(2, i + 1);
            int espacio_inicial = espacios / 2;

            imprimirNodos(nivel, espacio_inicial, espacios);

            if(i < altura - 1)
            {
                imprimirRamas(nivel, espacio_inicial, espacios);
            }

            List<Nodo> siguienteNivel = new ArrayList<>();

            for(Nodo nodo: nivel)
            {
                siguienteNivel.add(nodo != null ? nodo.izquierdo:null);
                siguienteNivel.add(nodo != null ? nodo.derecho:null);
            }
            nivel = siguienteNivel;
        }
    }

    private void imprimirNodos(List<Nodo> nivel, int espacio_inicial, int espacios) {
        imprimirEspacios(espacio_inicial);
        for(Nodo nodo : nivel)
        {
            if(nodo != null){
                System.out.printf("%2d", nodo.dato);
            }else{
                System.out.print("  ");
            }
            imprimirEspacios(espacios - 2);
        }
        System.out.println();
    }

    
    private void imprimirRamas(List<Nodo> nivel, int espacio_inicial, int espacios) {    
        
        int alturaRama = 1;
        
        for(int i = 1; i <= alturaRama ; i++)
        {
            
            imprimirEspacios(espacio_inicial - 1);
            for(Nodo nodo : nivel)
            {
                if(nodo == null)
                {
                    imprimirEspacios(espacios);
                    continue;
                }

                System.out.print(nodo.izquierdo != null ? "/" : " ");
                
                imprimirEspacios( 2 * i - i);

                System.out.print(nodo.derecho != null ? "\\" : " ");
                
                imprimirEspacios( espacios - (2 * i + i));
            }
            System.out.println();
        }
    }

    private void imprimirEspacios(int cantidad) {
        for(int i=0 ;i<cantidad;i++)
        {
            System.out.print(" ");
        }
    }

    private int calcularAltura(Nodo nodo){
        if(nodo == null) return 0;
        return 1 + Math.max(calcularAltura(nodo.izquierdo),calcularAltura(nodo.derecho));
    }
    // aca parte 
    public void recorridoPreOrden() {
    System.out.print("Recorrido PreOrden: ");
    preOrden(raiz);
    System.out.println();
}

private void preOrden(Nodo nodo) {
    if (nodo != null) {
        System.out.print(nodo.dato + " ");
        preOrden(nodo.izquierdo);
        preOrden(nodo.derecho);
    }
}

public void recorridoInOrden() {
    System.out.print("Recorrido InOrden: ");
    inOrden(raiz);
    System.out.println();
}

private void inOrden(Nodo nodo) {
    if (nodo != null) {
        inOrden(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrden(nodo.derecho);
    }
}

public void recorridoPostOrden() {
    System.out.print("Recorrido PostOrden: ");
    postOrden(raiz);
    System.out.println();
}

private void postOrden(Nodo nodo) {
    if (nodo != null) {
        postOrden(nodo.izquierdo);
        postOrden(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }
}

    
}