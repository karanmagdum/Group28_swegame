package test;

import game.Board;
import game.Point;
import game.Triangle.Checker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testAddChecker() {
        Board board = new Board();
        Point point = new Point();

        board.addChecker(point, 3, Checker.X);

        assertFalse(point.isEmpty());
        assertEquals(3, point.getSize());
        assertTrue(point.equals(Checker.X));
    }

    @Test
    public void testAssignCheckers() {
        Board board = new Board();
        Point[] points = new Point[26];
        for (int i = 0; i < 26; i++) {
            points[i] = new Point();
        }

        board.assignCheckers(points);

        // Check if checkers are assigned correctly to the specified points
        assertEquals(2, points[1].getSize());
        assertEquals(5, points[12].getSize());
        assertEquals(3, points[17].getSize());
        assertEquals(5, points[19].getSize());

        assertEquals(3, points[8].getSize());
        assertEquals(5, points[6].getSize());
        assertEquals(5, points[13].getSize());
        assertEquals(2, points[24].getSize());
    }

    @Test
    public void testRollDice() {
        Board board = new Board();

        // Roll the dice 1000 times and check if the result is within the valid range [1, 6]
        for (int i = 0; i < 1000; i++) {
            int result = Board.rollDice();
            assertTrue(result >= 1 && result <= 6);
        }
    }
}

