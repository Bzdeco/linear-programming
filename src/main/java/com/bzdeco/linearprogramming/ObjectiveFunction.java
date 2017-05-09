package com.bzdeco.linearprogramming;

/**
 * Created by bzdeco on 09.05.17.
 */
public class ObjectiveFunction {

    private Polynomial polynomial;
    private Goal goal;

    public ObjectiveFunction(Polynomial polynomial, Goal goal) {
        this.polynomial = polynomial;
        this.goal = goal;
    }
}
