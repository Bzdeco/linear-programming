package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.math.Constraint;
import com.bzdeco.linearprogramming.math.Space;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.util.Pair;

import java.util.*;

/**
 * Created by bzdeco on 09.05.17.
 */
public class MonteCarloSolver {

    /**
     * Determines how many times space will be shrunk in each dimension after each iteration
     */
    private int convergenceRate = 2;
    /**
     * Determines size of radius of space which center can be treated as accurate solution
     */
    private double precision = 0.0001;
    private int numberOfProbes = 100;

    protected ObjectiveFunction function;
    protected List<Constraint> constraints;
    protected Space space;

    public MonteCarloSolver(ObjectiveFunction function, List<Constraint> constraints, Space space) {
        this.function = function;
        this.constraints = constraints;
        this.space = space;
    }

    public Pair<Point, Integer> solve() {

        int numberOfIterations = 0;
        while(!isSolutionAccurate()) {
            reduceToSmallerProblem();
            numberOfIterations++;
        }

        return new Pair<>(getSolution(), numberOfIterations);
    }

    public void setConvergenceRate(int convergenceRate) {
        this.convergenceRate = convergenceRate;
    }

    public void setNumberOfProbes(int numberOfProbes) {
        this.numberOfProbes = numberOfProbes;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public void reduceToSmallerProblem() {

        Set<Point> pointsSatisfyingAllConditions = findPointsSatisfyingAllConditions();
        Point chosenBestPoint = function.getBestPoint(pointsSatisfyingAllConditions);

        space = space.getShrunkSpaceAroundPoint(chosenBestPoint, convergenceRate);
    }

    public List<MonteCarloSolver> reduceToSmallerProblems(int numberOfNewProblems) {

        Set<Point> pointsSatisfyingAllConditions = findPointsSatisfyingAllConditions();
        Set<Point> chosenBestPoints = function.getBestPoints(
                pointsSatisfyingAllConditions,
                numberOfNewProblems
        );

        List<MonteCarloSolver> solversForNextIteration = new ArrayList<>();
        for(Point point : chosenBestPoints) {

            Space narrowedDownSpace = space.getShrunkSpaceAroundPoint(point, convergenceRate);
            solversForNextIteration.add(new MonteCarloSolver(
                    this.function,
                    this.constraints,
                    narrowedDownSpace
            ));
        }

        return solversForNextIteration;
    }

    public Point getSolution() {

        return space.getCenter();
    }

    public boolean isSolutionAccurate() {

        return space.getBoundsLargestRadius() <= precision;
    }

    @Override
    public String toString() {

        String solverState = "Objective function:\n" + function + "\n";

        String constraintsText = "";
        for(Constraint constraint : constraints)
            constraintsText += constraint + "\n";

        solverState += "Constraints:\n" + constraintsText + "Bounds:\n" + space;

        return solverState;
    }

    private Set<Point> findPointsSatisfyingAllConditions() {

        Set<Point> randomPoints = space.getRandomPoints(numberOfProbes);
        Set<Point> result = new HashSet<>();

        for(Point point : randomPoints) {

            boolean pointSatisfiesAllConstraints = true;
            for(Constraint constraint : constraints) {
                if(!constraint.isSatisfiedWith(point)) {
                    pointSatisfiesAllConstraints = false;
                    break;
                }
            }

            if(pointSatisfiesAllConstraints)
                result.add(point);
        }

        return result;
    }

}
