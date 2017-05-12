package com.bzdeco.linearprogramming.math;

import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Polynomial {

    private List<Variable> variables;

    public Polynomial(List<Variable> variables) {
        this.variables = variables;
    }

    public double evaluateFor(Point point) {

        List<Double> coordinates = point.getCoordinates();
        double value = 0;

        int i = 0;
        for(Variable variable : variables) {
            value += variable.evaluateFor(coordinates.get(i));
            i++;
        }

        return value;
    }

    @Override
    public String toString() {

        String polynomial = "";
        for(Variable var : variables)
            polynomial += var.toString();

        return polynomial;
    }
}
