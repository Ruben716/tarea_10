import java.util.*;

public class ArbolBinario
{
    Nodo raiz;

    public ArbolBinario(Nodo raiz){
        this.raiz=raiz;
    }

    /**
     * 
     */
    public void imprimirArbol()
    {
        int altura = calcularAltura(raiz);
        //Queue<Nodo> cola = new LinkedList<>();
        List<Nodo> nivel = new ArrayList<>();
        //cola.add(raiz) ;
        nivel.add(raiz);

        /*
        int nivelActual = 1;

        while (nivelActual <= altura){
            int nodosNivel = (int)Math.pow(2, nivelActual -1);
            int espacio = (int)Math.pow(2, altura - nivelActual + 1);

            imprimirEspacios(espacio);

            for(int i=0;i<nodosNivel;i++)
            {
                Nodo actual = cola.poll();
                if(actual != null)
                {
                    System.out.print(actual.dato);
                    cola.add(actual.izquierdo);
                    cola.add(actual.derecho);
                    cola.add(null);
                    cola.add(null);
                }
                
            }
            imprimirEspacios(espacio);
                System.out.println();
                if (nivelActual != altura) {
                    imprimirRamas(cola,altura-nivelActual);
                }
            nivelActual++;    
        }*/
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

    //private void imprimirRamas(Queue<Nodo> cola, int espacios) {
    private void imprimirRamas(List<Nodo> nivel, int espacio_inicial, int espacios) {    
        //int cantidad = cola.size();
        int alturaRama = 1;
        //for(int i = 0;i<cantidad;i++)
        for(int i = 1; i <= alturaRama ; i++)
        {
            //imprimirEspacios(cantidad);
            //Nodo izquierda = cola.poll();
            //Nodo derecho = cola.poll();
            //System.out.print("/");
            //System.out.print(" ");
            //System.out.println();
            imprimirEspacios(espacio_inicial - 1);
            for(Nodo nodo : nivel)
            {
                if(nodo == null)
                {
                    imprimirEspacios(espacios);
                    continue;
                }

                System.out.print(nodo.izquierdo != null ? "/" : " ");
                //if(nodo.izquierdo != null)
                //{
                //    System.out.print("/");
                //}else{
                //    System.out.print(" ");
                //}
                imprimirEspacios( 2 * i - i);

                System.out.print(nodo.derecho != null ? "\\" : " ");
                //if(nodo.izquierdo != null)
                //{
                //    System.out.print("/");
                //}else{
                //    System.out.print(" ");
                //}
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