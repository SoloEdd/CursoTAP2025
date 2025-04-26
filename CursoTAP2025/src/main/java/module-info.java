module com.example.cursotap2025 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;
    requires javafx.graphics;
    requires org.apache.pdfbox;
    requires java.desktop;
    opens com.example.cursotap2025 to javafx.fxml;
    exports com.example.cursotap2025;
    opens com.example.cursotap2025.models;
}