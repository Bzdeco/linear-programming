package com.bzdeco.linearprogramming.concurrency;

import com.bzdeco.linearprogramming.MonteCarloSolver;
import com.bzdeco.linearprogramming.math.Constraint;
import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.math.Space;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.util.Pair;

import java.util.*;

/**
 * Created by bzdeco on 14.05.17.
 */
public class ConcurrentMonteCarloSolver extends MonteCarloSolver implements Runnable {

    private final Set<Point> solutions;

    public ConcurrentMonteCarloSolver(ObjectiveFunction function, List<Constraint> constraints, Space space) {
        super(function, constraints, space);
        solutions = new HashSet<>();
    }

    @Override
    public void run() {

        MonteCarloSolver threadLocalSolver = new MonteCarloSolver(function, constraints, space);

        Pair<Point, Integer> solution = threadLocalSolver.solve();

        synchronized (solutions) {
            solutions.add(solution.getKey());
        }
    }

    @Override
    public Point getSolution() {
        return function.getBestPoint(solutions);
    }

    public Set<Point> getSolutions() {

        return solutions;
    }
}
