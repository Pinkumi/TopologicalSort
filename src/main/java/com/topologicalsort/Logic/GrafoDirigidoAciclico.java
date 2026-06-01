package com.topologicalsort.Logic;

import com.topologicalsort.Tools.ColaCircular;
import com.topologicalsort.Tools.Nodo;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Calcula cuantas aristas hay en el grafo actualmente.
     * @return int la cantidad de aristas que tiene el grafo
     */
    public int cuantasAristasHay() {
        int cuantasHay=0;
        for(Nodo<T> n : nodos){cuantasHay+=n.gradoDeEntrada();}
        return cuantasHay;
    }

    /**
     * Verifica si dos nodos son vecinos, es decir
     * estan conectados entre si
     * @param i El nodo de donde se inicia.
     * @param j El nodo al quiere llegar
     * @return true si estan conectados
     */
    public boolean adyacente(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException();
        }

        return nodos.get(i).getEntradas().contains(nodos.get(j))||
                nodos.get(i).getSalidas().contains(nodos.get(j));
    }


    /**
     * Verifica si desde el nodo a se puede llegar al nodo b.
     * @param i El nodo de donde se inicia.
     * @param j El nodo al quiere llegar
     * @return true si estan conectados
     */
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


    /**
     * Este metodo utiliza el topologicalSort
     * el cual devuelve el grafo acomodado por
     * la cantidad de flechas que entran a los nodos
     * si uno le llegan 0, sera el primero en la lista
     * @return String el cual sera el metodo acomodado, o si no se pudo
     * usar
     */
    public String topologicalSort() {
        //Directamente verificamos si hay ciclo, para evitar hacer el ordenamiento
        //si llega a haber uno
        if(tieneCiclos()){
            return "Error, el grafo contiene un ciclo, no se puede realizar el ordenamiento";
        }
        ColaCircular<Nodo<T>> topo = new ColaCircular<>(n);
        ArrayList<T> resultado = new ArrayList<>();

        //Se utiliza para ir guardando los grados de forma temporal
        //sin tener que cambiar el arraylist original
        HashMap<Nodo<T>,Integer> gradosTemp = new HashMap<>();

        //Llenamos el hashmap con los nodos y sus entradas
        for(Nodo<T> n: nodos){
            int grado = n.gradoDeEntrada();
            gradosTemp.put(n, grado);

            //Si no tiene entradas se va agregando a la cola
            //para ser procesado
            if(grado==0){
                topo.insertar(n);
            }
        }

        while(!topo.isEmpty()){
            Nodo<T> actual = topo.eliminar();
            resultado.add(actual.getInfo());

            //Por cada arista la cual sale del nodo actual, le quitamos uno a su grado
            //Y lo agregamos al hashmap
            for(Nodo<T> vecino : actual.getSalidas()){
                int nuevoGrado = gradosTemp.get(vecino)-1;
                gradosTemp.put(vecino, nuevoGrado);

                //Si se encuentra uno sin vecinos de entrada, sera el siguiente en analizar
                if(nuevoGrado==0){
                    topo.insertar(vecino);
                }
            }
        }

        //Juntamos toda la informacion separada por " - " como se pide en la entrega
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n; i++){
            if(i>0)sb.append(" - ");
            sb.append(resultado.get(i));
        }

        return "Orden Topologico: " + sb.toString();
    }

    /**
     * Sirve para saber si hay un ciclo en el grafo
     * @return true si se encontro un ciclo en el grafo
     */
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

    /**
     * Metodo el cual sirve para mostrar la matriz de adyacencia
     * @return String el cual es en forma de matriz
     */
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
        nodos.get(i).conectarHacia(nodos.get(j));
        if(tieneCiclos()){
            nodos.get(i).eliminarNodoSalida(nodos.get(j));
            nodos.get(j).eliminarNodoEntrada(nodos.get(i));
            return false;
        }

        return true;
    }

    /**
     * Elimina todas las aristas del grafo
     */
    public void eliminarAristas() {
        for (Nodo<T> n : nodos){
            ArrayList<Nodo<T>> nodosEntradas = n.getEntradas();
            for(Nodo<T> entrada: nodosEntradas){
                n.eliminarNodoEntrada(entrada);
            }
        }
    }

    public void agregarNodo(T info){
        nodos.add(new Nodo<>(info));
    }

    public ArrayList<Nodo<T>> getNodos(){
        return nodos;
    }

    public int getSize(){
        return n;
    }
}