package com.topologicalsort.Logic;

import com.topologicalsort.Tools.Nodo;

import java.util.ArrayList;

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
        return 0;
    }

    public int gradoDeSalida(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
        return 0;
    }

    public int cuantasAristasHay() {
        return 0;
    }

    public boolean adyacente(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }
        return false;
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