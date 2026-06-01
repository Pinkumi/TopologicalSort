package com.topologicalsort.GUI;

import com.topologicalsort.Tools.Nodo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private VBox root;
    private ArrayList<ToggleButton> nodosSelec = new ArrayList<>();

    public View(){
        nodosLista = new HBox();
        matrizPanel = new GridPane();
        nodosScroll = new ScrollPane();
        matrizScroll = new ScrollPane();
        nodosSelec = new ArrayList<>();
        root = new VBox();

        Pane panel = new Pane();

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
        nodosScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        nodosScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        nodosScroll.setFitToHeight(true);

        nodosScroll.setPrefSize(550, 80);
        nodosScroll.setMinSize(550, 80);
        nodosScroll.setMaxSize(550, 80);
        //nodosScroll.setStyle("-fx-border-color: purple");

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

        Image img = new Image(getClass().getResource("/images/DS.png").toExternalForm());
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(800, 800, false, false, false, false));

        panel.setBackground(new Background(bg));
        panel.setPrefSize(800, 800);
        panel.setMinSize(800, 800);
        panel.setMaxSize(800, 800);

        Image omocat = new Image(getClass().getResource("/images/omocat.png").toExternalForm());
        ImageView gatito1 = new ImageView(omocat);
        gatito1.setFitWidth(170);
        gatito1.setFitHeight(170);
        gatito1.setPreserveRatio(true);
        gatito1.setLayoutX(550);
        gatito1.setLayoutY(-50);

        Image sakamoto = new Image(getClass().getResource("/images/sakamoto.png").toExternalForm());
        ImageView gatito2 = new ImageView(sakamoto);
        gatito2.setFitWidth(140);
        gatito2.setFitHeight(140);
        gatito2.setPreserveRatio(true);
        gatito2.setLayoutX(50);
        gatito2.setLayoutY(0);

        nodosScroll.setLayoutX(133);
        nodosScroll.setLayoutY(50);

        botonesPanel.setLayoutX(133);
        botonesPanel.setLayoutY(140);

        matrizScroll.setLayoutX(160);
        matrizScroll.setLayoutY(470);
        panel.getChildren().addAll(nodosScroll, botonesPanel, matrizScroll, gatito1,gatito2);
        setCenter(panel);

        matrizScroll.setContent(matrizPanel);
        matrizScroll.setFitToWidth(true);
        matrizScroll.setFitToHeight(true);
        matrizScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        matrizScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        matrizScroll.setPrefSize(470, 270);
        matrizScroll.setMinSize(470,270);
        matrizScroll.setMaxSize(470,270);

        matrizPanel.setAlignment(Pos.BOTTOM_CENTER);

        nodosLista.setSpacing(15);
        nodosScroll.setFitToWidth(true);

        botonesPanel.setPrefSize(550, 200);
        botonesPanel.setMinSize(550, 200);
        botonesPanel.setMaxSize(550, 200);
        //botonesPanel.setStyle("-fx-border-color: purple");
        nodosScroll.getStyleClass().add("scroll");

    }

    public void actualizarNodos(ArrayList<String> nodosString){
        nodosLista.getChildren().clear();

        nodosSelec.clear();
        for (int i = 0; i < nodosString.size(); i++) {
            ToggleButton nodeBttn = new ToggleButton(nodosString.get(i));
            nodeBttn.setUserData(i);
            nodeBttn.setPrefSize(40, 40);
            nodeBttn.setMinWidth(40);
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
        Label esquina = new Label("");
        esquina.setPrefSize(40, 40);
        esquina.getStyleClass().add("matriz-esquina");
        matrizPanel.add(esquina, 0, 0);
        for (int col = 0; col < nodos.size(); col++) {
            Label encabezado = new Label(nodos.get(col));
            encabezado.setAlignment(Pos.CENTER);
            encabezado.setPrefSize(40, 40);
            encabezado.setMinSize(30, 30);
            encabezado.getStyleClass().add("matriz-encabezado");
            matrizPanel.add(encabezado, col + 1, 0);
        }
        for (int fila = 0; fila < nodos.size(); fila++) {
            Label encabezadoFila = new Label(nodos.get(fila));
            encabezadoFila.setAlignment(Pos.CENTER);
            encabezadoFila.setPrefSize(40, 40);
            encabezadoFila.setMinSize(30, 30);
            encabezadoFila.getStyleClass().add("matriz-encabezado");
            matrizPanel.add(encabezadoFila, 0, fila + 1);
            for (int col = 0; col < nodos.size(); col++) {
                int valorMatriz = matrizAdyacencia[fila][col];
                Label valor = new Label(String.valueOf(valorMatriz));
                valor.setAlignment(Pos.CENTER);
                valor.setPrefSize(40, 40);
                valor.setMinSize(30, 30);
                if (valorMatriz == 1) {
                    valor.getStyleClass().add("matriz-celda-uno");
                } else {
                    valor.getStyleClass().add("matriz-celda-cero");
                }
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


    public void mostrarAlertaError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    //region popups
    public void mostrarInDeg(int inDegree, String nodoTxt){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("El nodo "+ nodoTxt+ " tiene " + inDegree + " grados de entrada");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        wind.setScene(scene);
        wind.showAndWait();
    }
    public void mostrarOutDeg(int outDegree, String nodoTxt){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("El nodo "+ nodoTxt+ " tiene " + outDegree + " grados de salida");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        wind.setScene(scene);
        wind.showAndWait();
    }

    public void mostrarEsAdy(boolean esAdy){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label;
        if(esAdy) label = new Label("Los 2 nodos son adyacentes");
        else label = new Label("Los nodos NO son adyacentes");
        StackPane root = new StackPane(label);

        root.getStyleClass().add("popup-root");
        label.getStyleClass().add("popup-label");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        wind.setScene(scene);
        wind.showAndWait();
    }
    public void mostrarEstanConectados(boolean estanConect){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label;
        if(estanConect) label = new Label("Los 2 nodos estan conectaods");
        else label = new Label("Los nodos NO estan conectaods");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        wind.setScene(scene);
        wind.showAndWait();
    }
    public void mostrarAristasTot(int aristasTot){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("En el grafo hay un total de: " + aristasTot+ " Aristas");
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        wind.setScene(scene);
        wind.showAndWait();
    }
    public void mostrarOrdenados(String ordenados){
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label(ordenados);
        label.getStyleClass().add("popup-label");
        StackPane root = new StackPane(label);
        root.getStyleClass().add("popup-root");
        Scene scene = new Scene(root, 300, 150);
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        wind.setScene(scene);
        wind.showAndWait();
    }

    //endregion

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
    public Button getInDegree(){
        return getInDegree;
    }
    public Button getOutDegree(){
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