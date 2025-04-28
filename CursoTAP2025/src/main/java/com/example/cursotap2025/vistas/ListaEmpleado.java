package com.example.cursotap2025.vistas;

import com.example.cursotap2025.componentes.ButtonCellEmpleado;
import com.example.cursotap2025.componentes.ButtonCellPuesto;
import com.example.cursotap2025.models.EmpleadoDAO;
import com.example.cursotap2025.models.PuestoTrabajoDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaEmpleado extends VBox {

    private ToolBar tblMenu;
    private TableView<EmpleadoDAO> tbvEmpleados;
    private TableView<PuestoTrabajoDAO> tbvPuestos;
    private Button btnAgregarEmpleado, btnAgregarPuestoTrabajo;
    private Label lblPuesto, lblEmpleado;

    public ListaEmpleado() {
        CrearUI();
    }

    private void CrearUI() {
        lblPuesto = new Label("Lista de puesto de trabajo");
        lblEmpleado = new Label("Lista de empleados");
        tbvEmpleados = new TableView<>();
        tbvPuestos = new TableView<>();
        btnAgregarEmpleado = new Button();
        ImageView imv = new ImageView(getClass().getResource("/images/person_add_icon.png").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregarEmpleado.setGraphic(imv);
        btnAgregarEmpleado.setOnAction(actionEvent -> new Empleado(tbvEmpleados, null));
        btnAgregarPuestoTrabajo = new Button("Agregar Puesto Trabajo");
        btnAgregarPuestoTrabajo.setOnAction(actionEvent -> new PuestoTrabajo(tbvPuestos, null));
        tblMenu = new ToolBar(btnAgregarEmpleado, btnAgregarPuestoTrabajo);
        CreateTablePuesto();
        CreateTable();

        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.getChildren().addAll(tblMenu, lblPuesto, tbvPuestos,lblEmpleado, tbvEmpleados);
        this.getStylesheets().add(getClass().getResource("/Styles/Empleados.css").toExternalForm());
    }

    private void CreateTablePuesto() {
        PuestoTrabajoDAO empDAO = new PuestoTrabajoDAO();
        TableColumn<PuestoTrabajoDAO, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id_puesto"));
        TableColumn<PuestoTrabajoDAO, String> colNombrePuesto = new TableColumn<>("Puesto de trabajo");
        colNombrePuesto.setCellValueFactory(new PropertyValueFactory<>("nombre_puesto"));
        TableColumn<PuestoTrabajoDAO, String> colDescripcion = new TableColumn<>("Descripcion");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<PuestoTrabajoDAO, String> colEditar = new TableColumn<>("Editar informacion");
        colEditar.setCellFactory(new Callback<TableColumn<PuestoTrabajoDAO, String>, TableCell<PuestoTrabajoDAO, String>>() {
            @Override
            public TableCell<PuestoTrabajoDAO, String> call(TableColumn<PuestoTrabajoDAO, String> puestoTrabajoDAOStringTableColumn) {
                return new ButtonCellPuesto("Editar");
            }
        });

        TableColumn<PuestoTrabajoDAO, String> colEliminar = new TableColumn<>("Eliminar puesto");
        colEliminar.setCellFactory(new Callback<TableColumn<PuestoTrabajoDAO, String>, TableCell<PuestoTrabajoDAO, String>>() {
            @Override
            public TableCell<PuestoTrabajoDAO, String> call(TableColumn<PuestoTrabajoDAO, String> puestoTrabajoDAOStringTableColumn) {
                return new ButtonCellPuesto("Eliminar");
            }
        });

        tbvPuestos.getColumns().addAll(colId, colNombrePuesto, colDescripcion, colEditar, colEliminar);
        tbvPuestos.setItems(empDAO.selectAllPuestoTrabajo());
    }

    private void CreateTable() {
        EmpleadoDAO objEmpleado = new EmpleadoDAO();
        TableColumn<EmpleadoDAO, String> colPrimerApellido = new TableColumn<>("Primera apellido");
        colPrimerApellido.setCellValueFactory(new PropertyValueFactory<>("primer_apellido"));
        TableColumn<EmpleadoDAO, String> colSegundoApellido = new TableColumn<>("Segundo apellido");
        colSegundoApellido.setCellValueFactory(new PropertyValueFactory<>("segundo_apellido"));
        TableColumn<EmpleadoDAO, String> colNombre = new TableColumn<>("Nombre del empleado");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<EmpleadoDAO, String> colCURP = new TableColumn<>("CURP");
        colCURP.setCellValueFactory(new PropertyValueFactory<>("curp"));
        TableColumn<EmpleadoDAO, String> colRFC = new TableColumn<>("RFC");
        colRFC.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        TableColumn<EmpleadoDAO, Double> colSueldo = new TableColumn<>("Sueldo por hora");
        colSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
        TableColumn<EmpleadoDAO,String> colTelefono = new TableColumn<>("Telefono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("celEmp"));
        TableColumn<EmpleadoDAO, String> colNSS = new TableColumn<>("NSS del empleado");
        colNSS.setCellValueFactory(new PropertyValueFactory<>("nssEmp"));
        TableColumn<EmpleadoDAO, String> colHorario = new TableColumn<>("Horas trabajadas");
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        TableColumn<EmpleadoDAO,String> colFecha = new TableColumn<>("Fecha de ingreso");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
        TableColumn<EmpleadoDAO, Integer> colRol = new TableColumn<>("Tipo de puesto");
        colRol.setCellValueFactory(new PropertyValueFactory<>("id_puesto"));
        TableColumn<EmpleadoDAO, String> colEditar = new TableColumn<>("Editar informacion");
        colEditar.setCellFactory(new Callback<TableColumn<EmpleadoDAO, String>, TableCell<EmpleadoDAO, String>>() {
            @Override
            public TableCell<EmpleadoDAO, String> call(TableColumn<EmpleadoDAO, String> empleadoDAOStringTableColumn) {
                return new ButtonCellEmpleado("Editar");
            }
        });
        TableColumn<EmpleadoDAO, String> colEliminar = new TableColumn<>("Eliminar informacion");
        colEliminar.setCellFactory(new Callback<TableColumn<EmpleadoDAO, String>, TableCell<EmpleadoDAO, String>>() {
            @Override
            public TableCell<EmpleadoDAO, String> call(TableColumn<EmpleadoDAO, String> empleadoDAOStringTableColumn) {
                return new ButtonCellEmpleado("Eliminar");
            }
        });

        tbvEmpleados.getColumns().addAll(colPrimerApellido, colSegundoApellido, colNombre, colCURP, colRFC, colSueldo, colTelefono, colNSS, colHorario, colFecha, colRol, colEditar, colEliminar);
        tbvEmpleados.setItems(objEmpleado.selectEmpleado());
    }
}
