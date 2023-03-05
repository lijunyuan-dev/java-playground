package com.junyuanli.projects.simple_calculator.core;

public abstract class Operation {
    public abstract double calculate();
}

abstract class BinaryOperation extends Operation {

}

class Addition extends Operation {
    @Override
    public double calculate() {
        return 0;
    }
}

class Subtraction extends Operation {
    @Override
    public double calculate() {
        return 0;
    }
}