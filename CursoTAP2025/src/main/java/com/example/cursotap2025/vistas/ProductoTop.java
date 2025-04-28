package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.GeneradorReporteProductos;
import com.example.cursotap2025.models.ProductoDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProductoTop extends VBox {

    private Label lblTitulo;
    private Label lblNombreProducto;
    private Label lblCantidadVendida;
    private Label lblIngresosGenerados;
    private Label lblMargenGanancia;
    private Button btnReporte;
    private ProductoDAO topProducto;

    public ProductoTop() {
        topProducto = obtenerProductoMasVendido();


        this.setPadding(new Insets(15));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #f0f4f8; -fx-border-color: #cccccc; -fx-border-radius: 10;");

        lblTitulo = new Label("Producto más vendido");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        if (topProducto != null) {
            double margen = topProducto.getIngresosTotales() - (topProducto.getCosto() * topProducto.getTotalVendido());

            lblNombreProducto = new Label("Producto: " + topProducto.getNombre_producto());
            lblCantidadVendida = new Label("Unidades vendidas: " + topProducto.getTotalVendido());
            lblIngresosGenerados = new Label("Ingresos generados: $" + String.format("%.2f", topProducto.getIngresosTotales()));
            lblMargenGanancia = new Label("Margen de ganancia: $" + String.format("%.2f", margen));

            btnReporte = new Button("Generar Reporte Completo");
            btnReporte.setOnAction(e -> {
                ObservableList<ProductoDAO> productosMasVendidos = ProductoDAO.obtenerProductosMasVendidos();
                GeneradorReporteProductos.generarReporte(productosMasVendidos);
            });

            this.getChildren().addAll(lblTitulo, lblNombreProducto, lblCantidadVendida,
                    lblIngresosGenerados, lblMargenGanancia, btnReporte);
        } else {
            this.getChildren().addAll(lblTitulo, new Label("No hay datos de ventas disponibles"));
        }
    }

    private ProductoDAO obtenerProductoMasVendido() {
        ObservableList<ProductoDAO> productos = ProductoDAO.obtenerProductosMasVendidos();
        return productos.isEmpty() ? null : productos.get(0);
    }
}
