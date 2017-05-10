package com.bzdeco.linearprogramming.math.limits;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Lower extends Inequality {

    @Override
    public boolean isSatisfiedWith(double value) {
        return value < limit;
    }
}
