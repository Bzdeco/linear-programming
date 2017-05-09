package com.bzdeco.linearprogramming;

import java.util.List;
import java.util.Set;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Solver {

    private static final int NUMBER_OF_PROBES = 1000;

    private ObjectiveFunction function;
    private List<Constraint> constraints;
    private Space space;

    public Solver(ObjectiveFunction function, List<Constraint> constraints, Space space) {
        this.function = function;
        this.constraints = constraints;
        this.space = space;
    }

    private void iteration() {

        Set<Point> randomPoints = space.getRandomPoints(NUMBER_OF_PROBES);

        for(Point point : randomPoints) {
            for(Constraint constraint : constraints) {
                if(constraint.is)
            }
        }
    }
}
