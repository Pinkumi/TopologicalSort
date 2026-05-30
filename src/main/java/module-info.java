module com.topologicalsort {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.topologicalsort to javafx.fxml;
    exports com.topologicalsort;
    exports com.topologicalsort.GUI;
    opens com.topologicalsort.GUI to javafx.fxml;
}