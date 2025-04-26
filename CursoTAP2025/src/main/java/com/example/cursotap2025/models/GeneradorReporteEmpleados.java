package com.example.cursotap2025.models;

import javafx.scene.control.Alert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorReporteEmpleados {

    public static void generarReporte(ObservableList<EmpleadoDAO> empleados, EmpleadoDAO topEmpleado) {



        try (PDDocument document = new PDDocument()) {
            // Variables para control de paginación
            PDPage currentPage = new PDPage();
            document.addPage(currentPage);
            PDPageContentStream contentStream = new PDPageContentStream(document, currentPage);

            // Configuración inicial
            float margin = 50;
            float yPosition = 700;
            float tableWidth = currentPage.getMediaBox().getWidth() - 2 * margin;

            try {
                // Título del reporte
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Reporte de Empleados - Restaurante");
                contentStream.endText();
                yPosition -= 20;

                // Fecha de generación
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Generado el: " + fecha);
                contentStream.endText();
                yPosition -= 30;

                // Sección de empleado top
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("EMPLEADO DESTACADO:");
                contentStream.endText();
                yPosition -= 20;

                if (topEmpleado != null) {
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText("Nombre: " + topEmpleado.getNombre() + " " +
                            topEmpleado.getPrimer_apellido() + " " +
                            topEmpleado.getSegundo_apellido());
                    contentStream.endText();
                    yPosition -= 15;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText("Ventas realizadas: " + topEmpleado.getNumVentas());
                    contentStream.endText();
                    yPosition -= 15;

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText("Total vendido: $" + String.format("%.2f", topEmpleado.getTotalVendido()));
                    contentStream.endText();
                    yPosition -= 30;
                }

                // Tabla de empleados
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("LISTA COMPLETA DE EMPLEADOS");
                contentStream.endText();
                yPosition -= 20;

                // Encabezados de tabla
                float[] columnWidths = {100, 100, 100, 80};
                String[] headers = {"Nombre", "Apellidos", "Puesto", "Sueldo"};

                // Dibujar encabezados
                drawTableHeader(contentStream, margin, yPosition, tableWidth, columnWidths, headers);
                yPosition -= 20;

                // Dibujar filas
                for (EmpleadoDAO emp : empleados) {
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

                    String[] rowData = {
                            emp.getNombre(),
                            emp.getPrimer_apellido() + " " + emp.getSegundo_apellido(),
                            String.valueOf(emp.getId_puesto()),
                            "$" + String.format("%.2f", emp.getSueldo())
                    };

                    drawTableRow(contentStream, margin, yPosition, tableWidth, columnWidths, rowData);
                    yPosition -= 15;
                }
            } finally {
                if (contentStream != null) {
                    contentStream.close();
                }
            }

            // Guardar el documento
            String fileName = "ReporteEmpleados_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            document.save(fileName);

            // Abrir el archivo (opcional)
            java.awt.Desktop.getDesktop().open(new java.io.File(fileName));

        } catch (IOException e) {
            e.printStackTrace();
            // Aquí podrías mostrar un mensaje de error al usuario
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