/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_1;

/**
 *
 * @author pabloispache
 */
class NodoArbol {
    /*se crea dato de tipo objeto, nodo izquierdo y derecho para utilizarlo
    posterior al crear el arbol*/
    
    /*Creamos una variable tipo dato para almacenar*/
    Object dato;
    
    /*creamos nuestra apuntador izquierdo*/
    NodoArbol izquierdo;
    
    /*creamos nuestra apuntador derecho*/
    NodoArbol derecho;
    
    /*Creamos nuestro constructor*/
    public NodoArbol(Object dato){
        this.dato=dato;
        this.izquierdo=null;
        this.derecho=null;
    }
}
