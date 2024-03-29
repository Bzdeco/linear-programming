package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Lower extends Inequality {

    public Lower() {
        super();
    }

    public Lower(double limit) {
        super(limit);
    }

    @Override
    public boolean isSatisfiedWith(double value) {
        return value < limit;
    }

    @Override
    public Inequality copy() {
        return new Lower();
    }

    @Override
    public String toString() {

        return "< " + limit;
    }
}
