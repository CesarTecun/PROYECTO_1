package proyecto_1;

import java.util.Scanner;


import java.util.Scanner;

public class PROYECTO_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolExpresion arbol = new ArbolExpresion();

        System.out.print("Ingrese la expresión matemática: ");
        String expresion = scanner.nextLine();

        if (!esExpresionValida(expresion)) {
            System.out.println("Error: La expresión ingresada no es válida.");
            return;
        }
        
        System.out.println("La expresión ingresada es válida puedes continuara.");
        arbol.construirArbol(expresion);

        int opcion;
        do {
            System.out.println("\n___________Menú_________:");
            System.out.println("1. Imprimir árbol de expresión");
            System.out.println("2. Imprimir árbol de expresión gráfico");
            System.out.println("3. Recorrido PreOrden");
            System.out.println("4. Recorrido InOrden");
            System.out.println("5. Recorrido PosOrden");
            System.out.println("6. Modificar valores de variables");
            System.out.println("7. Evaluar expresión");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            System.out.flush();  
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
                    System.out.println("\nRecorrido PreOrden:");
                    arbol.recorridoPreOrden();
                    break;
                case 4:
                    System.out.println("\nRecorrido InOrden:");
                    arbol.recorridoInOrden();
                    break;
                case 5:
                    System.out.println("\nRecorrido PosOrden:");
                    arbol.recorridoPosOrden();
                    break;
                case 6:
                    System.out.println("\nModificar valores de variables:");
                    modificarVariables(arbol, scanner);
                    break;
                case 7:
                    System.out.println("\nResultado de la evaluación:");
                    int resultado = arbol.evaluarExpresion();
                    System.out.println("El resultado de la expresión es: " + resultado);
                    System.out.flush();  
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

    private static void modificarVariables(ArbolExpresion arbol, Scanner scanner) {
        System.out.println("Ingrese la variable que desea modificar:");
        char variable = scanner.next().charAt(0);

        if (arbol.variables.containsKey(variable)) {
            System.out.println("El valor actual de '" + variable + "' es: " + arbol.variables.get(variable));
            System.out.println("Ingrese el nuevo valor para '" + variable + "':");
            int nuevoValor = scanner.nextInt();
            arbol.variables.put(variable, nuevoValor);
            System.out.println("Se ha modificado el valor de '" + variable + "' correctamente.");
        } else {
            System.out.println("La variable '" + variable + "' no existe en la expresión.");
        }
    }
    public static boolean esExpresionValida(String expresion) {
        // Verificar que la expresión no sea nula o esté vacía
        if (expresion == null || expresion.isEmpty()) {
            return false;
        }
        
        int parentesis = 0;
        
        // Recorrer cada caracter de la expresión
        for (char c : expresion.toCharArray()) {
            // Verificar si el caracter no es una letra ni un operador válido
            if (!Character.isLetter(c) && c != '+' && c != '-' && c != '*' && c != '/' && c != '^' && c != '(' && c != ')') {
                return false;
            }
            
            // Verificar balanceo de paréntesis
            if (c == '(') {
                parentesis++;
            } else if (c == ')') {
                parentesis--;
                if (parentesis < 0) {
                    return false; // Si hay un paréntesis de cierre sin su correspondiente de apertura
                }
            }
        }
        // Verificar que el balanceo de paréntesis sea correcto
        // Si la expresión ha pasado todas las verificaciones, es válida

        return parentesis == 0;
    }
}
