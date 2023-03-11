package com.junyuanli.projects.simple_calculator.core;

import java.util.ArrayList;
import java.util.HashMap;

public final class CalculatorStateManager {

    // Singleton Pattern
    private static final CalculatorStateManager manager = new CalculatorStateManager();
    private final HashMap<String, Operation> allOperations = new HashMap<>();
    private Operation currentOperation = null;
    private ArrayList<Double> operands;
    private int numberOfOperands = 0;
    private double calculationResult = 0.0d;

    // Prevent out-of-class instantiation
    private CalculatorStateManager() {}

    public static CalculatorStateManager getCalculatorStateManager() {
        initializeOperations();
        return manager;
    }

    private static void initializeOperations() {
        manager.allOperations.putIfAbsent("+", new Addition());
        manager.allOperations.putIfAbsent("-", new Subtraction());
        manager.allOperations.putIfAbsent("×", new Multiplication());
    }

    /**
     * Expected to be called on click of an operation button.
     * Returns true if state of calculator is updated, false otherwise.
     */
    public boolean updateCalculatorState(String newOperand, String newOperation) {
        if (currentOperation.getRequiredNumOfOperands() == operands.size()) {
            calculationResult = currentOperation.calculate(operands);
            currentOperation = allOperations.get(newOperation);
            return true;
        }
        operands.add(Double.parseDouble(newOperand));
        currentOperation = allOperations.get(newOperation);
        return false;
    }

    public double getCalculationResult() {
        double result = calculationResult;
        calculationResult = 0.0d;
        return result;
    }
}
