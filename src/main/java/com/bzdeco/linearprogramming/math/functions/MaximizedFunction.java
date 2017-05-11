package com.bzdeco.linearprogramming.math.functions;

import com.bzdeco.linearprogramming.math.Polynomial;
import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.util.Pair;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bzdeco on 10.05.17.
 */
public class MaximizedFunction extends ObjectiveFunction {

    public MaximizedFunction() {
        super();
    }

    public MaximizedFunction(Polynomial polynomial) {
        super(polynomial);
    }

    @Override
    public Point getBestPoint(Set<Point> pointsToChooseFrom) {

        Set<Pair<Point, Double>> evaluatedPoints = applyFor(pointsToChooseFrom);
        TreeSet<Pair<Point, Double>> sortedEvaluatedPoints = new TreeSet<>(evaluatedPoints);

        return sortedEvaluatedPoints.last().getKey();
    }
}
