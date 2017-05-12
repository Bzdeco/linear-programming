package com.bzdeco.linearprogramming.util;

import com.bzdeco.linearprogramming.math.Point;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class PairTest {
    @Test
    public void pairWithPointOfHigherValueIsGreater() throws Exception {

        // given:
        Pair<Point, Double> greater = new Pair<>(new Point(1, 1), 10.0);
        Pair<Point, Double> smaller = new Pair<>(new Point(1, 0), 5.0);

        // when:
        int comparison = greater.compareTo(smaller);

        // then:
        assertTrue(comparison > 0);

    }

}