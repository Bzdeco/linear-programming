package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.math.Constraint;
import com.bzdeco.linearprogramming.math.Space;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.math.Point;

import java.util.*;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Solver {

    /**
     * Determines how many times space will be shrunk in each dimension after each iteration
     */
    private int convergenceRate = 2;
    private int numberOfProbes = 1000;

    private ObjectiveFunction function;
    private List<Constraint> constraints;
    private Space space;

    public Solver(ObjectiveFunction function, List<Constraint> constraints, Space space) {
        this.function = function;
        this.constraints = constraints;
        this.space = space;
    }

    public void setConvergenceRate(int convergenceRate) {
        this.convergenceRate = convergenceRate;
    }

    public void setNumberOfProbes(int numberOfProbes) {
        this.numberOfProbes = numberOfProbes;
    }

    public List<Solver> getShrunkProblems(int numberOfNewProblems) {

        Set<Point> pointsSatisfyingAllConditions = findPointsSatisfyingAllConditions();
        Set<Point> chosenBestPoints = function.getBestPoints(
                pointsSatisfyingAllConditions,
                numberOfNewProblems
        );

        List<Solver> solversForNextIteration = new ArrayList<>();
        for(Point point : chosenBestPoints) {

            Space narrowedDownSpace = space.getShrunkSpaceAroundPoint(point, convergenceRate);
            solversForNextIteration.add(new Solver(
                    this.function,
                    this.constraints,
                    narrowedDownSpace
            ));
        }

        return solversForNextIteration;
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
