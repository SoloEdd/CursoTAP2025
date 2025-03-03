module com.example.cursotap2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;

    opens com.example.cursotap2025 to javafx.fxml;
    exports com.example.cursotap2025;
}