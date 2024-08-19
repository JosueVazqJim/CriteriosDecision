package org.vazquezj.criterios.criteriosdecisiones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.vazquezj.criterios.criteriosdecisiones.criterios.CriterioCal;

public class CriteriosController {
    @FXML
    private TextField rowsField;

    @FXML
    private TextField colsField;

    @FXML
    private GridPane matrixGridPane;

    @FXML
    private TextField probExitoField;

    @FXML
    private TextField probFracasoField;

    @FXML
    private TextArea resultArea;

    @FXML
    private Label welcomeText;

    @FXML
    private Label unText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onHelloButtonClick2() {
        unText.setText("Welcome to here!");
    }

    public void handleCreateMatrix(ActionEvent actionEvent) {
        System.out.println("Create Matrix");
        // Obtener el contenido de los TextFields
        String rowsText = rowsField.getText();
        String colsText = colsField.getText();

        // Imprimir el contenido para verificar
        System.out.println("Filas: " + rowsText);
        System.out.println("Columnas: " + colsText);

        // Obtener el número de filas y columnas desde los TextField
        int numRows = 0;
        int numCols = 0;
        try {
            numRows = Integer.parseInt(rowsField.getText());
            numCols = Integer.parseInt(colsField.getText());
        } catch (NumberFormatException e) {
            // Manejo de error si la entrada no es válida
            System.out.println("Por favor ingrese números válidos para filas y columnas.");
            return;
        }

        // Limpiar la matriz actual antes de crear una nueva
        matrixGridPane.getChildren().clear();

        // Crear la matriz de TextFields
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                TextField cell = new TextField();
                cell.setPromptText("R" + row + " C" + col);
                // Agregar el TextField al GridPane en la posición correspondiente
                matrixGridPane.add(cell, col, row);
            }
        }
    }

    // Método para manejar el evento del botón Calcular
    public void handleCalculate(ActionEvent actionEvent) {
        System.out.println("Calculate");
        // Obtener el número de filas y columnas de la matriz
        int numRows = Integer.parseInt(rowsField.getText());
        int numCols = Integer.parseInt(colsField.getText());

        // Crear una matriz para almacenar los valores de los TextField
        double[][] matrix = new double[numRows][numCols];

        // Recorrer la matriz de TextFields y obtener los valores
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Buscar el TextField en la posición actual
                TextField cell = getTextFieldAt(row, col);
                if (cell != null) {
                    // Obtener el valor del TextField y convertirlo a double
                    try {
                        double value = Double.parseDouble(cell.getText());
                        // Almacenar el valor en la matriz
                        matrix[row][col] = value;
                    } catch (NumberFormatException e) {
                        // Manejo de error si la entrada no es válida
                        System.out.println("Por favor ingrese números válidos en la matriz.");
                        return;
                    }
                } else {
                    System.out.println("No se encontró el TextField en la posición (" + row + ", " + col + ").");
                    return;
                }
            }
        }

        // Imprimir matriz para verificar
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        double exito = 0;
        double fracaso = 0;
        try{
            exito = Double.parseDouble(probExitoField.getText());
            fracaso = Double.parseDouble(probFracasoField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese números válidos para probabilidad de éxito y fracaso.");
            return;
        }
        CriterioCal criterios = new CriterioCal(matrix, exito, fracaso);
        resultArea.setText("Mejores decisiones\n\n" + "Criterio MaxiMin: " + criterios.criterioMaxiMin() + "\n" +
                "Criterio MaxiMax: " + criterios.criterioMaxiMax() + "\n" +
                "Criterio Laplace: " + criterios.criterioLaplace() + "\n" +
                "Criterio Hurwicz: " + criterios.criterioHurtwicz());
    }

    // Método auxiliar para obtener un TextField en una posición específica
    private TextField getTextFieldAt(int row, int col) {
        for (javafx.scene.Node node : matrixGridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col &&
                    node instanceof TextField) {
                return (TextField) node;
            }
        }
        return null;
    }

}