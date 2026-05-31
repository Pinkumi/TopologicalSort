package com.topologicalsort.Logic;

import com.topologicalsort.Tools.ColaCircular;
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

        //int sizeGrafo = nodos.size();
        ArrayList<Nodo<T>> visitados = new ArrayList<>();
        ColaCircular<Nodo<T>> colaBFS = new ColaCircular<>(n);
        Nodo<T> inicio = nodos.get(i);
        Nodo<T> fin = nodos.get(j);

        colaBFS.insertar(inicio);
        visitados.add(inicio);
        while(!colaBFS.isEmpty()){
            Nodo<T> current = colaBFS.eliminar();
            if(current == fin) return true;
            for(Nodo<T> vecino : current.getSalidas()){
                visitados.add(vecino);
                colaBFS.insertar(vecino);
                //if(current == fin) return true;
            }
        }

        return false;
    }


    public String topologicalSort() {
        return "";
    }

    public boolean tieneCiclos() {
        ArrayList<Nodo<T>> visitados = new ArrayList<>();
        ArrayList<Nodo<T>> pilaDFS = new ArrayList<>();

        for(Nodo<T> n :nodos){
            if(!visitados.contains(n)){
                if(dfs(n, visitados, pilaDFS)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(Nodo<T> current, ArrayList<Nodo<T>> visitados, ArrayList<Nodo<T>> pilaDFS){
        visitados.add(current);
        pilaDFS.add(current);
        for(Nodo<T> vecino : current.getSalidas()){
            if(!visitados.contains(vecino)){
                if(dfs(vecino, visitados, pilaDFS)){
                    return true;
                }
            }
            else if(pilaDFS.contains(vecino)){
                return true;
            }
        }
        pilaDFS.remove(current);
        return false;
    }

    public String mostrarEstructura() {
        StringBuilder sb = new StringBuilder();
        sb.append("DATO\t");
        for(Nodo<T> nodo : nodos){
            sb.append(nodo.getInfo()).append("\t");
        }
        sb.append("\n");
        for(int i = 0; i < nodos.size(); i++){
            sb.append(nodos.get(i).getInfo()).append("\t");
            for(int j = 0; j < nodos.size(); j++){
                boolean encontrado = false;
                for(Nodo<T> salida : nodos.get(i).getSalidas()){
                    if(salida == nodos.get(j)){
                        encontrado = true;
                        break;
                    }
                }
                if(encontrado){
                    sb.append("1\t");
                }else{
                    sb.append("0\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
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