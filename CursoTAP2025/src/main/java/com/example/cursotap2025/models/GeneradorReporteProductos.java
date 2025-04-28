package com.example.cursotap2025.models;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

public class GeneradorReporteProductos {

    public static void generarReporte(ObservableList<ProductoDAO> productos) {
        try (PDDocument document = new PDDocument()) {
            PDPage currentPage = new PDPage();
            document.addPage(currentPage);
            PDPageContentStream contentStream = new PDPageContentStream(document, currentPage);

            // Configuración inicial
            float margin = 50;
            float yPosition = 700;
            float tableWidth = currentPage.getMediaBox().getWidth() - 2 * margin;

            try {
                // Título del reporte
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Reporte de Productos Más Vendidos");
                contentStream.endText();
                yPosition -= 25;

                // Fecha de generación
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Generado el: " + fecha);
                contentStream.endText();
                yPosition -= 30;

                // Tabla de productos
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("PRODUCTOS MÁS VENDIDOS");
                contentStream.endText();
                yPosition -= 20;

                // Encabezados de tabla
                float[] columnWidths = {120, 80, 80, 80, 80};
                String[] headers = {"Producto", "Precio Unit.", "Cantidad Vendida", "Ingresos Totales", "Margen"};

                // Dibujar encabezados
                drawTableHeader(contentStream, margin, yPosition, tableWidth, columnWidths, headers);
                yPosition -= 20;

                // Dibujar filas
                for (ProductoDAO producto : productos) {
                    if (yPosition < 100) { // Nueva página si nos quedamos sin espacio
                        contentStream.close();
                        currentPage = new PDPage();
                        document.addPage(currentPage);
                        contentStream = new PDPageContentStream(document, currentPage);
                        yPosition = 700;

                        // Volver a dibujar encabezados en nueva página
                        drawTableHeader(contentStream, margin, yPosition, tableWidth, columnWidths, headers);
                        yPosition -= 20;
                    }

                    double margen = producto.getIngresosTotales() - (producto.getCosto() * producto.getTotalVendido());

                    String[] rowData = {
                            producto.getNombre_producto(),
                            "$" + String.format("%.2f", producto.getPrecio()),
                            String.valueOf(producto.getTotalVendido()),
                            "$" + String.format("%.2f", producto.getIngresosTotales()),
                            "$" + String.format("%.2f", margen)
                    };

                    drawTableRow(contentStream, margin, yPosition, tableWidth, columnWidths, rowData);
                    yPosition -= 15;
                }

                // Resumen estadístico
                if (yPosition < 150) {
                    contentStream.close();
                    currentPage = new PDPage();
                    document.addPage(currentPage);
                    contentStream = new PDPageContentStream(document, currentPage);
                    yPosition = 700;
                }

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("RESUMEN ESTADÍSTICO");
                contentStream.endText();
                yPosition -= 20;

                // Calcular estadísticas
                int totalProductosVendidos = productos.stream().mapToInt(ProductoDAO::getTotalVendido).sum();
                double totalIngresos = productos.stream().mapToDouble(ProductoDAO::getIngresosTotales).sum();

                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Total productos vendidos: " + totalProductosVendidos);
                contentStream.endText();
                yPosition -= 15;

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Ingresos totales: $" + String.format("%.2f", totalIngresos));
                contentStream.endText();

            } finally {
                if (contentStream != null) {
                    contentStream.close();
                }
            }

            // Guardar el documento
            String fileName = "ReporteProductos_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            document.save(fileName);

            // Mostrar mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reporte generado");
            alert.setHeaderText(null);
            alert.setContentText("El reporte de productos se ha generado correctamente.");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al generar reporte");
            alert.setHeaderText(null);
            alert.setContentText("Ocurrió un error al generar el reporte PDF: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private static void drawTableHeader(PDPageContentStream contentStream, float x, float y,
                                        float tableWidth, float[] columnWidths, String[] headers) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        float currentX = x;

        for (int i = 0; i < headers.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(currentX, y);
            contentStream.showText(headers[i]);
            contentStream.endText();
            currentX += columnWidths[i];
        }

        // Línea debajo del encabezado
        contentStream.moveTo(x, y - 5);
        contentStream.lineTo(x + tableWidth, y - 5);
        contentStream.stroke();
    }

    private static void drawTableRow(PDPageContentStream contentStream, float x, float y,
                                     float tableWidth, float[] columnWidths, String[] rowData) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        float currentX = x;

        for (int i = 0; i < rowData.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(currentX, y);
            contentStream.showText(rowData[i]);
            contentStream.endText();
            currentX += columnWidths[i];
        }
    }
}
