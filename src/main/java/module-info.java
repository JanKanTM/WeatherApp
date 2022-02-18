module com.example.balancechecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.balancechecker to javafx.fxml;
    exports com.example.balancechecker;
}