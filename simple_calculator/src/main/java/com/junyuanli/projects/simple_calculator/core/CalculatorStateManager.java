package com.junyuanli.projects.simple_calculator.core;

import java.util.ArrayList;
import java.util.HashMap;

public final class CalculatorStateManager {

    // Singleton Pattern
    private static final CalculatorStateManager calculatorStateManager = new CalculatorStateManager();
    private final HashMap<String, Operation> allOperations = new HashMap<>();
    private final ArrayList<Double> operands = new ArrayList<>();
    private Operation currentOperation = null;


    static {
        initializeOperations();
        initializeOperands();
    }

    // Prevent out-of-class instantiation
    private CalculatorStateManager() {}

    public static CalculatorStateManager getCalculatorStateManager() {
        return CalculatorStateManager.calculatorStateManager;
    }

    private static void initializeOperations() {
        CalculatorStateManager.calculatorStateManager.allOperations.putIfAbsent("+", new Addition());
        CalculatorStateManager.calculatorStateManager.allOperations.putIfAbsent("-", new Subtraction());
        CalculatorStateManager.calculatorStateManager.allOperations.putIfAbsent("×", new Multiplication());
    }

    private static void initializeOperands() {
        CalculatorStateManager.calculatorStateManager.operands.add(0.0d);
    }

    public void addOperand(String newOperand) {
        operands.add(Double.parseDouble(newOperand));
    }

    public void updateOperand(String newOperand, int operandIndex) {
        operands.set(operandIndex, Double.parseDouble(newOperand));
    }

    public void changeOperation(String newOperator) {
        currentOperation = allOperations.get(newOperator);
    }

    public boolean isReadyToCalculate() {
        return (currentOperation != null) && (currentOperation.getRequiredNumOfOperands() == operands.size());
    }

    public void attemptCalculation() {
        if (isReadyToCalculate()) {
            double calculationResult = currentOperation.calculate(operands);
            operands.clear();
            operands.add(calculationResult);
        }
    }

    public void resetCalculator() {
        operands.clear();
        currentOperation = null;
    }

    public ArrayList<Double> getOperands() {
        return this.operands;
    }
}
