package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.math.Constraint;
import com.bzdeco.linearprogramming.math.Space;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.util.UserInput;

import java.util.List;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Application {

    private static UserInput userInput = new UserInput();

    public static void main(String[] args) {

        int numberOfVariables = userInput.askForNumberOfVariables();
        int numberOfConstraints = userInput.askForNumberOfConstraints();
        List<String> variablesNames = userInput.askForVariablesNames(numberOfVariables);

        ObjectiveFunction objectiveFunction = userInput.askForObjectiveFunction(variablesNames);
        List<Constraint> constraints = userInput.askForConstraints(variablesNames);
        Space space = userInput.askForSpace(variablesNames);

        MonteCarloSolver solver = new MonteCarloSolver(objectiveFunction, constraints, space);
        System.out.println(solver);
    }
}
