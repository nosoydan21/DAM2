package org.example.vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.modelo.*;
import org.example.controlador.Inventario;
import org.example.controlador.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppTiendaGrafica extends Application {
    private BorderPane root = new BorderPane();
    private Inventario inventario;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        try {
            inventario=new Inventario();

        } catch (SQLException e) {
            showError(e.getMessage());
        } catch (IOException e) {
            showError(e.getMessage());;
        }


        MenuBar menuBar = crearMenu();

       root.setTop(menuBar);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventario Tienda Online");
        primaryStage.show();
    }

    private MenuBar crearMenu() {
        Menu menu = new Menu("Opciones");

        MenuItem insertar = new MenuItem("Insertar producto");
        MenuItem vender = new MenuItem("Vender producto");
        MenuItem reponer = new MenuItem("Reponer producto");
        MenuItem mostrar = new MenuItem("Mostrar inventario");
        MenuItem salir = new MenuItem("Salir y guardar");

        insertar.setOnAction(e -> root.setCenter(pantallaInsertarProducto()));
        vender.setOnAction(e -> root.setCenter(pantallaVenderReponer(true)));
        reponer.setOnAction(e -> root.setCenter(pantallaVenderReponer(false)));
        mostrar.setOnAction(e -> root.setCenter(pantallaMostrarInventario()));
        salir.setOnAction(e -> {


                showInfo("Datos guardados de forma correcta");

            System.exit(0);
        });

        menu.getItems().addAll(insertar, vender, reponer, mostrar, salir);

        return new MenuBar(menu);
    }

    private GridPane pantallaInsertarProducto() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll("Electronico", "Ropa");

        TextField nombre = new TextField();
        TextField precio = new TextField();
        TextField stock = new TextField();

        TextField campoExtra1 = new TextField(); // marca / talla
        TextField campoExtra2 = new TextField(); // garantía / material

        Label l0 = new Label("Tipo:");
        Label l2 = new Label("Nombre:");
        Label l3 = new Label("Precio:");
        Label l4 = new Label("Stock:");
        Label l5 = new Label("Marca / Talla:");
        Label l6 = new Label("Garantía / Material:");

        Button btn = new Button("Guardar");
        btn.setOnAction(e -> {
            String tipo = tipoBox.getValue();
            String nom = nombre.getText();
            double pre = Double.parseDouble(precio.getText());
            int sto = Integer.parseInt(stock.getText());
            String ex1 = campoExtra1.getText();
            String ex2 = campoExtra2.getText();
            if (tipo.equals("Electronico")){
                Electronico productoElectronico=new Electronico(nom, pre,sto,ex1,Integer.parseInt(ex2));
                try {
                    if (inventario.insertarProducto(productoElectronico)){
                        showInfo("Producto insertado correctamente");
                    }
                    else{
                        showInfo("El producto con ese id ya está en el inventario");
                    }
                } catch (SQLException ex) {
                    showError(ex.getMessage());
                }
            }
            else{
                Ropa productoRopa=new Ropa(nom, pre,sto,ex1,ex2);
                try {
                    if (inventario.insertarProducto(productoRopa)){
                        showInfo("Producto insertado correctamente");
                    }
                    else{
                        showInfo("El producto con ese id ya está en el inventario");
                    }
                } catch (SQLException ex) {
                    showError(ex.getMessage());
                }
            }
        });

        grid.addRow(0, l0, tipoBox);
        grid.addRow(1, l2, nombre);
        grid.addRow(2, l3, precio);
        grid.addRow(3, l4, stock);
        grid.addRow(4, l5, campoExtra1);
        grid.addRow(5, l6, campoExtra2);
        grid.add(btn, 1, 6);

        return grid;
    }
    private VBox pantallaMostrarInventario() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        TableView<Producto> table = new TableView<>();

        TableColumn<Producto, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Producto, String> tipoCol = new TableColumn<>("Tipo");
        tipoCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue() instanceof Electronico ? "Electronico" : "Ropa"));

        TableColumn<Producto, Number> precioCol = new TableColumn<>("Precio");
        precioCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()));

        TableColumn<Producto, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()));

        table.getColumns().addAll(nombreCol, tipoCol, precioCol, stockCol);
        ArrayList<Producto> listaProductos= null;
        try {
            listaProductos = inventario.getListaProductos();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
        for (Producto p:listaProductos){
            table.getItems().add(p);
        }


        box.getChildren().add(table);
        return box;
    }

    private VBox pantallaVenderReponer(boolean esVenta) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        ComboBox<Producto> combo = new ComboBox<>();
        ArrayList<Producto> listaProductos= null;
        try {
            listaProductos = inventario.getListaProductos();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
        for (Producto p:listaProductos){
            combo.getItems().add(p);
        }
        TextField cantidadField = new TextField();
        Button btn = new Button(esVenta ? "Vender" : "Reponer");
        btn.setOnAction(e -> {
            Producto p = combo.getValue();
            int cantidad = Integer.parseInt(cantidadField.getText());
            if (esVenta){
                try {
                    if (inventario.venderProducto(p.getId(),cantidad)){
                        showInfo("Venta realizada correctamente");
                    }
                    else{
                        showInfo("Venta no realizada. No hay stock suficiente");
                    }
                } catch (SQLException ex) {
                   showError(ex.getMessage());
                }
            }
            else{
                try {
                    inventario.reponerProducto(p.getId(),cantidad);
                } catch (SQLException ex) {
                    showError(ex.getMessage());
                }
                showInfo("Producto respuesto de forma correcta.");
            }



        });

        box.getChildren().addAll(new Label("Producto:"), combo, new Label("Cantidad:"), cantidadField, btn);
        return box;
    }


    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }

}
