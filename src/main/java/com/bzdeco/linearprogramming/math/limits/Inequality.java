package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public abstract class Inequality {
    
    protected double limit;
    
    public abstract boolean isSatisfiedWith(double value);
}
