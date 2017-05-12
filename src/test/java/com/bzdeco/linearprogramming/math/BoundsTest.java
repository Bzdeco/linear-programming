package com.bzdeco.linearprogramming.math;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class BoundsTest {
    @Test
    public void getProperBoundsRadius() throws Exception {

        // given:
        Bounds bounds = new Bounds(20.5, 101.4);

        // when:
        double boundsRadius = bounds.getBoundsRadius();

        // then:
        assertEquals(40.45, boundsRadius, 1e-2);
    }

    @Test
    public void getProperBoundsCenter() throws Exception {

        // given:
        Bounds bounds = new Bounds(20.5, 101.4);

        // when:
        double boundsCenter = bounds.getCenter();

        // then:
        assertEquals(60.95, boundsCenter, 1e-2);

    }

}