package com.bzdeco.linearprogramming.math;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class VariableTest {
    @Test
    public void variableIsEvaluatedProperlyForGivenValue() throws Exception {

        // given:
        Variable variable = new Variable("x", -2.3, 3);

        // when:
        double evaluation = variable.evaluateFor(3.14);

        // then:
        assertEquals(-71.2060312, evaluation, 1e-7);
    }

}