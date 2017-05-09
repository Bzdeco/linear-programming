package com.bzdeco.linearprogramming;

import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Point {
    private List<Double> coordinates;

    public Point(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }
}
