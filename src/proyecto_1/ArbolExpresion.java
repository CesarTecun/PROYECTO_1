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

    public void construirArbol(String expresion) {
        String postfija = convertirPostfija(expresion);
        Stack<Nodo> pila = new Stack<>();

        for (int i = 0; i < postfija.length(); i++) {
            char caracter = postfija.charAt(i);
            if (esOperador(caracter)) {
                Nodo nodo = new Nodo(caracter);
                nodo.derecho = pila.pop();
                nodo.izquierdo = pila.pop();
                pila.push(nodo);
            } else if (Character.isLetter(caracter)) {
                if (!variables.containsKey(caracter)) {
                    System.out.println("Ingrese el valor para '" + caracter + "':");
                    Scanner scanner = new Scanner(System.in);
                    int valor = scanner.nextInt();
                    variables.put(caracter, valor);
                }
                pila.push(new Nodo(caracter));
            } else {
                pila.push(new Nodo(caracter));
            }
        }

        raiz = pila.pop();
    }

    private String convertirPostfija(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                resultado.append(caracter);
            } else if (caracter == '(') {
                pila.push(caracter);
            } else if (caracter == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop();
            } else {
                while (!pila.isEmpty() && precedencia(caracter) <= precedencia(pila.peek())) {
                    resultado.append(pila.pop());
                }
                pila.push(caracter);
            }
        }

        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

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

    public void imprimirArbol() {
        imprimirArbol(raiz);
    }

    private void imprimirArbol(Nodo nodo) {
        if (nodo != null) {
            imprimirArbol(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            imprimirArbol(nodo.derecho);
        }
    }

    //////////Imprimir Valores////////////
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
                    throw new ArithmeticException("División por cero");
                }
            default:
                //devolvera de manera entera por medio de la recursividad
                //el resultado de las operaciones
                return (int) Math.pow(izquierdo, derecho);
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
            imprimirArbolGrafico(nodo.derecho, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("   ");
            }
            System.out.println(nodo.dato);
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
}
