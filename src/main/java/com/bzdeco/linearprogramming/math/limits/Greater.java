package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Greater extends Inequality {

    public Greater() {
        super();
    }

    public Greater(double limit) {
        super(limit);
    }

    @Override
    public boolean isSatisfiedWith(double value) {
        return value > limit;
    }

    @Override
    public String toString() {

        return "> " + limit;
    }
}
