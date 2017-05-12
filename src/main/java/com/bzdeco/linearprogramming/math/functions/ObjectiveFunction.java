package com.bzdeco.linearprogramming.math.functions;

import com.bzdeco.linearprogramming.math.Polynomial;
import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bzdeco on 09.05.17.
 */
public abstract class ObjectiveFunction {

    protected Polynomial polynomial;

    /**
     * Constructs objective function with empty polynomial (equal to 0)
     */
    public ObjectiveFunction() {
        this(new Polynomial(new ArrayList<>()));
    }

    public ObjectiveFunction(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public abstract Point getBestPoint(Set<Point> pointsToChooseFrom);

    public void setPolynomial(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public Set<Point> getBestPoints(Set<Point> pointsToChooseFrom, int numberOfPoints) {

        Set<Point> result = new HashSet<>();

        for(int i = 0; i < numberOfPoints; i++) {

            Point nextBestPoint = getBestPoint(pointsToChooseFrom);
            pointsToChooseFrom.remove(nextBestPoint);
            result.add(nextBestPoint);
        }

        return result;
    }

    protected Set<Pair<Point, Double>> applyFor(Set<Point> points) {

        Set<Pair<Point, Double>> result = new HashSet<>();

        for(Point point : points)
            result.add(new Pair<>(point, polynomial.evaluateFor(point)));

        return result;
    }
}
