package com.topologicalsort.Logic;


/**
 * Clase que representa un grafo aciclico dirigido
 *
 * @param <T>
 */
public class GrafoDirigidoAciclico {

    private int n; // cant vertices en grafo
    //private matriz;  aun no se q  estructura

    public GrafoDirigidoAciclico(int n) {
        this.n = n;
        //this.matriz = aun no se q  estructura





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