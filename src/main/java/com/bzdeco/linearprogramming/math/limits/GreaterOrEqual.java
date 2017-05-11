package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public class GreaterOrEqual extends Inequality {

    public GreaterOrEqual() {
        super();
    }

    public GreaterOrEqual(double limit) {
        super(limit);
    }

    @Override
    public boolean isSatisfiedWith(double value) {
        return value >= limit;
    }
}
