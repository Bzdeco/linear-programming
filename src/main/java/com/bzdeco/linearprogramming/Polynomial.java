package com.bzdeco.linearprogramming;

import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Polynomial {

    private List<Variable> variables;

    public Polynomial(List<Variable> variables) {
        this.variables = variables;
    }

    public double evaluateForPoint(Point point) {

        List<Double> coordinates = point.getCoordinates();
        double value = 0;

        int i = 0;
        for(Variable variable : variables) {
            value += variable.evaluateForValue(coordinates.get(i));
            i++;
        }

        return value;
    }
}
