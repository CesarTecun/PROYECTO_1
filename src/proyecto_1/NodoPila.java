/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_1;

/**
 *
 * @author Victor
 */
public class NodoPila {
     //Almacena la informacion que recibe en dato
    NodoArbol dato;
    //Almacena la informacion e interactua para agregar el siguiente valor
    NodoPila siguiente;
 //constructor que recibe un parametro de NodoArbol
    public NodoPila(NodoArbol dato){
        this.dato=dato;
        //se inicializa el campo en null
        this.siguiente=null;
    }
}
