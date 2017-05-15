package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.concurrency.ConcurrentMonteCarloSolver;
import com.bzdeco.linearprogramming.math.Constraint;
import com.bzdeco.linearprogramming.math.Space;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.util.UserInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bzdeco on 14.05.17.
 */
public class ApplicationConcurrent {

    private static UserInput userInput = new UserInput();
    private static List<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {

        // Ask for problem parameters
        int numberOfThreads = userInput.askForNumberOfThreads();
        int numberOfVariables = userInput.askForNumberOfVariables();
        int numberOfConstraints = userInput.askForNumberOfConstraints();
        List<String> variablesNames = userInput.askForVariablesNames(numberOfVariables);
        ObjectiveFunction objectiveFunction = userInput.askForObjectiveFunction(variablesNames);
        List<Constraint> constraints = userInput.askForConstraints(numberOfConstraints, variablesNames);
        Space space = userInput.askForSpace(variablesNames);

        // Setup concurrent-ready Monte Carlo solver
        ConcurrentMonteCarloSolver solver = new ConcurrentMonteCarloSolver(objectiveFunction, constraints, space);
        solver.setNumberOfProbes(1000);
        solver.setConvergenceRate(2);
        solver.setPrecision(1e-5);

        // Display entered input
        System.out.println(solver);

        // Run threads solving problem
        for(int i = 0; i < numberOfThreads; ++i) {

            Thread solverThread = new Thread(solver);
            solverThread.setName("Thread" + i);

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

        // Display result
        System.out.println("Solution:");
        System.out.println(solver.getSolution());
    }
}
