package com.junyuanli.projects.simple_calculator.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalculatorController {
    @FXML
    private GridPane calculatorWindow;
    @FXML
    private TextField calculatorDisplay;

    @FXML
    public void initialize() {
        calculatorDisplay.setText("0");
        Platform.runLater(() -> calculatorWindow.requestFocus());
    }

    @FXML
    private void onNumberButtonClick(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            if (calculatorDisplay.getText().equals("0")) {
                calculatorDisplay.replaceText(0, 1, button.getText());
                return;
            }
            calculatorDisplay.appendText(button.getText());
            return;
        }
        throw new UnsupportedOperationException();
    }

    @FXML
    private void onSwitchSignButtonClick() {
        String processedDisplayText;
        String calculatorDisplayText = calculatorDisplay.getText();
        if (calculatorDisplayText.equals("0")) {
            return;
        }
        if (calculatorDisplayText.startsWith("-")) {
            processedDisplayText = calculatorDisplayText.substring(1);
        } else {
            processedDisplayText = "-".concat(calculatorDisplayText);
        }
        calculatorDisplay.setText(processedDisplayText);
    }

    @FXML
    private void onClearButtonClick() {
        calculatorDisplay.clear();
        calculatorDisplay.setText("0");
    }

    @FXML
    private void onOperatorButtonClick() {

    }
}