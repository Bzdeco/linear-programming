package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.concurrency.ConcurrentMonteCarloSolver;
import com.bzdeco.linearprogramming.math.*;
import com.bzdeco.linearprogramming.math.functions.MinimizedFunction;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.math.limits.GreaterOrEqual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bzdeco on 14.05.17.
 */
public class ApplicationConcurrentTest {

    @Test
    public void exampleProblemSolvingWithThreads() throws Exception {

        // Example problem:
        // F = 6x + 9y -> min
        // 3x + 9y >= 27
        // 8x + 4y >= 32
        // 12x + 3y >= 36
        // x, y >= 0

        int numberOfThreads = 10;

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


        ConcurrentMonteCarloSolver solver = new ConcurrentMonteCarloSolver(minFunction, constraints, space);
        solver.setNumberOfProbes(1000);
        solver.setConvergenceRate(2);
        solver.setPrecision(1e-5);

        System.out.println(solver);

        // Run threads solving problem
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < numberOfThreads; ++i) {

            Thread solverThread = new Thread(solver);

            threads.add(solverThread);
            solverThread.start();
        }

        // Wait for all threads to finish
        for(int i = 0; i < numberOfThreads; ++i) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Solutions:");
        System.out.println(solver.getSolutions());
        System.out.println("Best solution:");
        System.out.println(solver.getSolution());
    }

}