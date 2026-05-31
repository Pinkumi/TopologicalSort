package com.topologicalsort.Tools;

public class Arista <T>{
    private Nodo<T> fin;
    private Nodo<T> inicio;

    public Arista(Nodo<T> fin, Nodo<T> inicio) {
        this.fin = fin;
        this.inicio = inicio;
    }

    @Override
    public String toString(){
        return "Inicio -> " + inicio +" -- " +fin+" <- fin";
    }

    //region setters y getters

    public void setFin(Nodo<T> fin){
        this.fin = fin;
    }

    public void setInicio(Nodo<T> inicio){
        this.inicio = inicio;
    }

    public Nodo<T> getFin(){
        return fin;
    }

    public Nodo<T> getInicio(){
        return inicio;
    }

    //endregion
}
