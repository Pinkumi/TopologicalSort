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
}