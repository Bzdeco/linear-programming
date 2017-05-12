package com.bzdeco.linearprogramming.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Point {

    private List<Double> coordinates;

    public Point(double ... coords) {

        coordinates = new ArrayList<>();
        for(double coord : coords) {
            coordinates.add(coord);
        }
    }

    public Point(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return coordinates.equals(point.coordinates);
    }

    @Override
    public int hashCode() {
        return coordinates.hashCode();
    }

    @Override
    public String toString() {

        String point = "(";
        for(int i = 0; i < coordinates.size(); i++) {

            point += coordinates.get(i);
            if(i != coordinates.size() - 1)
                point += "; ";
            else
                point += ")";
        }

        return point;

    }
}
