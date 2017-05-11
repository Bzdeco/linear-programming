package com.bzdeco.linearprogramming.math;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Variable {

    private double coefficient;
    private int exponent;
    private String name;

    public Variable(String name, double coefficient, int exponent) {
        this.name = name;
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public String getName() {
        return name;
    }

    public double evaluateFor(double value) {

        return coefficient * Math.pow(value, exponent);
    }
}
