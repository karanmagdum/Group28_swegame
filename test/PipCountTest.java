package test;

import game.Point;
import game.PipCount;
import game.Triangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PipCountTest {

    @Test
    void testCountIndividualPip() {
        // Create a test scenario where it's player X's turn
        Point[] pointsX = createTestPointsForPlayerX();
        int[] pipCountX = PipCount.countIndividualPip(pointsX, true);
        for(int i=1;i<=24;i++)
            System.out.print(pipCountX[i]+" ");
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 10, 0, 0, 0, 0, 15, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0}, pipCountX);
        System.out.println();
        // Create a test scenario where it's player O's turn
        Point[] pointsO = createTestPointsForPlayerO();
        int[] pipCountO = PipCount.countIndividualPip(pointsO, false);
        for(int i=1;i<=24;i++)
            System.out.print(pipCountO[i]+" ");
        assertArrayEquals(new int[]{0, 24, 23, 22, 21, 0, 19, 18, 17, 16, 0, 14, 13, 12, 11, 0, 9, 8, 7, 6, 0, 4, 3, 2, 1, 0}, pipCountO);
    }

    @Test
    void testCountTotalPip() {
        // Create a test scenario
        Point[] points = createTestPointsForTotalPip();
        PipCount pipCounter = new PipCount();
        int[] totalPip = pipCounter.countTotalPip(points);
        assertArrayEquals(new int[]{50, 250}, totalPip);
    }

    private Point[] createTestPointsForPlayerX() {
        Point[] points = new Point[26];
        points[0] = new Point();
        points[25] = new Point();
        for (int i = 1; i <= 24; i++) {
            points[i] = new Point();
            if (i % 5 == 0) {
                points[i].add(Triangle.Checker.X);
            }
        }
        return points;
    }

    private Point[] createTestPointsForPlayerO() {
        Point[] points = new Point[26];
        points[0] = new Point();
        points[25] = new Point();
        for (int i = 1; i <= 24; i++) {
            points[i] = new Point();
            if (i % 5 != 0) {
                points[i].add(Triangle.Checker.O);
            }
        }
        return points;
    }

    private Point[] createTestPointsForTotalPip() {
        Point[] points = new Point[26];
        for (int i = 1; i <= 24; i++) {
            points[i] = new Point();
            if (i % 5 == 0) {
                points[i].add(Triangle.Checker.X);
            } else {
                points[i].add(Triangle.Checker.O);
            }
        }
        return points;
    }
}

