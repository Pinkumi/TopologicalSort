package com.topologicalsort.Tools;

/**
 * Clase que representa una pila
 * @Autor Yael Alfonso Limon Orozco
 * @param <T>
 * @Version 1.0.0
 */
public class Pila<T>{
    private T[] pila;
    private int tope;
    public Pila(int capacidad){
        pila = (T[]) new Object[capacidad]; //Creamos el arreglo de objetos T
        tope= -1;
    }
    public Pila(){
        pila = (T[]) new Object[10];
        tope= -1;
    }
    public void push(T dato){//Agrega un dato a la pila
        if (isFull()){
            System.out.println("Desbordamiento");
        }else{
            tope++;
            pila[tope]=dato;
        }
    }
    public T pop() { //toma un dato de la pila 
        if (isEmpty()) {
            System.out.println("Subdesbordamiento");
            return null;
        }
        T elemento = pila[tope];
        tope--;
        return elemento;
    }
    public T getTope(){ //para verificar el valor del objeto superior
        if(isEmpty())return null;
        return pila[tope];
    }
    public boolean isEmpty(){
        return tope==-1;
    }
    public boolean isFull(){
        return tope ==pila.length-1;
    }
    @Override
    public String toString(){
        StringBuilder strb = new StringBuilder();
        strb.append("[ ");
        for(int i =0; i<tope+1;i++){
            strb.append(pila[i]);
            strb.append(" > ");
        }
        strb.append(" ]");
        return strb.toString();
    }


}