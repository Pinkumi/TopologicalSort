package com.topologicalsort;

import com.topologicalsort.GUI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Controller controller = new Controller();
        Scene scene = new Scene(controller.getVista(), 820, 850);
        stage.setTitle("Hello!");
        scene.getStylesheets().add("file:src/main/resources/estilos.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}