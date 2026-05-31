package com.topologicalsort.Tools;

import java.util.ArrayList;

/**
 * Clase la cual esta parametrizada tipo T
 * Sirve como nodos del grafo
 * Contiene como atributo un arraylist de salidas y otro de entradas
 * @param <T>
 */

public class Nodo <T>{
    private T info;
    private ArrayList<Nodo<T>> salidas;
    private ArrayList<Nodo<T>> entradas;

    public Nodo(T info){
        this.info = info;
        this.salidas = new ArrayList<Nodo<T>>();
        this.entradas = new ArrayList<Nodo<T>>();
    }

    public Nodo(T info, ArrayList<Nodo<T>> salida, ArrayList<Nodo<T>> entradas){
        this.info = info;
        this.salidas = salida;
        this.entradas= entradas;
    }

    public boolean esFinal(){
        return salidas.isEmpty();
    }

    public int gradoDeEntrada(){
        return entradas.size();
    }

    public int gradoDeSalida(){
        return salidas.size();
    }

    /**Conecta dos nodos entre si
     * @param destino El otro nodo al cual se va a conectar
     */
    public void conectarHacia(Nodo<T> destino) {
        if(destino != null){
            this.salidas.add(destino);
            destino.entradas.add(this);
        }
    }

    public void eliminarNodoEntrada(Nodo<T> entrada){
        if(this.entradas.remove(entrada))entrada.salidas.remove(this);

    }

    public void eliminarNodoSalida(Nodo<T> salida){
        if(this.salidas.remove(salida))salida.entradas.remove(this);
    }

    @Override
    public String toString(){
        return "Info: "+info+" Cantidad de aristas: "+ (gradoDeEntrada()+gradoDeSalida());
    }

    //region setter y getters

    public void setInfo(T info){
        this.info=info;
    }

    public void setSalidas(ArrayList<Nodo<T>> salida){
        this.salidas=salida;
    }

    public void setEntradas(ArrayList<Nodo<T>> entradas){
        this.entradas = entradas;
    }

    public T getInfo(){
        return info;
    }

    public ArrayList<Nodo<T>> getSalidas(){
        return salidas;
    }

    public ArrayList<Nodo<T>> getEntradas(){
        return entradas;
    }
    //endregion
}
