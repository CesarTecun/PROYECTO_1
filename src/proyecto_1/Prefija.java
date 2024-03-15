package proyecto_1;

/**
 *
 * @author cesar
 */
public class Prefija {

    /**
     *
     * @author cesar
     */
    public class postfija {

        public static Pila_Arbol Conversione(String encadenar) {

            encadenar += ')';
            int tamaño = encadenar.length();
            // para que busque el número de caracteres de la cadena
            //y devolverme el número
            Pila_Arbol salir = new Pila_Arbol(tamaño);
            //LLamo a las clases
            Pila_Arbol pila = new Pila_Arbol(tamaño);
            pila.push('(');
            //Basicamente indico que dentro de () buscara y apilara
            //solo si esta ese signo
            try {
                for (int i = tamaño - 1; i >= 0; i--) {
                    char datos = encadenar.charAt(i);
                    switch (datos) {
                        case '(':
                            pila.push(datos);
                            break;
                        case '+':
                        case '-':
                        case '^':
                        case '*':
                        case '/':
                            while (Jerarquia_OP(datos) <= Jerarquia_OP(pila.nextPop())) {
                                salir.push(pila.pop());
                            }
                            pila.push(datos);
                            break;
                        case ')':
                            while (pila.nextPop() != '(') {
                                salir.push(pila.pop());
                            }
                            pila.pop();
                            break;
                        default:
                            salir.push(datos);
                    }
                }
            } catch (Throwable e) {

            }
            return salir;
        }

        public static int Jerarquia_OP(char ope) {
            int jerarquia = 0;
            String op = String.valueOf(ope);
            //Cuando es un parentesis abierto es porque voy a operar
            //muchas mas cantidades
            if (op.equals(")")) {
                jerarquia = 1;
            }
            if (op.equals("^")) {
                jerarquia = 4;
            }
            if (op.equals("*") || op.equals("/")) {
                jerarquia = 3;
            }
            if (op.equals("+")) {
                jerarquia = 2;
            }

            if (op.equals("-")) {
                jerarquia = 5;
            }
            return jerarquia;
        }
    }
}