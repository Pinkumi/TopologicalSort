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
        this.salidas=new ArrayList<>();
        this.entradas=new ArrayList<>();
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Nodo<T> salida: salidas) sb.append(salida.toString()).append(" ");
        return sb.toString();
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

    public void agregarNodoEntrada(Nodo<T> entrada){
        this.entradas.add(entrada);
    }

    public void agregarNodoSalida(Nodo<T> salida){
        this.salidas.add(salida);
    }

    public void eliminarNodoEntrada(Nodo<T> entrada){
        entrada.eliminarNodoSalida(this);
        entradas.remove(entrada);
    }

    public void eliminarNodoSalida(Nodo<T> salida){
        salida.eliminarNodoEntrada(this);
        salidas.remove(salida);
    }
    //endregion
}
