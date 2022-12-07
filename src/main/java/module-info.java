module com.example.datastructurevisualization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.project.datastructurevisualization to javafx.fxml;
    exports com.project.datastructurevisualization;
}