package proyecto_1;

/**
 *
 * @author cesar
 */
public class PilaArbolExp {

    //Definimos una variable privada de tipo Pila, que hara referencia
    //al nodo en la parte superior de la pila.
    private NodoPila tope;

    //Realizamos un constructor vacio.
    public PilaArbolExp() {
        //lo inicializamos como nulo para indicar que la pila
        //esta vacion al principio
        //
        tope = null;

    }

    //////METODO PARA INSERTAR DATOS EN EL NODO//////
    public void insertar(NodoArbol elemento) {
        NodoPila nuevo;
        nuevo = new NodoPila(elemento);
        //Crear un nuevo nodo
        nuevo.siguiente = tope;
        //Le asigno el nodo recien creado como el siguiente nodo
        //para que se vaya al tope de la pila
        tope = nuevo;
        // El tope de la pila apuntara al nuevo nodo
    }

    ////METODO BOOLEANO OPARA DETECTAR PILA VACIA
    public boolean pilaVacia() {
        //devolvera en la variable tope la cual esta en la parte superior
        //de la pila es igual a nulo
        return tope == null;
        //si tope es nulo la pila esta vacia (true) si no es nulo entonces dara un (false)
    }

    ///METODO PARA VERIFICAR EL TOPE DE LA PILA
    public NodoArbol topePila() {
        //Accede al nodo en la parte superior de la pila y obtiene el dato
        //almacenado en el nodo tope.dato
        return tope.dato;
        //Devuelve el dato almacenado en el tope de pila del NodoArbol.
    }

    /////METODO PARA REINICIAR PILA
    public void ReiniciarPila() {
        //Asigna el nulo a la variable tope y
        //elimino la conexion con cualquier nodo anterior en la pila
        tope = null;
        //la pila queda vacia sin ningun dato
    }

    ///METODO QUITAR
    public NodoArbol quitar() {
        //Declaramos una variable auxiliar y la iniciamos en nulo
        NodoArbol aux = null;
        //Verificamos si esta vacia nuestra pila
        //si es diferente a vacio es decir tiene datos
        if (!pilaVacia()) {
            //asignara el dato del nodo en el tope de la pila a la variable
            //auxiliar
            aux = tope.dato;
            //Actualizara el tope de la pila para que aqpunte al siguiente nodo
            //eliminando así el nodo que estaba en la cima de la pila
            tope = tope.siguiente;
        }

        //devolvera nuestra variable auxiliar que sera el dato del nodo que se quito
        return aux;
    }

}
