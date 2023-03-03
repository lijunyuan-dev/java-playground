package com.junyuanli.projects.simple_calculator.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField calculatorDisplay;

    @FXML
    protected void onNumberTwoClick() {
        calculatorDisplay.appendText("2");
    }
}