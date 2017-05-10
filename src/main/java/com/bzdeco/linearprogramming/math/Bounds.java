package com.bzdeco.linearprogramming.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Bounds {

    private double lowerBound;
    private double upperBound;

    public Bounds(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getBoundsRadius() {

        return (upperBound - lowerBound) / 2.0;
    }
}
