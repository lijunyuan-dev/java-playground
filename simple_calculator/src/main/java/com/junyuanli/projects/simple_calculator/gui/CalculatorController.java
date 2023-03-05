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
        String processedDisplayText;
        String calculatorDisplayText = calculatorDisplay.getText();
        if (calculatorDisplayText.startsWith("-")) {
            processedDisplayText = calculatorDisplayText.substring(1);
        } else {
            processedDisplayText = "-".concat(calculatorDisplayText);
        }
        calculatorDisplay.setText(processedDisplayText);
    }

    @FXML
    protected void onClearButtonClick() {
        calculatorDisplay.clear();
    }

    @FXML
    protected void onOperatorButtonClick() {

    }

}