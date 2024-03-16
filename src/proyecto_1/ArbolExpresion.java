package proyecto_1;

import java.util.Stack;

/**
 *
 * @author cesar
 */
public class ArbolExpresion {

    Nodo raiz;

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

    public void imprimirConstruccionArbol() {
        imprimirConstruccionArbol(raiz);
    }

    private void imprimirConstruccionArbol(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato);
            if (nodo.izquierdo != null || nodo.derecho != null) {
                
                imprimirConstruccionArbol(nodo.izquierdo);
                if (nodo.derecho != null) {
                   
                }
                imprimirConstruccionArbol(nodo.derecho);
                
            }
        }
    }

    public void imprimirArbolGrafico() {
        imprimirArbolGrafico(raiz, 0);
    }

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
}
