package com.topologicalsort.Logic;

import com.topologicalsort.Tools.Nodo;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Clase que representa un grafo aciclico dirigido
 *
 * @param <T>
 */

public class GrafoDirigidoAciclico <T>{

    private int n; //Cantidad maxima de nodos
    private ArrayList<Nodo<T>> nodos;

    public GrafoDirigidoAciclico(int n) {
        this.n = n;
        this.nodos= new ArrayList<>();
    }

    public GrafoDirigidoAciclico(int n, ArrayList<Nodo<T>> nodos){
        this.n = n;
        this.nodos= nodos;
    }

    public int gradoDeEntrada(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
        return nodos.get(i).gradoDeEntrada();
    }

    public int gradoDeSalida(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
        return nodos.get(i).gradoDeSalida();
    }

    public int cuantasAristasHay() {
        int cuantasHay=0;
        for(Nodo<T> n : nodos){cuantasHay+=n.gradoDeEntrada();}
        return cuantasHay;
    }

    public boolean adyacente(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }

        return nodos.get(i).getEntradas().contains(nodos.get(j))||
                nodos.get(i).getSalidas().contains(nodos.get(j));
    }

    public boolean conectados(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    public String topologicalSort() {
        return "";
    }

    public boolean tieneCiclos() {
        Queue<>

        return false;
    }

    public String mostrarEstructura() {
        return "";
    }

    public boolean insertarArista(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    public void elimiarAristas() {

    }
}