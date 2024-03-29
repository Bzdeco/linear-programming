package com.bzdeco.linearprogramming.math;

import java.util.*;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Space {

    private List<Bounds> bounds;
    private Point center;

    public Space(List<Bounds> bounds) {
        this.bounds = bounds;
        this.center = getInitialCenterPoint();
    }

    public Space(List<Bounds> bounds, Point center) {
        this(bounds);
        this.center = center;
    }

    public List<Bounds> getBounds() {
        return bounds;
    }

    public Point getCenter() {
        return center;
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

    public Space getShrunkSpaceAroundPoint(Point point, int convergenceRate) {

        List<Double> coordinates = point.getCoordinates();
        double boundsLargestRadius = getBoundsLargestRadius();

        List<Bounds> shrunkBounds = new ArrayList<>();

        int i = 0;
        for(Bounds dimensionBounds : bounds) {

            double newMaximumRadius = boundsLargestRadius / convergenceRate;
            double newSpaceCenterCoordinate = coordinates.get(i);
            i++;

            double newLowerBound;
            double desiredNewLowerBound = newSpaceCenterCoordinate - newMaximumRadius;
            if(desiredNewLowerBound > dimensionBounds.getLowerBound())
                newLowerBound = desiredNewLowerBound;
            else
                newLowerBound = dimensionBounds.getLowerBound();

            double newUpperBound;
            double desiredNewUpperBound = newSpaceCenterCoordinate + newMaximumRadius;
            if(desiredNewUpperBound < dimensionBounds.getUpperBound())
                newUpperBound = desiredNewUpperBound;
            else
                newUpperBound = dimensionBounds.getUpperBound();

            shrunkBounds.add(new Bounds(newLowerBound, newUpperBound));
        }

        return new Space(shrunkBounds, point);
    }

    public double getBoundsLargestRadius() {

        double maxRadius = 0;
        for(Bounds dimensionBounds : bounds) {
            double boundsRadius = dimensionBounds.getBoundsRadius();
            if(boundsRadius > maxRadius)
                maxRadius = boundsRadius;
        }

        return maxRadius;
    }

    @Override
    public String toString() {

        String space = "";
        for(Bounds dimensionBounds : bounds)
            space += dimensionBounds.toString() + "\n";

        return space;
    }

    private double getRandomDoubleWithinRange(double min, double max) {

        return new Random().nextDouble() * (max - min) + min;
    }

    private Point getInitialCenterPoint() {

        List<Double> coordinates = new ArrayList<>();
        for(Bounds dimensionBounds : bounds) {
            coordinates.add(dimensionBounds.getCenter());
        }

        return new Point(coordinates);
    }
}
