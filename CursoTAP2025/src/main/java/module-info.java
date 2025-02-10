module com.example.cursotap2025 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cursotap2025 to javafx.fxml;
    exports com.example.cursotap2025;
}