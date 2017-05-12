package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.math.*;
import com.bzdeco.linearprogramming.math.functions.MinimizedFunction;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.math.limits.GreaterOrEqual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class ApplicationTest {
    @Test
    public void exampleProblemSolving() throws Exception {

        // Example problem:
        // F = 6x + 9y -> min
        // 3x + 9y >= 27
        // 8x + 4y >= 32
        // 12x + 3y >= 36
        // x, y >= 0

        List<Variable> functionPolynomialVariables = new ArrayList<>();
        functionPolynomialVariables.add(new Variable("x", 6, 1));
        functionPolynomialVariables.add(new Variable("y", 9, 1));
        Polynomial functionPolynomial = new Polynomial(functionPolynomialVariables);
        ObjectiveFunction minFunction = new MinimizedFunction(functionPolynomial);

        List<Constraint> constraints = new ArrayList<>();

        List<Variable> firstConstraintVariables = new ArrayList<>();
        firstConstraintVariables.add(new Variable("x", 3, 1));
        firstConstraintVariables.add(new Variable("y", 9, 1));
        Polynomial firstConstraintPolynomial = new Polynomial(firstConstraintVariables);
        constraints.add(new Constraint(firstConstraintPolynomial, new GreaterOrEqual(27)));

        List<Variable> secondConstraintVariables = new ArrayList<>();
        secondConstraintVariables.add(new Variable("x", 8, 1));
        secondConstraintVariables.add(new Variable("y", 4, 1));
        Polynomial secondConstraintPolynomial = new Polynomial(secondConstraintVariables);
        constraints.add(new Constraint(secondConstraintPolynomial, new GreaterOrEqual(32)));

        List<Variable> thirdConstraintVariables = new ArrayList<>();
        thirdConstraintVariables.add(new Variable("x", 12, 1));
        thirdConstraintVariables.add(new Variable("y", 3, 1));
        Polynomial thirdConstraintPolynomial = new Polynomial(thirdConstraintVariables);
        constraints.add(new Constraint(thirdConstraintPolynomial, new GreaterOrEqual(36)));

        List<Bounds> bounds = new ArrayList<>();
        bounds.add(new Bounds(0, 20));
        bounds.add(new Bounds(0, 20));
        Space space = new Space(bounds);


        MonteCarloSolver solver = new MonteCarloSolver(minFunction, constraints, space);
        solver.setNumberOfProbes(1000);
        solver.setConvergenceRate(2);
        solver.setPrecision(1e-5);

        System.out.println(solver);

        int numberOfIterations = 0;
        while(!solver.isSolutionAccurate()) {
            solver = solver.reduceToNewProblems(1).get(0);
            numberOfIterations++;
        }

        System.out.println("Number of iterations: " + numberOfIterations);
        System.out.println("Solution:");
        System.out.println(solver.getSolution());
    }

}