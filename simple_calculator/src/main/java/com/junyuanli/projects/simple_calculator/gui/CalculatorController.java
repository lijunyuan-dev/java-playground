package com.junyuanli.projects.simple_calculator.gui;

import com.junyuanli.projects.simple_calculator.core.CalculatorStateManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalculatorController {

    private final CalculatorStateManager calculatorStateManager = CalculatorStateManager.getCalculatorStateManager();
    private boolean endingInNumber = true;
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
        calculatorStateManager.resetCalculator();
        calculatorDisplay.clear();
        calculatorDisplay.setText("0");
    }

    /**
     * Expected behavior:
     * 1. Change the operator when not ending with number
     * 2. Add this operator to the end if ending with number and operand number is not enough (is not ready to calculate).
     * 3. Attempt calculation if ending with number and operand number is enough (is ready to calculate).
     * **/

    @FXML
    private void onBinaryOperatorButtonClick(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            if (endingInNumber) {
                if (calculatorStateManager.isReadyToCalculate()) {
                    // Attempt calculation
                } else {
                    // Add this operator to the end
                }
            } else {
                // Change Operator
            }
            return;
        }
        throw new UnsupportedOperationException();
    }

    @FXML
    private void onEvaluateButtonClick() {

    }

}