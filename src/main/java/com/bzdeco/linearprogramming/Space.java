package com.bzdeco.linearprogramming;

import java.util.*;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Space {

    private List<Bounds> bounds;
    private int dimension;

    public Space(List<Bounds> bounds) {
        this.bounds = bounds;
        dimension = bounds.size();
    }

    public Set<Point> getRandomPoints(int numberOfPointsToGenerate) {

        Set<Point> generatedPoints = new HashSet<>();

        for(int i = 0; i < numberOfPointsToGenerate; i++) {

            List<Double> pointCoordinates = new ArrayList<>();

            for(Bounds bound : bounds) {
                double randomCoordinate = getRandomDoubleWithinRange(bound.getLowerBound(), bound.getUpperBound());
                pointCoordinates.add(randomCoordinate);
            }

            generatedPoints.add(new Point(pointCoordinates));
        }

        return generatedPoints;
    }

    private double getRandomDoubleWithinRange(double min, double max) {

        return new Random().nextDouble() * (max - min + 1) + min;
    }
}
