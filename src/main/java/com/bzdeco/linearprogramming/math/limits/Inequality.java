package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public abstract class Inequality {
    
    protected double limit;

    public Inequality() {
        this(0);
    }

    public Inequality(double limit) {
        this.limit = limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public abstract boolean isSatisfiedWith(double value);

    public abstract Inequality copy();
}
