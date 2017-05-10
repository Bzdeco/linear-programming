package com.bzdeco.linearprogramming.math;

import com.bzdeco.linearprogramming.math.limits.Inequality;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Constraint {

    private Polynomial polynomial;
    private Inequality condition;

    public Constraint(Polynomial polynomial, Inequality condition) {
        this.polynomial = polynomial;
        this.condition = condition;
    }

    public boolean isSatisfiedWith(Point point) {

        double polynomialValue = polynomial.evaluateFor(point);

        return condition.isSatisfiedWith(polynomialValue);
    }
}
