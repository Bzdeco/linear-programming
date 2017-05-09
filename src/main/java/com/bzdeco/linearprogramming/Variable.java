package com.bzdeco.linearprogramming;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Variable {

    private double coefficient;
    private int exponent;
    private double lowerBound;
    private double upperBound;

    public Variable(double coefficient, int exponent, double lowerBound, double upperBound) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public double evaluateForValue(double value) {

        return coefficient * Math.pow(value, exponent);
    }
}
