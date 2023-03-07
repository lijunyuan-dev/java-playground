package com.junyuanli.projects.simple_calculator.core;

import java.util.HashMap;

public final class CalculatorStateManager {

    // Singleton Pattern
    private static final CalculatorStateManager manager = new CalculatorStateManager();
    private final HashMap<String, Operation> allOperations = new HashMap<>();
    private Operation currentOperation = null;
    private int numberOfOperands = 0;

    // Prevent out-of-class instantiation
    private CalculatorStateManager() {}

    public static CalculatorStateManager initialize() {
        initializeOperations();
        return manager;
    }

    private static void initializeOperations() {
        manager.allOperations.putIfAbsent("+", new Addition());
        manager.allOperations.putIfAbsent("-", new Subtraction());
        manager.allOperations.putIfAbsent("×", new Multiplication());
    }

    public void updateOperation(String operation) {
        currentOperation = allOperations.get(operation);
    }
}
