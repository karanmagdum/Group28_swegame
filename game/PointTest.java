package game;

import game.Triangle.Checker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    public void testAdd() {
        Point point = new Point();

        assertTrue(point.isEmpty());

        point.add(Checker.X);

        assertFalse(point.isEmpty());
        assertEquals(1, point.getSize());
        assertTrue(point.equals(Checker.X));
    }

    @Test
    public void testAddWithException() {
        Point point = new Point();

        assertTrue(point.isEmpty());

        point.add(Checker.X);

        assertFalse(point.isEmpty());
        assertEquals(1, point.getSize());
        assertTrue(point.equals(Checker.X));

        // Adding a different checker should throw an exception
        assertThrows(RuntimeException.class, () -> point.add(Checker.O));
    }

    @Test
    public void testEqualsObject() {
        Point point1 = new Point();
        Point point2 = new Point();

        assertFalse(point1.equals(point2));

        point1.add(Checker.X);

        assertFalse(point1.equals(point2));

        // Adding a same checker should make the points equal
        point2.add(Checker.X);
        assertTrue(point1.equals(point2));
    }
}

