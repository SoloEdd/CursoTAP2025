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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GeneradorReporteVentasDia {

    public static void generarReporte() {
        try (PDDocument document = new PDDocument()) {
            // Configuración inicial
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            try {
                // Parámetros de diseño
                float margin = 50;
                float yPosition = 700;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

                // Título del reporte
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Reporte de Ventas del Día");
                contentStream.endText();
                yPosition -= 25;

                // Fecha del reporte
                String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Fecha: " + fechaHoy);
                contentStream.endText();
                yPosition -= 20;

                // Hora de generación
                String horaGeneracion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Generado a las: " + horaGeneracion);
                contentStream.endText();
                yPosition -= 30;

                // Obtener datos
                ObservableList<OrdenDAO> ventas = OrdenDAO.obtenerVentasDelDia();
                double totalDia = OrdenDAO.obtenerTotalVentasDelDia();

                // Tabla de ventas
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("DETALLE DE VENTAS");
                contentStream.endText();
                yPosition -= 20;

                // Encabezados de tabla
                float[] columnWidths = {60, 120, 80, 80, 100, 80};
                String[] headers = {"# Orden", "Cliente", "Mesa", "Empleado", "Fecha/Hora", "Total"};
                drawTableHeader(contentStream, margin, yPosition, tableWidth, columnWidths, headers);
                yPosition -= 20;

                // Formato de fecha/hora
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

                // Dibujar filas
                for (OrdenDAO venta : ventas) {
                    if (yPosition < 100) { // Nueva página si nos quedamos sin espacio
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);

                        // Crear nuevo contentStream con nombre diferente
                        PDPageContentStream newContentStream = new PDPageContentStream(document, page);

                        // Configurar nueva página
                        yPosition = 700;
                        drawTableHeader(newContentStream, margin, yPosition, tableWidth, columnWidths, headers);
                        yPosition -= 20;

                        // Reemplazar el contentStream anterior
                        contentStream = newContentStream;
                    }

                    String[] rowData = {
                            String.valueOf(venta.getId_orden()),
                            venta.getEmailCliente(),
                            String.valueOf(venta.getNo_mesa()),
                            venta.getNombreEmpleado(),
                            dateFormat.format(venta.getFecha()),
                            "$" + String.format("%.2f", venta.getTotal())
                    };

                    drawTableRow(contentStream, margin, yPosition, tableWidth, columnWidths, rowData);
                    yPosition -= 15;
                }

                // Resumen del día
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("RESUMEN DEL DÍA");
                contentStream.endText();
                yPosition -= 20;

                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Total de ventas: " + ventas.size());
                contentStream.endText();
                yPosition -= 15;

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Ingresos totales: $" + String.format("%.2f", totalDia));
                contentStream.endText();

            } finally {
                if (contentStream != null) {
                    contentStream.close();
                }
            }

            // Guardar documento
            String fileName = "ReporteVentasDia_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            document.save(fileName);

            // Mostrar confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reporte generado");
            alert.setHeaderText(null);
            alert.setContentText("Reporte guardado como: " + fileName);
            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al generar reporte: " + e.getMessage());
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
