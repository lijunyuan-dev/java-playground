package com.junyuanli.projects.simple_calculator.core;

public abstract class Operation {
    public abstract double calculate() throws Division.ZeroDivisionException;
}

abstract class BinaryOperation extends Operation {
    protected double operand1;
    protected double operand2;


}

class Addition extends BinaryOperation {
    @Override
    public double calculate() {
        return operand1 + operand2;
    }
}

class Subtraction extends BinaryOperation {
    @Override
    public double calculate() {
        return operand1 - operand2;
    }
}

class Multiplication extends BinaryOperation {
    @Override
    public double calculate() {
        return operand1 * operand2;
    }
}

class Division extends BinaryOperation {
    @Override
    public double calculate() throws ZeroDivisionException {
        if (operand2 == 0.0d) {
            throw new ZeroDivisionException();
        }
        return operand1 * operand2;
    }
    static class ZeroDivisionException extends Exception {
    }
}