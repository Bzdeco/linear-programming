package com.bzdeco.linearprogramming;

import java.util.List;

/**
 * Created by bzdeco on 09.05.17.
 */
public class Constraint {

    private Polynomial polynomial;
    private Sign sign;
    private double limit;

    public Constraint(Polynomial polynomial, Sign sign, double limit) {
        this.polynomial = polynomial;
        this.sign = sign;
        this.limit = limit;
    }
}
