import java.util.Scanner;

public class Main {
    static Scanner leer_dato = new Scanner(System.in);

    public static void main(String[] args){
                //Nodo subarbolIzquierdo = new Nodo(5, null, null);
        //Nodo nodo12 = new Nodo(12, null, null);
        //Nodo nodo20 = new Nodo(20, null, null);

        //Nodo nodo15 = new Nodo(15, nodo12, nodo20);
        //Nodo nodo5 = new Nodo(5, null, null);

        //Nodo raiz = new Nodo(10, nodo5,nodo15);

        //ArbolBinario arbol = new ArbolBinario(raiz);

        //arbol.imprimirArbol();

        //arbol.raiz =new Nodo(1,subarbolIzquierdo,subarbolDerecho);

        //System.out.println("Arbol binario credo con T = (1,5,10)");

        //arbol.imprimirArbol(arbol.raiz);
        int opcion;
        
        do{
            System.out.println("\n============= [ MENÚ ] =============");
            System.out.println("1. Ingresar por matriz de Adyacencia");
            System.out.println("2. Ingresar por matriz de Incidencia");
            System.out.println("0. Salir");


            opcion = leer_dato.nextInt();
            
            switch (opcion) {
                case 1:
                    ingresarMatrizAdyacencia();
                    break;
                
                case 2:
                    ingresarMatrizIncidencia();
                    break;
                
                case 0:
                    System.out.println("Programa finalizado");
                    break;
            
                default:
                    System.out.println("¡Opción inválida!");
                    break;
            }

        }while(opcion != 0);
        
        leer_dato.close();
    }

    public static void ingresarMatrizAdyacencia(){
            System.out.println("Ingresar el número de nodos");
            int n = leer_dato.nextInt();

            int[][] matriz = new int[n][n];
            int[] valores = new int[n];

            System.out.println("Ingrese los valores de los nodos:");
            for(int i = 0 ;i < n ; i++){
                System.out.print("Valor del nodo " + i + " : ");
                valores[i] = leer_dato.nextInt();
            }

            System.out.println("Ingrese la matriz de adyacencia:");
            for(int i = 0; i < n ;i++){
                for(int j = 0; j < n ; j++){
                    System.out.print("¿Existe conexion de " + valores[i] + " a " + valores[j] + " ? (0/1): ");
                    matriz[i][j] = leer_dato.nextInt();
                }
            }

            Nodo raiz = ArbolDesdeMatriz.construirDesdeAdyacencia(matriz, valores);
            ArbolBinario arbol = new ArbolBinario(raiz);

            System.out.println("\nÁrbol binario generado desde una matiz de adyacencia:");
            arbol.imprimirArbol();
            arbol.recorridoPreOrden();
            arbol.recorridoInOrden();
            arbol.recorridoPostOrden();

    }

    public static void ingresarMatrizIncidencia(){
        System.out.println("Ingresar el número de nodos");
        int n = leer_dato.nextInt();

        System.out.println("Ingresar el número de aristas");
        int m = leer_dato.nextInt();


        int[][] matriz = new int[n][m];
        int[] valores = new int[n];

        System.out.println("Ingrese los valores de los nodos:");

        for(int i = 0; i < n ;i++){
            System.out.print("Valor del nodo " + i + ": ");
            valores[i] = leer_dato.nextInt();
        }
        
        System.out.println("Ingrese la matriz de incidencia (-1: origen, 1: destino, 0: vacio):");
        for(int j = 0; j < m; j++){
            for(int i = 0; i < n ; i++){
                System.out.print("T|[" + i + "][" + j +"]: ");
                matriz[i][j] = leer_dato.nextInt();
            }
        }

        Nodo raiz = ArbolDesdeMatriz.construirDesdeIncidencia(matriz, valores);
        ArbolBinario arbol = new ArbolBinario(raiz);

        arbol.imprimirArbol();
        arbol.recorridoPreOrden();
        arbol.recorridoInOrden();
        arbol.recorridoPostOrden();

    }
}
