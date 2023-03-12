package com.junyuanli.projects.simple_calculator.core;

import java.util.ArrayList;

abstract class Operation {
    abstract int getRequiredNumOfOperands();
    abstract double calculate(ArrayList<Double> operands);

}

abstract class BinaryOperation extends Operation {
    @Override
    public int getRequiredNumOfOperands() {
        return 2;
    }
}

class Addition extends BinaryOperation {
    @Override
    public double calculate(ArrayList<Double> operands) {
        return operands.get(0) + operands.get(1);
    }
}

class Subtraction extends BinaryOperation {
    @Override
    public double calculate(ArrayList<Double> operands) {
        return operands.get(0) - operands.get(1);
    }
}

class Multiplication extends BinaryOperation {
    @Override
    public double calculate(ArrayList<Double> operands) {
        return operands.get(0) * operands.get(1);
    }
}

//class Division extends BinaryOperation {
//    @Override
//    public double calculate() throws ZeroDivisionException {
//        if (operand2 == 0.0d) {
//            throw new ZeroDivisionException();
//        }
//        return operand1 / operand2;
//    }
//    static class ZeroDivisionException extends Exception {
//    }
//}