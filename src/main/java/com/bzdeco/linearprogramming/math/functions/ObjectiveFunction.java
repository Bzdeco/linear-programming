package com.bzdeco.linearprogramming.math.functions;

import com.bzdeco.linearprogramming.math.Polynomial;
import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.util.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bzdeco on 09.05.17.
 */
public abstract class ObjectiveFunction {

    protected Polynomial polynomial;

    public ObjectiveFunction(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public double evaluateFor(Point point) {
        return polynomial.evaluateFor(point);
    }

    public Set<Pair<Point, Double>> applyFor(Set<Point> points) {

        Set<Pair<Point, Double>> result = new HashSet<>();

        for(Point point : points)
            result.add(new Pair<>(point, evaluateFor(point)));

        return result;
    }

    public abstract Point getBestPoint(Set<Point> pointsToChooseFrom);

    public Set<Point> getBestPoints(Set<Point> pointsToChooseFrom, int numberOfPoints) {

        Set<Point> result = new HashSet<>();

        for(int i = 0; i < numberOfPoints; i++) {

            Point nextBestPoint = getBestPoint(pointsToChooseFrom);
            pointsToChooseFrom.remove(nextBestPoint);
            result.add(nextBestPoint);
        }

        return result;
    }
}
