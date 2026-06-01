package com.topologicalsort.GUI;

import com.topologicalsort.Tools.Nodo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class View extends Pane {
    private HBox nodosLista;
    private GridPane matrizPanel;

    private Button nuevoGrafo;
    private Button agregarArista;
    private Button sonAdyacentes;
    private Button topologicalSort;
    private Button getInDegree, getOutDegree, conectado, totalAristas;
    private ScrollPane nodosScroll, matrizScroll;
    private GridPane botonesPanel;
    private VBox root;
    private ArrayList<ToggleButton> nodosSelec = new ArrayList<>();
    public View(){
        nodosLista = new HBox();
        matrizPanel = new GridPane();
        nodosScroll = new ScrollPane();
        matrizScroll = new ScrollPane();
        nodosSelec = new ArrayList<>();
        root = new VBox();
        nuevoGrafo = new Button("Nuevo Grafo");
        agregarArista = new Button("Agregar Arista");
        sonAdyacentes = new Button("Son Adyacentes");
        topologicalSort = new Button("Topological Sort");
        getInDegree = new Button("Grado de entrada");
        getOutDegree = new Button("Grado de salida");
        conectado = new Button("Estan Conectados");
        totalAristas = new Button("Total Aristas");
//        agregarArista.setOnAction(e -> { controller.});
        matrizPanel.setAlignment(Pos.CENTER);
        nodosScroll.setContent(nodosLista);
        nodosLista.setAlignment(Pos.CENTER);
        matrizPanel.setAlignment(Pos.CENTER);
        nodosScroll.setMinSize(100, 60);

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
//        setCenter(botonesPanel);
//        setTop(nodosScroll);
//        setBottom(matrizScroll);
        root.getChildren().addAll(nodosScroll,botonesPanel,matrizScroll);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        root.setPadding(new Insets(20,20,20,20));
        root.setPrefSize(800, 650);
        getChildren().add(root);

        matrizScroll.setContent(matrizPanel);
        matrizScroll.setFitToWidth(true);
        matrizScroll.setFitToHeight(true);
        matrizScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        matrizScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        matrizPanel.setAlignment(Pos.CENTER);

        nodosLista.setSpacing(15);
    }
    public void actualizarNodos(ArrayList<String> nodosString){
        nodosLista.getChildren().clear();

        nodosSelec.clear();
        for (int i = 0; i < nodosString.size(); i++) {
            ToggleButton nodeBttn = new ToggleButton(nodosString.get(i));
            nodeBttn.setUserData(i);
            nodeBttn.setPrefSize(40, 40);
            nodeBttn.setOnAction(e -> {
                if(nodeBttn.isSelected()){
                    if(nodosSelec.size() >= 2){
                        nodeBttn.setSelected(false);
                        return;
                    }
                    nodosSelec.add(nodeBttn);
                }else nodosSelec.remove(nodeBttn);
            });
            nodosLista.getChildren().add(nodeBttn);
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
                valor.setPrefSize(30, 30);
                valor.setStyle("-fx-border-color: purple;");
                matrizPanel.add(valor, col + 1, fila + 1);
            }
        }
        matrizPanel.applyCss();
        matrizPanel.layout();
        matrizScroll.setPrefViewportWidth(matrizPanel.prefWidth(-1) + 20);
        matrizScroll.setPrefViewportHeight(matrizPanel.prefHeight(-1) + 20);
    }
    public ArrayList<String> pedirNodos() {
        TextInputDialog cantidadDialog = new TextInputDialog();
        cantidadDialog.setTitle("Nuevo Grafo");
        cantidadDialog.setHeaderText("Cuntos vertices seran?");
        var resultado = cantidadDialog.showAndWait();
        if(resultado.isEmpty()){
            return null;
        }
        cantidadDialog.setGraphic(null);

        int cantidad = Integer.parseInt(resultado.get());
        ArrayList<String> nombres = new ArrayList<>();
        for(int i = 0; i < cantidad; i++){
            TextInputDialog nodoDialog = new TextInputDialog();
            nodoDialog.setGraphic(null);
            nodoDialog.setHeaderText("Nodo: " );
            var nodo = nodoDialog.showAndWait();
            if(nodo.isEmpty()){
                return null;
            }
            nombres.add(nodo.get());
        }
        return nombres;
    }


    public int getIdx(){
        if(nodosSelec.isEmpty()){
            return -1;
        }
        return nodosLista.getChildren().indexOf(nodosSelec.getFirst());
    }

    public int[] getIdxNodes(){
        if(nodosSelec.size() != 2){
            return null;
        }
        return new int[]{nodosLista.getChildren().indexOf(nodosSelec.get(0)), nodosLista.getChildren().indexOf(nodosSelec.get(1))};
    }

    public void limpiarSeleccion(){
        for(ToggleButton btn : nodosSelec){
            btn.setSelected(false);
        }
        nodosSelec.clear();
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
