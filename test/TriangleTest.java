package test;

import game.Point;
import game.Triangle;
import game.Triangle.Checker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void testGetSize() {
        Triangle triangle = new Point();
        assertEquals(0, triangle.getSize());

        triangle.add(Checker.X);
        assertEquals(1, triangle.getSize());

        triangle.add(Checker.X);
        assertEquals(2, triangle.getSize());
    }

    @Test
    public void testIsEmpty() {
        Triangle triangle = new Point();
        assertTrue(triangle.isEmpty());
        triangle.add(Checker.X);
        assertFalse(triangle.isEmpty());
    }

    @Test
    public void testEquals() {
        Triangle triangle = new Point();
        assertFalse(triangle.equals(Checker.X));
        triangle.add(Checker.X);
        assertTrue(triangle.equals(Checker.X));
        assertFalse(triangle.equals(Checker.O));
    }

    @Test
    public void testEqualsObject() {
        Triangle triangle1 = new Point();
        Triangle triangle2 = new Point();
        assertFalse(triangle1.equals(triangle2));
        triangle1.add(Checker.X);
        triangle2.add(Checker.X);
        assertTrue(triangle1.equals(triangle2));
    }

    @Test
    public void testAdd() {
        Triangle triangle = new Point();
        assertTrue(triangle.isEmpty());
        triangle.add(Checker.X);
        assertFalse(triangle.isEmpty());
        assertEquals(1, triangle.getSize());
        assertTrue(triangle.equals(Checker.X));
    }
}
