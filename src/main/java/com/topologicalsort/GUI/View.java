package com.topologicalsort.GUI;

import com.topologicalsort.Tools.Nodo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class View extends BorderPane {
    private HBox nodosLista;
    private GridPane matrizPanel;

    private Button nuevoGrafo;
    private Button agregarArista;
    private Button sonAdyacentes;
    private Button topologicalSort;
    private Button getInDegree, getOutDegree, conectado, totalAristas;
    private ScrollPane nodosScroll, matrizScroll;
    private GridPane botonesPanel;
    private VBox centroPanel;
    public View(){
        nodosLista = new HBox();
        matrizPanel = new GridPane();
        nodosScroll = new ScrollPane();
        matrizScroll = new ScrollPane();

        nuevoGrafo = new Button("Nuevo Grafo");
        agregarArista = new Button("Agregar Arista");
        sonAdyacentes = new Button("Son Adyacentes");
        topologicalSort = new Button("Topological Sort");
        getInDegree = new Button("Grado de entrada");
        getOutDegree = new Button("Grado de salida");
        conectado = new Button("Estan Conectados");
        totalAristas = new Button("Total Aristas");
//        agregarArista.setOnAction(e -> { controller.});
        nodosLista.setAlignment(Pos.CENTER);
        matrizPanel.setAlignment(Pos.CENTER);
        nodosScroll.setContent(nodosLista);
        nodosLista.setAlignment(Pos.CENTER);
        matrizPanel.setAlignment(Pos.CENTER);

        botonesPanel = new GridPane();
        botonesPanel.setAlignment(Pos.CENTER);
        botonesPanel.setHgap(10);
        botonesPanel.setVgap(10);

        botonesPanel.add(nuevoGrafo,0, 0);
        botonesPanel.add(agregarArista,1, 0);
        botonesPanel.add(sonAdyacentes,2, 0);
        botonesPanel.add(topologicalSort,3, 0);
        botonesPanel.add(getInDegree,0, 1);
        botonesPanel.add(getOutDegree,1, 1);
        botonesPanel.add(conectado,2, 1);
        botonesPanel.add(totalAristas,3, 1);

        matrizScroll.setContent(matrizPanel);
        setCenter(botonesPanel);
        setTop(nodosScroll);
        setBottom(matrizScroll);
        matrizScroll.setPrefSize(300,300);
    }
    public void actualizarNodos(ArrayList<String> nodosString){
        nodosLista.getChildren().clear();
        for (String nodo : nodosString) {
            Label nodoText = new Label(nodo);
            nodoText.setPrefSize(60, 40);
            nodoText.setAlignment(Pos.CENTER);
            nodosLista.getChildren().add(nodoText);
        }
    }
    public void actualizarMatriz(ArrayList<String> nodos, int[][] matrizAdyacencia) {
        matrizPanel.getChildren().clear();
        matrizPanel.add(new Label(""), 0, 0);
        for (int col = 0; col < nodos.size(); col++) {
            Label encabezado = new Label(nodos.get(col));
            encabezado.setAlignment(Pos.CENTER);
            encabezado.setPadding(new Insets(5));
            matrizPanel.add(encabezado, col + 1, 0);
        }

        for (int fila = 0; fila < nodos.size(); fila++) {
            Label encabezadoFila = new Label(nodos.get(fila));
            encabezadoFila.setAlignment(Pos.CENTER);
            encabezadoFila.setPadding(new Insets(5));
            matrizPanel.add(encabezadoFila, 0, fila + 1);
            for (int col = 0; col < nodos.size(); col++) {
                Label valor = new Label(String.valueOf(matrizAdyacencia[fila][col]));
                valor.setAlignment(Pos.CENTER);
                valor.setPadding(new Insets(5));
                matrizPanel.add(valor, col + 1, fila + 1);
            }
        }
    }
    public ArrayList<String> pedirNodos() {
        TextInputDialog cantidadDialog = new TextInputDialog();
        cantidadDialog.setTitle("Nuevo Grafo");
        cantidadDialog.setHeaderText("Cantidad de vértices");
        var resultado = cantidadDialog.showAndWait();
        if(resultado.isEmpty()){
            return null;
        }
        int cantidad = Integer.parseInt(resultado.get());
        ArrayList<String> nombres = new ArrayList<>();
        for(int i = 0; i < cantidad; i++){
            TextInputDialog nodoDialog = new TextInputDialog();
            nodoDialog.setTitle("Nuevo Nodo");
            nodoDialog.setHeaderText("Ingrese el nombre del nodo " + (i + 1));
            var nodo = nodoDialog.showAndWait();
            if(nodo.isEmpty()){
                return null;
            }
            nombres.add(nodo.get());
        }
        return nombres;
    }


    //region getters de botones para asignar funcion
    public Button getNuevoGrafo(){
        return nuevoGrafo;
    }
    public Button getAgregarArista(){
        return agregarArista;
    }
    public Button getSonAdyacentes(){
        return sonAdyacentes;
    }
    public Button getTopologicalSort(){
        return topologicalSort;
    }
    public Button getGetInDegree(){
        return getInDegree;
    }
    public Button getGetOutDegree(){
        return getOutDegree;
    }
    public Button getConectado(){
        return conectado;
    }
    public Button getTotalAristas(){
        return totalAristas;
    }
    //endregion

}
