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
    private int previousDisplayLength = 0;
    @FXML
    private GridPane calculatorWindow;
    @FXML
    private TextField calculatorDisplay;

    @FXML
    public void initialize() {
        this.calculatorDisplay.setText("0");
        Platform.runLater(() -> calculatorWindow.requestFocus());
    }

    @FXML
    private void onNumberButtonClick(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            if (this.calculatorDisplay.getText().equals("0")) {
                this.calculatorDisplay.replaceText(0, 1, button.getText());
                return;
            }
            this.calculatorDisplay.appendText(button.getText());
            this.endingInNumber = true;
            return;
        }
        throw new UnsupportedOperationException();
    }

    @FXML
    private void onSwitchSignButtonClick() {
        String processedDisplayText;
        String calculatorDisplayText = this.calculatorDisplay.getText();
        if (calculatorDisplayText.equals("0")) {
            return;
        }
        if (calculatorDisplayText.startsWith("-")) {
            processedDisplayText = calculatorDisplayText.substring(1);
        } else {
            processedDisplayText = "-".concat(calculatorDisplayText);
        }
        this.calculatorDisplay.setText(processedDisplayText);
    }

    @FXML
    private void onClearButtonClick() {
        this.calculatorStateManager.resetCalculator();
        this.previousDisplayLength = 0;
        this.calculatorDisplay.setText("0");
    }

    /**
     * Expected behavior:
     * 1. Change the operator when not ending with number
     * 2. Add this operator to the end if ending with number and operand number is not enough (is not ready to calculate).
     * 3. Attempt calculation if ending with number and operand number is enough (is ready to calculate).
     **/

    @FXML
    private void onBinaryOperatorButtonClick(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            String newOperator = button.getText();
            int displayLength = this.calculatorDisplay.getLength();
            if (this.endingInNumber) {
                assert this.calculatorStateManager.getOperands().size() == 1;
                // if no operator yet, this means we're changing the first operand
                // otherwise we're adding an operand
                if (this.calculatorStateManager.getCurrentOperation() == null) {
                    this.calculatorStateManager.updateOperand(this.calculatorDisplay.getText(), 0);
                    this.previousDisplayLength = displayLength;
                } else {
                    this.calculatorStateManager.addOperand(
                            this.calculatorDisplay.getText(
                                    this.previousDisplayLength, displayLength
                            )
                    );
                    if (this.calculatorStateManager.isReadyToCalculate()) {
                        this.calculatorStateManager.attemptCalculation();
                        double calculatedResult = this.calculatorStateManager.getOperands().get(0);
                        long calculatedResultLong = Math.round(calculatedResult);
                        if (calculatedResult == calculatedResultLong) {
                            this.calculatorDisplay.setText(String.valueOf(calculatedResultLong));
                        } else {
                            this.calculatorDisplay.setText(String.valueOf(calculatedResult));
                        }
                        this.previousDisplayLength = this.calculatorDisplay.getLength();
                    }
//                        System.out.println(calculatorStateManager.getOperands());
                }
                this.calculatorStateManager.changeOperation(newOperator);
                this.calculatorDisplay.appendText(newOperator);
                this.endingInNumber = false;
                this.previousDisplayLength += 1;

            } else {
                this.calculatorStateManager.changeOperation(newOperator);
                this.calculatorDisplay.replaceText(displayLength - 1, displayLength, newOperator);
            }
            return;
        }
        throw new UnsupportedOperationException();
    }

    @FXML
    private void onEvaluateButtonClick() {

    }

}