package com.example.cursotap2025.vistas;

import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.models.ProductoDAO;
import com.example.cursotap2025.models.ProductoPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.ListChangeListener;

public class SistemaOrdenes extends Stage {

    private EmpleadoDAO empleado;
    private VBox vBoxEmpleado, vBoxMesas;
    private HBox hBoxOpcionesMenu;
    private HBox hBoxGlobalTop = new HBox();
    private BorderPane root;
    private Scene scene;
    private Button btnBebidasAl, btnPlatillos, btnPostres, btnReservaciones, btnCerrarSesion;
    private Button[] buttons = new Button[9];
    private String numeroMesas [] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Pane panelCentral;
    private int mesaSeleccionada = -1;
    private ListView<HBox> lvProductosAgregados;
    private ObservableList<ProductoPedido> productosAgregados = FXCollections.observableArrayList();
    private Label lblTotal;
    private double totalOrden = 0.0;

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
        crearPanelDerecho();

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
        btnReservaciones = new Button("Reservaciones");
        btnCerrarSesion = new Button("Cerrar Sesion");

        btnBebidasAl.setOnAction(e -> mostrarProductosPorCategoria(5));
        btnPlatillos.setOnAction(e -> mostrarProductosPorCategoria(6));
        btnPostres.setOnAction(e -> mostrarProductosPorCategoria(7));
        btnReservaciones.setOnAction(e -> {});
        btnCerrarSesion.setOnAction(e -> {
            this.close();
            new LoginEmpleado();
        });

        hBoxOpcionesMenu = new HBox(btnBebidasAl, btnPlatillos, btnPostres, btnReservaciones, btnCerrarSesion);
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
        // Verifica si el producto ya está en la lista
        for (ProductoPedido pp : productosAgregados) {
            if (pp.getProducto().getId_producto() == producto.getId_producto()) {
                pp.setCantidad(pp.getCantidad() + 1);
                actualizarListaProductos();
                return;
            }
        }
        // Si no existe, lo agrega como nuevo
        productosAgregados.add(new ProductoPedido(producto));
        actualizarListaProductos();
    }

    private void mostrarMensajeCentral(String mensaje) {
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setStyle("-fx-font-size: 16px;");
        panelCentral.getChildren().clear();
        panelCentral.getChildren().add(lblMensaje);
    }

    private void crearPanelDerecho() {
        VBox panelDerecho = new VBox(10);
        panelDerecho.setPadding(new Insets(15));
        panelDerecho.setStyle("-fx-border-color: #ddd; -fx-border-width: 0 0 0 1px;");

        Label lblTitulo = new Label("Productos Agregados");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        lvProductosAgregados = new ListView<>();
        lvProductosAgregados.setPrefWidth(250);
        lvProductosAgregados.setItems(crearItemsLista());
        lvProductosAgregados.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : item);
            }
        });

        lblTotal = new Label("Total: $0.00");
        lblTotal.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button btnFinalizarOrden = new Button("Finalizar Orden");
        btnFinalizarOrden.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnFinalizarOrden.setOnAction(e -> finalizarOrden());

        panelDerecho.getChildren().addAll(lblTitulo, lvProductosAgregados, lblTotal, btnFinalizarOrden);
        root.setRight(panelDerecho);
    }

    private ObservableList<HBox> crearItemsLista() {
        ObservableList<HBox> items = FXCollections.observableArrayList();

        productosAgregados.addListener((ListChangeListener.Change<? extends ProductoPedido> change) -> {
            items.clear();
            totalOrden = 0.0;

            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    for (ProductoPedido productoPedido : productosAgregados) {
                        HBox item = crearItemProducto(productoPedido);
                        items.add(item);
                        totalOrden += productoPedido.getSubtotal();
                    }
                    lblTotal.setText(String.format("Total: $%.2f", totalOrden));
                }
            }
        });

        return items;
    }

    private HBox crearItemProducto(ProductoPedido productoPedido) {
        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(5));

        // Botón para disminuir cantidad
        Button btnMenos = new Button("-");
        btnMenos.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-min-width: 25;");
        btnMenos.setOnAction(e -> {
            if (productoPedido.getCantidad() > 1) {
                productoPedido.setCantidad(productoPedido.getCantidad() - 1);
                actualizarListaProductos();
            }
        });


        Label lblCantidad = new Label(String.valueOf(productoPedido.getCantidad()));
        lblCantidad.setPrefWidth(30);
        lblCantidad.setAlignment(Pos.CENTER);


        Button btnMas = new Button("+");
        btnMas.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-min-width: 25;");
        btnMas.setOnAction(e -> {
            productoPedido.setCantidad(productoPedido.getCantidad() + 1);
            actualizarListaProductos();
        });


        HBox contenedorCantidad = new HBox(5, btnMenos, lblCantidad, btnMas);
        contenedorCantidad.setAlignment(Pos.CENTER);

        Label lblNombre = new Label(productoPedido.getProducto().getNombre_producto());
        lblNombre.setPrefWidth(100);
        lblNombre.setWrapText(true);

        Label lblSubtotal = new Label(String.format("$%.2f", productoPedido.getSubtotal()));
        lblSubtotal.setPrefWidth(60);

        Button btnEliminar = new Button("X");
        btnEliminar.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white;");
        btnEliminar.setOnAction(e -> {
            productosAgregados.remove(productoPedido);
            actualizarListaProductos();
        });

        item.getChildren().addAll(contenedorCantidad, lblNombre, lblSubtotal, btnEliminar);
        return item;
    }

    private void actualizarListaProductos() {
        // Actualiza la lista y el total
        ObservableList<HBox> items = FXCollections.observableArrayList();
        double nuevoTotal = 0.0;

        for (ProductoPedido productoPedido : productosAgregados) {
            items.add(crearItemProducto(productoPedido));
            nuevoTotal += productoPedido.getSubtotal();
        }

        lvProductosAgregados.setItems(items);
        lblTotal.setText(String.format("Total: $%.2f", nuevoTotal));
    }

    private void finalizarOrden() {
        if (mesaSeleccionada == -1 || productosAgregados.isEmpty()) {
            mostrarMensajeCentral("Seleccione mesa y agregue productos");
            return;
        }

        // Calcular el total correctamente
        double totalCorrecto = productosAgregados.stream()
                .mapToDouble(ProductoPedido::getSubtotal)
                .sum();

        // Construir resumen de la orden
        StringBuilder resumen = new StringBuilder();
        resumen.append("Orden para Mesa ").append(mesaSeleccionada).append("\n\n");

        for (ProductoPedido pp : productosAgregados) {
            resumen.append(String.format("%d x %s - $%.2f\n",
                    pp.getCantidad(),
                    pp.getProducto().getNombre_producto(),
                    pp.getSubtotal()));
        }

        resumen.append("\nTotal: $").append(String.format("%.2f", totalCorrecto));

        // Mostrar resumen
        mostrarMensajeCentral(resumen.toString());

        // Limpiar después de guardar
        productosAgregados.clear();
        actualizarListaProductos();
        mesaSeleccionada = -1;
        actualizarBotonMesas();
    }
}