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
    private int previousOperatorPosition = -1;
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
        previousOperatorPosition = -1;
        calculatorDisplay.clear();
        calculatorDisplay.setText("0");
    }

    @FXML
    private void onOperatorButtonClick(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            if (calculatorDisplay.getText().length() == previousOperatorPosition + 1) {
                calculatorDisplay.replaceText(
                        previousOperatorPosition, previousOperatorPosition + 1, button.getText());
                calculatorStateManager.changeOperation(button.getText());
                return;
            }
            calculatorStateManager.addOperand(
                    calculatorDisplay.getText().substring(previousOperatorPosition + 1));

            boolean isReadyToCalculate = calculatorStateManager.isReadyToCalculate();
            calculatorStateManager.attemptCalculation();
            calculatorStateManager.changeOperation(button.getText());

            if (isReadyToCalculate) {
                calculatorDisplay.setText(calculatorStateManager.getOperands().get(0).toString());
            }

            calculatorDisplay.appendText(button.getText());
            previousOperatorPosition = calculatorDisplay.getLength() - 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    @FXML
    private void onEvaluateButtonClick() {
        calculatorStateManager.addOperand(
                calculatorDisplay.getText().substring(previousOperatorPosition + 1));

        boolean isReadyToCalculate = calculatorStateManager.isReadyToCalculate();
        calculatorStateManager.attemptCalculation();

        if (isReadyToCalculate) {
            calculatorDisplay.setText(calculatorStateManager.getOperands().get(0).toString());
        }

        previousOperatorPosition = calculatorDisplay.getLength() - 1;
    }
}