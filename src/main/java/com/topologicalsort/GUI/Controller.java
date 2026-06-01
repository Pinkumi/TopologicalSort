package com.topologicalsort.GUI;

import com.topologicalsort.Logic.GrafoDirigidoAciclico;
import com.topologicalsort.Tools.Nodo;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Conexion entre vista y logica del grafo
 *
 */

public class Controller {

    private GrafoDirigidoAciclico logica;
    private View vista;
    public Controller(){
        vista = new View();
        inicializarBotones();
    }
    public void crearGrafoNuevo(int n, String tipo){
        if (tipo.equals("Integer")) {
            logica = new GrafoDirigidoAciclico<Integer>(n);
        } else {
            logica = new GrafoDirigidoAciclico<String>(n);
        }
    }

    public void inicializarBotones() {
        vista.getNuevoGrafo().setOnAction(e -> {
            crearGrafo();
        });
        vista.getAgregarArista().setOnAction(e -> agregarArista());
        vista.getConectado().setOnAction(e -> estanConectados());
        vista.getSonAdyacentes().setOnAction(e -> esAdy());
        vista.getOutDegree().setOnAction(e -> mostrarOutDeg());
        vista.getInDegree().setOnAction(e -> mostrarInDeg());
        vista.getTotalAristas().setOnAction(e -> mostrarAristasTot());
        vista.getTopologicalSort().setOnAction(e -> ordenar());

    }

    public void crearGrafo() {
        ArrayList<String> nombres = vista.pedirNodos();
        if(nombres == null){
            return;
        }
        logica = new GrafoDirigidoAciclico<>(nombres.size());
        for(String nombre : nombres){
            logica.agregarNodo(nombre);
        }
        vista.actualizarNodos(logica.getNodosTxt());
        vista.actualizarMatriz(logica.getNodosTxt(), logica.getMatrizAdy());
    }

    public void updateNodos(){
        vista.actualizarNodos(logica.getNodosTxt());
    }
    public void actualizarMatriz(){
        vista.actualizarMatriz(logica.getNodosTxt(), logica.getMatrizAdy());
    }
    public View getVista() {
        return vista;
    }


    //region botones funciones
    public void agregarArista(){
        int[] seleccion = vista.getIdxNodes();
        if(seleccion == null){
            vista.mostrarAlertaError("Faltan Nodos", "No seleccionaste los nodos.");
            return;
        }
        try {
            logica.insertarArista(seleccion[0], seleccion[1]);
            vista.actualizarMatriz(logica.getNodosTxt(), logica.getMatrizAdy());
        } catch (Exception e) {
            vista.mostrarAlertaError("Error de Conexión", "No se pudo añadir la arista ya que genera ciclo o se repite");
        }
        vista.limpiarSeleccion();
    }
    public void esAdy(){
        int[] seleccion = vista.getIdxNodes();
        if(seleccion == null){
            vista.mostrarAlertaError("Faltan Nodos", "No seleccionaste los nodos.");
            return;
        }
        boolean sonAdy = logica.adyacente(seleccion[0], seleccion[1]);
        vista.mostrarEsAdy(sonAdy);
        vista.limpiarSeleccion();
    }
    public void estanConectados(){
        int[] seleccion = vista.getIdxNodes();
        if(seleccion == null){
            vista.mostrarAlertaError("Faltan Nodos", "No seleccionaste los nodos.");
            return;
        }
        boolean conect = logica.conectados(seleccion[0], seleccion[1]);
        vista.mostrarEstanConectados(conect);
        vista.limpiarSeleccion();
    }
    public void mostrarInDeg(){
        int seleccion = vista.getIdx();

        if(seleccion == -1){
            vista.mostrarAlertaError("Faltan Nodos", "No seleccionaste los nodos.");
            return;
        }
        String nodoTxt = (String)logica.getNodosTxt().get(seleccion);
        int inDeg = logica.gradoDeEntrada(seleccion);
        vista.mostrarInDeg(inDeg, nodoTxt);
        vista.limpiarSeleccion();
    }
    public void mostrarOutDeg(){
        int seleccion = vista.getIdx();

        if(seleccion == -1){
            vista.mostrarAlertaError("Faltan Nodos", "No seleccionaste los nodos.");
            return;
        }
        String nodoTxt = (String)logica.getNodosTxt().get(seleccion);
        int OutDeg = logica.gradoDeSalida(seleccion);
        vista.mostrarOutDeg(OutDeg, nodoTxt);
        vista.limpiarSeleccion();
    }

    public void mostrarAristasTot(){
        int arisT = logica.cuantasAristasHay();
        vista.mostrarAristasTot(arisT);
        vista.limpiarSeleccion();
    }
    public void ordenar(){
        vista.mostrarOrdenados(logica.topologicalSort());
        vista.actualizarMatriz(logica.getNodosTxt(), logica.getMatrizAdy());
        vista.limpiarSeleccion();
    }

    //endregion

}