package com.topologicalsort.Tools;

/**
 * Clase que representa una cola circular
 *
 * @param <T>
 */
public class ColaCircular<T> {
    private T[] colaCir;
    private int inicio;
    private int fin;
    private int max;

    public ColaCircular(int max) {
        this.max = max;
        this.fin = -1;
        this.inicio = -1;
        colaCir = (T[]) new Object[max];
    }

    public ColaCircular() {
        this(100);
    }

    public void insertar(T dato) {
        if (isFull()) {
            System.out.println("Desbordamiento");
        } else if (isEmpty()) {
            inicio = 0;
            fin = 0;
            colaCir[fin] = dato;
        } else {
            fin = (fin + 1) % max;
            colaCir[fin] = dato;
        }
    }

    public T eliminar() {
        if (isEmpty()) {
            System.out.println("Subdesbordamiento");
            return null;
        }
        T dato = colaCir[inicio];
        if (inicio == fin) {
            inicio = -1;
            fin = -1;
        } else {
            inicio = (inicio + 1) % max;
        }
        return dato;
    }

    public T verPrimero() {
        if (isEmpty()) {
            System.out.println("Vacío");
            return null;
        }
        return colaCir[inicio];
    }

    public boolean isFull() {
        if (isEmpty()) return false;
        return (fin + 1) % max == inicio;
    }

    public boolean isEmpty() {
        return inicio == -1 && fin == -1;
    }

    public int numElementos() {
        if (isEmpty()) return 0;
        if (fin >= inicio) return fin - inicio + 1;
        return max - inicio + fin + 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        int i = inicio;
        while (true) {
            sb.append(colaCir[i]).append(" ");
            if (i == fin) break;
            i = (i + 1) % max;
        }
        return sb.toString();
    }
}