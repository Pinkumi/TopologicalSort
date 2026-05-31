package com.topologicalsort.Tools;

import java.util.ArrayList;

/**
 * Clase la cual esta parametrizada tipo T
 * Sirve como nodos del grafo
 * Contiene como atributo un arraylist de vecinos y info
 * @param <T>
 */

public class Nodo <T>{
    private T info;
    private ArrayList<Arista<T>> vecinos;


    public Nodo(T info){
        this.info = info;
        this.vecinos=null;
    }

    public Nodo(T info, ArrayList<Arista<T>> vecinos){
        this.info = info;
        this.vecinos = vecinos;
    }

    public boolean esFinal(){
        return vecinos.isEmpty();
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Arista<T> vecino: vecinos) sb.append(vecino.toString()).append(" ");
        return sb.toString();
    }

    //region setter y getters

    public void setInfo(T info){
        this.info=info;
    }

    public void setVecinos(ArrayList<Arista<T>> vecinos){
        this.vecinos=vecinos;
    }

    public T getInfo(){
        return info;
    }

    public ArrayList<Arista<T>> getVecinos(){
        return vecinos;
    }

    //endregion
}
