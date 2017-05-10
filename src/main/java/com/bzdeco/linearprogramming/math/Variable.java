package com.bzdeco.linearprogramming.math;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Variable {

    private double coefficient;
    private int exponent;

    public Variable(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double evaluateFor(double value) {

        return coefficient * Math.pow(value, exponent);
    }
}
