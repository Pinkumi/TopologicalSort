package com.topologicalsort.Logic;

import com.topologicalsort.Tools.ColaCircular;
import com.topologicalsort.Tools.Nodo;
import java.util.ArrayList;
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
        boolean estanConectados = false;
        ColaCircular<T> cola = new ColaCircular<>(n);




        return false;
    }

    public String topologicalSort() {
        ColaCircular<Nodo<T>> topo = new ColaCircular<>(n);
        for(Nodo<T> n: nodos){
            if(n.getEntradas().isEmpty()){
                topo.insertar(n);
            }
        }

        return "";
    }

    public boolean tieneCiclos() {
        return false;
    }

    public String mostrarEstructura(){
        return "";
    }

    /**
     * Conecta dos nodos por una arista.
     * @param i El nodo de donde sale la arista.
     * @param j El nodo al cual se conecta i
     * @return true si se pudo conectar la arista.
     */
    public boolean insertarArista(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }
        nodos.get(i).agregarNodoSalida(nodos.get(j));
        nodos.get(j).agregarNodoEntrada(nodos.get(i));
        if(tieneCiclos()){
            nodos.get(i).eliminarNodoSalida(nodos.get(j));
            nodos.get(j).eliminarNodoEntrada(nodos.get(i));
            return false;
        }

        return true;
    }

    public void eliminarAristas() {
        for (Nodo<T> n : nodos){
            ArrayList<Nodo<T>> nodosEntradas = n.getEntradas();
            for(Nodo<T> entrada: nodosEntradas){
                n.eliminarNodoEntrada(entrada);
            }
        }
    }
}