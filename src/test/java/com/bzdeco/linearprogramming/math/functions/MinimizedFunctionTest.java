package com.bzdeco.linearprogramming.math.functions;

import com.bzdeco.linearprogramming.math.Point;
import com.bzdeco.linearprogramming.math.Polynomial;
import com.bzdeco.linearprogramming.math.Variable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class MinimizedFunctionTest {

    // TODO mock part for which Polynomial is responsible
    @Test
    public void getBestPointReturnsPointOfLowestValue() throws Exception {

        // given:
        List<Variable> variables = new ArrayList<>();
        variables.add(new Variable("x", 2, 1));
        variables.add(new Variable("y", 3, 1));
        variables.add(new Variable("z", 4, 1));
        Polynomial polynomial = new Polynomial(variables);
        ObjectiveFunction minFunction = new MinimizedFunction(polynomial);
        Point bestPoint = new Point(0, 0, 0);
        Set<Point> points = new HashSet<>();
        points.add(new Point(1, 2, 3));
        points.add(bestPoint);
        points.add(new Point(1, 1, 0));

        // when:
        Point foundBestPoint = minFunction.getBestPoint(points);

        // then:
        assertEquals(foundBestPoint, bestPoint);

    }
}