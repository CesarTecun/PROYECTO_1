package proyecto_1;

import java.util.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ArbolExpresion {

    Nodo raiz;
    Map<Character, Integer> variables;

    static class Nodo {

        char dato;
        Nodo izquierdo, derecho;

        Nodo(char dato) {
            this.dato = dato;
            izquierdo = derecho = null;
        }
    }

    public ArbolExpresion() {
        raiz = null;
        variables = new HashMap<>();
    }

    ////////////FORMA DE CONSTRUCCION DEL ARBOL/////////
    public void construirArbol(String expresion) {
        // Convertir la expresión a notación postfija
        String postfija = convertirPostfija(expresion);

        // Crear una pila para almacenar los nodos del árbol
        Stack<Nodo> pila = new Stack<>();

        // Recorrer la expresión postfija caracter por caracter
        for (int i = 0; i < postfija.length(); i++) {
            char caracter = postfija.charAt(i);
            if (esOperador(caracter)) {
                // Si el caracter es un operador, crear un nodo para el operador
                Nodo nodo = new Nodo(caracter);
                if (caracter == '-' && (i == 0 || esOperador(postfija.charAt(i - 1)))) {
                    // Operador de negación unaria
                    Nodo operando = pila.pop();
                    Nodo negacion = new Nodo(caracter);
                    negacion.izquierdo = operando;
                    pila.push(negacion);
                } else {
                    // Operador binario, asignar nodos izquierdo y derecho al nodo operador
                    nodo.derecho = pila.pop();
                    nodo.izquierdo = pila.pop();
                    pila.push(nodo);
                }
            } else if (Character.isLetter(caracter)) {
                // Caracter es una letra (variable), agregar nodo correspondiente a la pila
                if (!variables.containsKey(caracter)) {
                    // Solicitar valor al usuario si la variable no está en el mapa de variables
                    System.out.println("Ingrese el valor para '" + caracter + "':");
                    Scanner scanner = new Scanner(System.in);
                    int valor = scanner.nextInt();
                    variables.put(caracter, valor);
                }
                pila.push(new Nodo(caracter));
            } else if (Character.isDigit(caracter) || (caracter == '-' && i + 1 < postfija.length() && Character.isDigit(postfija.charAt(i + 1)))) {
                // Caracter es un número (positivo o negativo), agregar nodo correspondiente a la pila
                StringBuilder numeroBuilder = new StringBuilder();
                while ((Character.isDigit(caracter) || caracter == '-') && i < postfija.length()) {
                    numeroBuilder.append(caracter);
                    i++;
                    if (i < postfija.length()) {
                        caracter = postfija.charAt(i);
                    }
                }
                int numero = Integer.parseInt(numeroBuilder.toString());
                pila.push(new Nodo((char) numero));
                i--; // Disminuir i en 1 para compensar el aumento en el bucle for
            } else {
                // Caracter no es operador, letra ni número, agregar nodo correspondiente a la pila
                pila.push(new Nodo(caracter));
            }
        }

        // La raíz del árbol es el último nodo en la pila después de procesar toda la expresión
        raiz = pila.pop();
    }

    private String convertirPostfija(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                // Si el caracter es alfanumérico, agregarlo directamente al resultado
                resultado.append(caracter);
                // Si el siguiente carácter es un paréntesis '(', agregar '*' para indicar multiplicación implícita
                if (i + 1 < expresion.length() && expresion.charAt(i + 1) == '(') {
                    resultado.append('*');
                }
            } else if (caracter == '(') {
                // Si es un paréntesis '(', agregarlo a la pila
                pila.push(caracter);
            } else if (caracter == ')') {
                // Si es un paréntesis ')', desapilar operadores hasta encontrar '(' y agregarlos al resultado
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop(); // Eliminar el '(' de la pila
            } else {
                // Si es un operador, desapilar operadores con mayor o igual precedencia y agregarlos al resultado
                while (!pila.isEmpty() && precedencia(caracter) <= precedencia(pila.peek())) {
                    resultado.append(pila.pop());
                }
                pila.push(caracter); // Agregar el operador a la pila
            }
        }

        // Desapilar operadores restantes y agregarlos al resultado
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString(); // Devolver la expresión en notación postfija
    }

    ////DETERMINA LA PRIORIDAD DE UNA OPREACION ARITMETICA  
    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private boolean esOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '^';
    }

    /**
     * Imprime el árbol de expresión de manera inorden (izquierda-raíz-derecha).
     * Se realiza una llamada al método privado imprimirArbol(Nodo nodo) pasando
     * la raíz del árbol como argumento.
     */
    public void imprimirArbol() {
        imprimirArbol(raiz);
    }

    /*
      Imprime recursivamente el árbol de expresión en orden inorden. El orden
      de impresión es: izquierda - raíz - derecha.
     
      @param nodo El nodo actual a imprimir.
     */
    private void imprimirArbol(Nodo nodo) {
        if (nodo != null) {
            imprimirArbol(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            imprimirArbol(nodo.derecho);
        }
    }

    /*
      Imprime los valores de las variables almacenadas en el mapa de variables.
      Itera a través de las entradas del mapa e imprime cada variable junto con
      su valor.
     */
    private void imprimirValores() {
        System.out.println("Valores de las variables:");
        for (Map.Entry<Character, Integer> entry : variables.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
///////Evalua expresiones para llamarlo en el main////////

    public int evaluarExpresion() {
        return evaluarExpresion(raiz);
    }

    /////Evaluar las expresiones aritmeticas basicas///
    private int evaluarExpresion(Nodo nodo) {
        if (nodo == null) {
            //Verifica si el nodo es nulo
            return 0;
            //y devuelve 0
        }
        //Compruieba si el dato no es una letra
        //si es una letra, devuelve el valor asociado a esa variable en el mapa de variables
        if (Character.isLetter(nodo.dato)) {
            return variables.get(nodo.dato);
        }

        // Si el dato del nodo es un operador unario '-', se evalúa negación unaria antes de la potenciación
        if (nodo.dato == '-') {
            if (nodo.izquierdo != null && nodo.izquierdo.dato == '^') {
                return -evaluarExpresion(nodo.izquierdo.derecho); // Evaluar la potencia negativa
            } else {
                return -evaluarExpresion(nodo.izquierdo); // Evaluar la negación unaria
            }
        }
        //si no es una letra, recursivamente evalua las expresiones
        //en los nodos izquierdo y derecho
        int izquierdo = evaluarExpresion(nodo.izquierdo);
        int derecho = evaluarExpresion(nodo.derecho);
        //Utiliza un swithc para realizar las operaciones aritmeticas
        switch (nodo.dato) {
            case '+':
                return izquierdo + derecho;
            case '-':
                return izquierdo - derecho;
            case '*':
                return izquierdo * derecho;
            case '/':

                if (derecho != 0) {
                    return izquierdo / derecho;
                } else {
                    throw new ArithmeticException("Division por cero error");
                }

            case '^':
                return (int) Math.pow(izquierdo, derecho);
            default:
                //devolvera de manera entera por medio de la recursividad
                //el resultado de las operaciones
                return 0;
        }
    }

    //LLamar a los recorridos para implementar en el menu
    public void recorridoPreOrden() {
        recorridoPreOrden(raiz);
    }

    public void recorridoInOrden() {
        recorridoInOrden(raiz);
    }

    public void recorridoPosOrden() {
        recorridoPosOrden(raiz);
    }

    //Recorrido preOrden RID
    private void recorridoPreOrden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato);
            if (nodo.izquierdo != null || nodo.derecho != null) {
                recorridoPreOrden(nodo.izquierdo);
                if (nodo.derecho != null) {
                    recorridoPreOrden(nodo.derecho);
                }
            }
        }
    }

    ///InOrden IRD
    private void recorridoInOrden(Nodo nodo) {
        if (nodo != null) {
            if (nodo.izquierdo != null) {
                recorridoInOrden(nodo.izquierdo);
            }
            System.out.print(nodo.dato);
            if (nodo.derecho != null) {
                recorridoInOrden(nodo.derecho);
            }
        }
    }

    //PostOrden IDR
    private void recorridoPosOrden(Nodo nodo) {
        if (nodo != null) {
            if (nodo.izquierdo != null) {
                recorridoPosOrden(nodo.izquierdo);
            }
            if (nodo.derecho != null) {
                recorridoPosOrden(nodo.derecho);
            }
            System.out.print(nodo.dato);
        }
    }

    //Metodo para llamar a la impresion
    public void imprimirArbolGrafico() {
        imprimirArbolGrafico(raiz, 0);
    }

    //ImprimirArbolGrafico con los nodos y los niveles
    private void imprimirArbolGrafico(Nodo nodo, int nivel) {
        if (nodo != null) {
            // Imprimir el subárbol derecho primero para mantener el orden correcto
            imprimirArbolGrafico(nodo.derecho, nivel + 1);

            // Imprimir espacios para representar la indentación según el nivel del nodo
            for (int i = 0; i < nivel; i++) {
                System.out.print("   "); // Cada nivel se representa con 3 espacios
            }

            // Imprimir el dato del nodo (valor o operador)
            System.out.println(nodo.dato);

            // Llamar recursivamente para imprimir el subárbol izquierdo
            imprimirArbolGrafico(nodo.izquierdo, nivel + 1);
        }
    }

    public static void main(String[] args) {
         // Crear una instancia de la clase ArbolExpresion
        ArbolExpresion arbol = new ArbolExpresion();
        // Construir el árbol de expresión a partir de la cadena "a+b*c"
        arbol.construirArbol("a+b*c");
        // Imprimir el árbol de expresión
        arbol.imprimirArbol();
    }
    //Checha
}