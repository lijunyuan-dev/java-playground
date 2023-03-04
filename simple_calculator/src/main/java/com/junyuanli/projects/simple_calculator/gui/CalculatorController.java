package com.junyuanli.projects.simple_calculator.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField calculatorDisplay;

    @FXML
    protected void onNumberButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        calculatorDisplay.appendText(button.getText());
    }

    @FXML
    protected void onSwitchSignButtonClick() {
        String calculatorDisplayText = calculatorDisplay.getText();
        if (calculatorDisplayText.startsWith("-")) {
            calculatorDisplayText = calculatorDisplayText.substring(1);
        } else {
            calculatorDisplayText = "-".concat(calculatorDisplayText);
        }
        calculatorDisplay.setText(calculatorDisplayText);
    }

    @FXML
    protected void onClearButtonClick() {
        calculatorDisplay.clear();
    }

}