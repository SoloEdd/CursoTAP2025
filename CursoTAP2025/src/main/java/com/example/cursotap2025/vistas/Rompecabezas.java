package com.example.cursotap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rompecabezas extends Stage {

    private Scene scene;
    private BorderPane root = new BorderPane();
    private VBox panelDerecho = new VBox();
    private VBox panelIzquierdo = new VBox();
    private VBox panelCentral = new VBox();
    private HBox panelSuperior = new HBox();
    private GridPane puzzleArea = new GridPane();
    private GridPane piecesArea = new GridPane();
    private Label title;
    private Label mode;
    private Label timerLabel = new Label("Tiempo: 0s");
    private ComboBox<String> gameMode = new ComboBox<>();
    private Button play;

    private List<Image> puzzlePieces = new ArrayList<>();
    private List<ImageView> puzzlePieceViews = new ArrayList<>();
    private int rows = 4;
    private int cols = 4;

    public Rompecabezas() {
        CrearUI();
        this.setTitle("Rompecabezas");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        CrearPanelSuperior();
        CrearPanelIzquierdo();
        CrearPanelCentral();
        scene = new Scene(root, 900, 600);
    }

    private void CrearPanelSuperior() {
        panelSuperior.getChildren().add(timerLabel);
        panelSuperior.setAlignment(Pos.CENTER);
        panelSuperior.setPadding(new Insets(10));
        root.setTop(panelSuperior);
    }

    private void CrearPanelIzquierdo() {
        title = new Label("Rompecabezas");
        mode = new Label("Dificultad");
        gameMode.setPromptText("Elige la dificultad");
        gameMode.getItems().addAll("Fácil (4x4)", "Medio (6x6)", "Difícil (8x8)");
        play = new Button("Jugar!");

        play.setOnAction(e -> iniciarJuego());

        panelIzquierdo.getChildren().addAll(title, mode, gameMode, play, piecesArea);
        panelIzquierdo.setPadding(new Insets(10, 10, 10, 10));
        panelIzquierdo.setSpacing(10);
        panelIzquierdo.setAlignment(Pos.TOP_CENTER);
        root.setLeft(panelIzquierdo);
    }

    private void CrearPanelCentral() {
        puzzleArea.setHgap(2);
        puzzleArea.setVgap(2);
        puzzleArea.setAlignment(Pos.CENTER);

        panelCentral.getChildren().add(puzzleArea);
        panelCentral.setAlignment(Pos.CENTER);
        root.setCenter(panelCentral);
    }

    private void CrearPanelDerecho(){
        panelDerecho.setPadding(new Insets(20));
        panelDerecho.setSpacing(15);
        panelDerecho.setStyle("-fx-background-color: #f0f0f0;");
        root.setRight(panelDerecho);
    }

    private void iniciarJuego() {
        cargarImagenes();
        puzzleArea.getChildren().clear();
        piecesArea.getChildren().clear();
        puzzlePieceViews.clear();

        // Configurar tamaño según dificultad
        String dificultad = gameMode.getValue();
        if (dificultad != null) {
            if (dificultad.equals("Medio (6x6)")) {
                rows = 6;
                cols = 6;
            } else if (dificultad.equals("Difícil (8x8)")) {
                rows = 8;
                cols = 8;
            } else {
                rows = 4;
                cols = 4;
            }
        }

        // Crear áreas vacías para el rompecabezas
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Pane placeholder = new Pane();
                placeholder.setMinSize(80, 80);
                placeholder.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
                puzzleArea.add(placeholder, col, row);

                // Configurar para aceptar piezas
                placeholder.setOnDragOver(event -> {
                    if (event.getGestureSource() != placeholder && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                placeholder.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        int pieceIndex = Integer.parseInt(db.getString());
                        ImageView piece = puzzlePieceViews.get(pieceIndex);

                        // Verificar si el lugar está vacío
                        if (((Pane) event.getSource()).getChildren().isEmpty()) {
                            ImageView newPiece = new ImageView(piece.getImage());
                            newPiece.setFitWidth(80);
                            newPiece.setFitHeight(80);
                            ((Pane) event.getSource()).getChildren().add(newPiece);
                            event.setDropCompleted(true);
                        } else {
                            event.setDropCompleted(false);
                        }
                    }
                    event.consume();
                });
            }
        }

        // Mostrar piezas desorganizadas
        mostrarPiezasDesorganizadas();
    }

    private void cargarImagenes() {
        puzzlePieces.clear();
        try {
            for (int row = 1; row <= 4; row++) {
                for (int col = 1; col <= 4; col++) {
                    String imagePath = "/images/Manzanilla/row-" + row + "-column-" + col + ".jpg";
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    puzzlePieces.add(image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarPiezasDesorganizadas() {
        piecesArea.getChildren().clear();
        piecesArea.setHgap(5);
        piecesArea.setVgap(5);

        // Mezclar las piezas
        List<Image> shuffledPieces = new ArrayList<>(puzzlePieces);
        Collections.shuffle(shuffledPieces);

        // Mostrar las piezas mezcladas
        int index = 0;
        for (Image piece : shuffledPieces) {
            ImageView imageView = new ImageView(piece);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setUserData(index);

            // Configurar drag and drop
            imageView.setOnDragDetected(event -> {
                Dragboard db = imageView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(imageView.getUserData()));
                db.setContent(content);
                event.consume();
            });

            piecesArea.add(imageView, index % 4, index / 4);
            puzzlePieceViews.add(imageView);
            index++;
        }
    }
}
