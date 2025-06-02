public class ArbolDesdeMatriz {
    public static Nodo construirDesdeAdyacencia(int[][] matriz, int[] valores){
        int n = valores.length;
        Nodo[] nodos = new Nodo[n];
        
        for(int i = 0; i < n ; i++ )
            nodos[i] = new Nodo(valores[i]);
        
        for (int i = 0; i < n; i++)
        {
            int hijos = 0;
            for( int j = 0; j < n ; j++)
            {
                if(matriz[i][j] != 0)
                {
                    if(hijos == 0) nodos[i].izquierdo = nodos[j];
                    else if( hijos == 1) nodos[i].derecho = nodos[j];
                    hijos++;
                }
            }    
        }        

        return nodos[0];
    }

    public static Nodo construirDesdeIncidencia(int[][] matriz, int[] valores){
        int n = valores.length;
        Nodo[] nodos = new Nodo[n];

        for(int i = 0; i < n ; i++ )
        {
            nodos[i] = new Nodo(valores[i]);
        }

        for(int j = 0; j < matriz[0].length; j++){
            int origen = -1, destino = -1;
            for(int i = 0; i < n; i++){
                if(matriz[i][j] == -1) origen = i;
                if(matriz[i][j] == 1) destino = i;
            }

            if(origen != -1 && destino != -1){
                if(nodos[origen].izquierdo == null)
                    nodos[origen].izquierdo = nodos[destino];
                else
                    nodos[origen].derecho = nodos[destino];    
            }
        }

            boolean[] esDestino = new boolean[n];

            for(int j = 0; j< matriz[0].length; j++){
                for(int i = 0;i < n; i++){
                    if(matriz[i][j] == 1){
                        esDestino[i] = true;
                    }
                }
            }

            for(int i = 0;i < n;i++)
            {
                if(!esDestino[i]) return nodos[i];
            }    

        return nodos[0];
    }
}
