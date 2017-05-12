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

        // Ask for problem parameters
        int numberOfVariables = userInput.askForNumberOfVariables();
        int numberOfConstraints = userInput.askForNumberOfConstraints();
        List<String> variablesNames = userInput.askForVariablesNames(numberOfVariables);
        ObjectiveFunction objectiveFunction = userInput.askForObjectiveFunction(variablesNames);
        List<Constraint> constraints = userInput.askForConstraints(variablesNames);
        Space space = userInput.askForSpace(variablesNames);

        // Setup Monte Carlo solver
        MonteCarloSolver solver = new MonteCarloSolver(objectiveFunction, constraints, space);
        solver.setNumberOfProbes(1000);
        solver.setConvergenceRate(100);
        solver.setPrecision(1e-5);

        while(!solver.isSolutionAccurate()) {
            solver = solver.reduceToNewProblems(1).get(0);
        }

        System.out.println(solver);
    }
}
