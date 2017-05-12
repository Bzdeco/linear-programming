package com.bzdeco.linearprogramming.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by bzdeco on 12.05.17.
 */
public class SpaceTest {
    @Test
    public void getRandomPointsGeneratesProperPoints() throws Exception {

        // given:
        int numberOfGeneratedPoints = 1000;
        List<Bounds> bounds = new ArrayList<>();
        bounds.add(new Bounds(0, 100));
        bounds.add(new Bounds(20, 70));
        bounds.add(new Bounds(0, 50));
        Space space = new Space(bounds);

        // when:
        Set<Point> generatedPoints = space.getRandomPoints(numberOfGeneratedPoints);

        // then:
        assertTrue(generatedPoints.size() <= numberOfGeneratedPoints);
        for(Point point : generatedPoints) {
            double x = point.getCoordinates().get(0);
            double y = point.getCoordinates().get(1);
            double z = point.getCoordinates().get(2);
            assertTrue(0.0 <= x && x <= 100.0);
            assertTrue(20.0 <= y && y <= 70.0);
            assertTrue(0.0 <= z && z <= 50);
        }
    }

    @Test
    public void shrunkSpaceAroundPointReturnsProperSpace() throws Exception {

        // given:
        List<Bounds> bounds = new ArrayList<>();
        bounds.add(new Bounds(0, 100));
        bounds.add(new Bounds(20, 70));
        bounds.add(new Bounds(0, 50));
        Space space = new Space(bounds);
        Point centerOfShrunkSpace = new Point(40, 35, 40);
        int convergenceRate = 5; // will result in new space radius = 10

        // when:
        Space shrunkSpace = space.getShrunkSpaceAroundPoint(centerOfShrunkSpace, convergenceRate);

        // then:
        List<Bounds> shrunkSpaceBounds = shrunkSpace.getBounds();
        Bounds xBounds = new Bounds(30, 50);
        Bounds yBounds = new Bounds(25, 45);
        Bounds zBounds = new Bounds(30, 50);

        assertEquals(10, shrunkSpace.getBoundsLargestRadius(), 1e0);
        assertEquals(xBounds, shrunkSpace.getBounds().get(0));
        assertEquals(yBounds, shrunkSpace.getBounds().get(1));
        assertEquals(zBounds, shrunkSpace.getBounds().get(2));

    }

    @Test
    public void shrunkSpaceAroundPointNearToBoundReturnsProperlyTrimmedSpace() throws Exception {

        // given:
        List<Bounds> bounds = new ArrayList<>();
        bounds.add(new Bounds(0, 100));
        bounds.add(new Bounds(20, 70));
        bounds.add(new Bounds(0, 50));
        Space space = new Space(bounds);
        Point centerOfShrunkSpace = new Point(5, 60, 45);
        int convergenceRate = 5; // will result in new space radius = 10

        // when:
        Space shrunkSpace = space.getShrunkSpaceAroundPoint(centerOfShrunkSpace, convergenceRate);

        // then:
        List<Bounds> shrunkSpaceBounds = shrunkSpace.getBounds();
        Bounds xBounds = new Bounds(0, 15);
        Bounds yBounds = new Bounds(50, 70);
        Bounds zBounds = new Bounds(35, 50);

        assertEquals(10, shrunkSpace.getBoundsLargestRadius(), 1e0);
        assertEquals(xBounds, shrunkSpace.getBounds().get(0));
        assertEquals(yBounds, shrunkSpace.getBounds().get(1));
        assertEquals(zBounds, shrunkSpace.getBounds().get(2));
    }

    @Test
    public void getBoundsLargestRadiusReturnsProperValue() throws Exception {

        // given:
        List<Bounds> bounds = new ArrayList<>();
        bounds.add(new Bounds(0, 100));
        bounds.add(new Bounds(20, 70));
        bounds.add(new Bounds(0, 50));
        Space space = new Space(bounds);

        // when:
        double largestBoundsRadius = space.getBoundsLargestRadius();

        // then:
        assertEquals(50, largestBoundsRadius, 1e0);
    }

}