package com.topologicalsort.Tools;

/**
 * Clase la cual esta parametrizada tipo T
 * Sirve como nodos del grafo
 * @param <T>
 */

public class Nodo <T>{
    private T info;


    public Nodo(T info){
        this.info = info;
    }

    public Nodo(){
        this.info = null;
    }


    public void setInfo(T info){
        this.info=info;
    }

    public T getInfo(){
        return info;
    }

}
