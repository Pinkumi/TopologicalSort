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
    }

    public void crearGrafoNuevo(int n, String tipo){
        if (tipo.equals("Integer")) {
            logica = new GrafoDirigidoAciclico<Integer>(n);
        } else {
            logica = new GrafoDirigidoAciclico<String>(n);
        }
    }

    public void inicializarBotones(){

    }

    public void actualizarNodos(){
        vista.actualizarNodos(logica.getNodos());
    }




}