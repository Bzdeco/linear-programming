package com.bzdeco.linearprogramming.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class PolynomialTest {
    @Test
    public void polynomialIsEvaluatedProperlyForGivenVariables() throws Exception {

        // given:
        List<Variable> variables = new ArrayList<>();
        variables.add(new Variable("x", 2, 1));
        variables.add(new Variable("y", -3.5, 2));
        variables.add(new Variable("z", 2.2, 3));
        Polynomial polynomial = new Polynomial(variables);
        Point evaluationPoint = new Point(1, 2, 3);

        // when:
        double evaluation = polynomial.evaluateFor(evaluationPoint);

        // then:
        assertEquals(47.4, evaluation, 1e-1);
    }

}