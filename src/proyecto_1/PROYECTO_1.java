package proyecto_1;

import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class PROYECTO_1 {

    public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
        ArbolExpresion arbol = new ArbolExpresion();

        System.out.print("Ingrese la expresión matemática: ");
        String expresion = scanner.nextLine();
        arbol.construirArbol(expresion);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Imprimir árbol de expresión");
            System.out.println("2. Imprimir árbol de expresión gráfico");
            System.out.println("3. Imprimir construcción del árbol");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\nÁrbol de expresión:");
                    arbol.imprimirArbol();
                    break;
                case 2:
                    System.out.println("\nÁrbol de expresión gráfico:");
                    arbol.imprimirArbolGrafico();
                    break;
                case 3:
                    System.out.println("\nConstrucción del árbol:");
                    arbol.imprimirConstruccionArbol();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
