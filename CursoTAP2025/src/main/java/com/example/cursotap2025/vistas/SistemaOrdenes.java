package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.models.ProductoDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SistemaOrdenes extends Stage {

    private EmpleadoDAO empleado;
    private VBox vBoxEmpleado, vBoxMesas;
    private HBox hBoxOpcionesMenu;
    private HBox hBoxGlobalTop = new HBox();
    private BorderPane root;
    private Scene scene;
    private Button btnBebidasAl, btnPlatillos, btnPostres;
    private Button[] buttons = new Button[9];
    private String numeroMesas [] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Pane panelCentral;
    private int mesaSeleccionada = -1;

    public SistemaOrdenes(EmpleadoDAO empleado) {
        this.empleado = empleado;
        crearUI();
        this.setTitle("Sistema de Órdenes - " + empleado.getNombre());
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }

    private void crearUI() {
        root = new BorderPane();
        panelCentral = new StackPane();
        InforEmpleado();
        padMesas();
        OpcionesMenu();
        hBoxGlobalTop.getChildren().addAll(vBoxEmpleado, hBoxOpcionesMenu);
        hBoxGlobalTop.setAlignment(Pos.CENTER);
        hBoxGlobalTop.setPadding(new Insets(10, 10, 10, 10));
        hBoxGlobalTop.setSpacing(10);
        root.setTop(hBoxGlobalTop);
        root.setCenter(panelCentral);
        scene = new Scene(root);
    }

    private void InforEmpleado(){
        Label lblEmpleado = new Label("Empleado: " + empleado.getNombre() + " " +
                empleado.getPrimer_apellido() + " " +
                empleado.getSegundo_apellido());
        lblEmpleado.setStyle("-fx-font-weight: bold; -fx-padding: 10px;");
        vBoxEmpleado = new VBox(lblEmpleado);
        vBoxEmpleado.setPadding(new Insets(10, 10, 10, 10));
        vBoxEmpleado.setSpacing(10);
        vBoxEmpleado.setAlignment(Pos.CENTER);
    }

    private void padMesas(){
        vBoxMesas = new VBox();
        Label lblMesas = new Label("Mesas");
        vBoxMesas.getChildren().add(lblMesas);
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("Mesa " + numeroMesas[i]);
            final int mesaNum = i + 1;

            buttons[i].setOnAction(e -> {
                mesaSeleccionada = mesaNum;
                actualizarBotonMesas();
                mostrarMensajeCentral("Seleccione una categoría para la Mesa " + mesaNum);
            });
            vBoxMesas.getChildren().add(buttons[i]);
        }
        vBoxMesas.setPadding(new Insets(10, 10, 10, 10));
        vBoxMesas.setSpacing(10);
        vBoxMesas.setAlignment(Pos.CENTER);
        root.setLeft(vBoxMesas);
    }

    private void actualizarBotonMesas() {
        for (int i = 0; i < 9; i++) {
            if ((i + 1) == mesaSeleccionada) {
                buttons[i].setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            } else {
                buttons[i].setStyle("");
            }
        }
    }

    private void OpcionesMenu(){
        btnBebidasAl = new Button("Bebidas Alcohólicas");
        btnPlatillos = new Button("Platillos");
        btnPostres = new Button("Postres");

        btnBebidasAl.setOnAction(e -> mostrarProductosPorCategoria(5));
        btnPlatillos.setOnAction(e -> mostrarProductosPorCategoria(6));
        btnPostres.setOnAction(e -> mostrarProductosPorCategoria(7));

        hBoxOpcionesMenu = new HBox(btnBebidasAl, btnPlatillos, btnPostres);
        hBoxOpcionesMenu.setAlignment(Pos.CENTER);
        hBoxOpcionesMenu.setPadding(new Insets(10, 10, 10, 10));
        hBoxOpcionesMenu.setSpacing(5);
    }

    private void mostrarProductosPorCategoria(int idCategoria) {
        if (mesaSeleccionada == -1) {
            mostrarMensajeCentral("Por favor seleccione una mesa primero");
            return;
        }

        GridPane gridProductos = new GridPane();
        gridProductos.setPadding(new Insets(20));
        gridProductos.setHgap(15);
        gridProductos.setVgap(15);
        gridProductos.setAlignment(Pos.TOP_CENTER);

        ObservableList<ProductoDAO> productos = ProductoDAO.obtenerProductosPorCategoria(idCategoria);

        int columna = 0;
        int fila = 0;
        final int MAX_COLUMNAS = 3;

        for (ProductoDAO producto : productos) {
            VBox cardProducto = crearCardProducto(producto);
            gridProductos.add(cardProducto, columna, fila);
            columna++;
            if (columna >= MAX_COLUMNAS) {
                columna = 0;
                fila++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(gridProductos);
        scrollPane.setFitToWidth(true);
        panelCentral.getChildren().clear();
        panelCentral.getChildren().add(scrollPane);
    }

    private VBox crearCardProducto(ProductoDAO producto) {
        VBox card = new VBox(5);
        card.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        card.setPrefWidth(200);
        card.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView();
        try {
            String imagePath = producto.getImagenPath() != null ?
                    producto.getImagenPath() :
                    "/images/productos/default.png";
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
        }

        Label lblNombre = new Label(producto.getNombre_producto());
        lblNombre.setStyle("-fx-font-weight: bold;");

        Label lblPrecio = new Label("$" + String.format("%.2f", producto.getPrecio()));

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> agregarProductoAOrden(producto));

        card.getChildren().addAll(imageView, lblNombre, lblPrecio, btnAgregar);
        return card;
    }

    private void agregarProductoAOrden(ProductoDAO producto) {
        if (mesaSeleccionada == -1) {
            mostrarMensajeCentral("Seleccione una mesa primero");
            return;
        }

        // Aquí implementarías la lógica para agregar el producto a la orden
        // Por ahora solo mostraremos un mensaje
        mostrarMensajeCentral("Agregado " + producto.getNombre_producto() +
                " a la Mesa " + mesaSeleccionada);

        // TODO: Implementar lógica real de agregar a orden
    }

    private void mostrarMensajeCentral(String mensaje) {
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setStyle("-fx-font-size: 16px;");
        panelCentral.getChildren().clear();
        panelCentral.getChildren().add(lblMensaje);
    }
}