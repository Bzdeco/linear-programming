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

    public double getCenter() {
        return lowerBound + getBoundsRadius();
    }

    @Override
    public String toString() {

        return "[" + lowerBound + "; " + upperBound + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bounds bounds = (Bounds) o;

        if (Double.compare(bounds.lowerBound, lowerBound) != 0) return false;
        return Double.compare(bounds.upperBound, upperBound) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lowerBound);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(upperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
