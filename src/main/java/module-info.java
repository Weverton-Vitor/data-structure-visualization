module com.example.datastructurevisualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.datastructurevisualization to javafx.fxml;
    exports com.example.datastructurevisualization;
}