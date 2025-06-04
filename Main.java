import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args){
              
        int opcion;
        
        do{
            System.out.println("\n============= [ MENÚ ] =============");
            System.out.println("1. Ingresar por matriz de Adyacencia");
            System.out.println("2. Ingresar por matriz de Incidencia");
            System.out.println("3. Ingresar datos para generar Árbol ID3");

            System.out.println("0. Salir");


            opcion = teclado.nextInt();
            
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
                case 3:
                    ArbolID3.ejecutar();
                    break;

            
                default:
                    System.out.println("¡Opción inválida!");
                    break;
            }

        }while(opcion != 0);
        
        teclado.close();
    }

    public static void ingresarMatrizAdyacencia(){
            System.out.println("Ingresar el número de nodos");
            int n = teclado.nextInt();

            int[][] matriz = new int[n][n];
            int[] valores = new int[n];

            System.out.println("Ingrese los valores de los nodos:");
            for(int i = 0 ;i < n ; i++){
                System.out.print("Valor del nodo " + i + " : ");
                valores[i] = teclado.nextInt();
            }

            System.out.println("Ingrese la matriz de adyacencia:");
            for(int i = 0; i < n ;i++){
                for(int j = 0; j < n ; j++){
                    System.out.print("¿Existe conexion de " + valores[i] + " a " + valores[j] + " ? (0/1): ");
                    matriz[i][j] = teclado.nextInt();
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
        int n = teclado.nextInt();

        System.out.println("Ingresar el número de aristas");
        int m = teclado.nextInt();


        int[][] matriz = new int[n][m];
        int[] valores = new int[n];

        System.out.println("Ingrese los valores de los nodos:");

        for(int i = 0; i < n ;i++){
            System.out.print("Valor del nodo " + i + ": ");
            valores[i] = teclado.nextInt();
        }
        
        System.out.println("Ingrese la matriz de incidencia (-1: origen, 1: destino, 0: vacio):");
        for(int j = 0; j < m; j++){
            for(int i = 0; i < n ; i++){
                System.out.print("T|[" + i + "][" + j +"]: ");
                matriz[i][j] = teclado.nextInt();
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
