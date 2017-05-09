package com.bzdeco.linearprogramming;

/**
 * Created by bzdeco on 09.05.17.
 */
public class ObjectiveFunction {

    private Polynomial polynomial;
    private ObjectiveFunctionGoal goal;

    public ObjectiveFunction(Polynomial polynomial, ObjectiveFunctionGoal goal) {
        this.polynomial = polynomial;
        this.goal = goal;
    }
}
