package test;

import game.Move;
import game.Point;
import game.Triangle.Checker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Move move;
    private Point[] points;

    @BeforeEach
    void setUp() {
        move = new Move();
        points = new Point[26];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }
    }

    @Test
    void testIsBearOff() {

        for (int i=19;i<=24;i++)
            points[i].add(Checker.X);
        // Assuming points 19 to 24 are valid for player 1
        move.validPointPositions = new ArrayList<>(Arrays.asList(19, 20, 21, 22, 23, 24));
        assertTrue(move.isBearOff(Checker.X));
        for (int i=1;i<=24;i++)
            points[i].getList().clear();
        for (int i=1;i<=6;i++)
            points[i].add(Checker.O);
        // Assuming points 1 to 6 are valid for player 2
        move.validPointPositions = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertTrue(move.isBearOff(Checker.O));

        points[20].add(Checker.O);
        points[21].add(Checker.O);
        // Assuming points 1 to 6 and 19 to 24 are valid for player 1 and player 2
        move.validPointPositions = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 20, 21));
        assertFalse(move.isBearOff(Checker.O));
    }

    @Test
    public void testGetMoves() {
        // populate points
        points[0].add(Checker.O);
        points[5].add(Checker.O);

        move.map = move.getMoves(Checker.O, points, 4, 2);
        // validate moves
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(0, 4)));
        expected.add(new ArrayList<>(Arrays.asList(5, 7)));

        assertEquals(expected, move.map.get(2));
    }

    @Test
    public void testGetMovesSameDiceRoll() {
        // populate points
        points[1].add(Checker.X);
        points[5].add(Checker.X);
        points[7].add(Checker.X);

        HashMap<Integer, ArrayList<ArrayList<Integer>>> map = move.getMoves(Checker.X, points, 4, 4);
        // validate moves
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, 5)));
        expected.add(new ArrayList<>(Arrays.asList(5, 13)));

        assertEquals(expected, map.get(2));
    }

    @Test
    public void testGetMovesSameDiceRollBearOff() {
        // populate points
        points[1].add(Checker.O);
        points[4].add(Checker.O);

        HashMap<Integer, ArrayList<ArrayList<Integer>>> map = move.getMoves(Checker.O, points, -4, -4);
        // validate moves
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, -1)));
        expected.add(new ArrayList<>(Arrays.asList(4, -1)));

        assertEquals(expected, map.get(2));
    }

    @Test
    public void testGetMovesSameDiceRollCheckerX() {
        // populate points
        points[1].add(Checker.X);
        points[4].add(Checker.X);

        HashMap<Integer, ArrayList<ArrayList<Integer>>> map = move.getMoves(Checker.X, points, 4, 4);
        // validate moves
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, 5)));
        expected.add(new ArrayList<>(Arrays.asList(4, 12)));

        assertEquals(expected, map.get(2));
    }

    @Test
    public void testMakeMove() {
        points[1].add(Checker.O);
        points[5].add(Checker.X);

        move.makeMove(points, 1, 5, Checker.O);

        assertEquals(1, points[5].getSize());
        assertEquals(Checker.X, points[0].getList().peek());
    }

    @Test
    public void testGenerateSameDiceRollMoves() {
        points[5].add(Checker.X);
        points[7].add(Checker.X);
        points[15].add(Checker.X);

        move.validPointPositions.addAll(new ArrayList<>(Arrays.asList(5,7,15)));

        move.generateSameDiceRollMoves(points, 3, false);

        ArrayList<ArrayList<Integer>> expected1 = new ArrayList<>();
        expected1.add(new ArrayList<>(Arrays.asList(5, 8)));
        expected1.add(new ArrayList<>(Arrays.asList(7, 10)));
        assertEquals(expected1, move.map.get(2));
    }

    @Test
    public void testAreBarsClearable() {
        points[5].add(Checker.O);

        points[0].add(Checker.O);
        assertFalse(move.areBarsClearable(0));
        points[25].add(Checker.X);
        assertFalse(move.areBarsClearable(25));
    }

    @Test
    public void testIsValidMove() {
        points[1].add(Checker.X);
        points[5].add(Checker.O);
        points[7].add(Checker.O);

        assertTrue(move.isValidMove(points, 5, 3));
        assertTrue(move.isValidMove(points, 7, 5));
    }
    @Test
    public void testIsValidMoveInvalid() {
        // occupying end point
        points[5].add(Checker.X);
        assertTrue(move.isValidMove(points, 0, 5));
        // trying to move opponent piece
        points[3].add(Checker.X);
        assertTrue(move.isValidMove(points, 3, 6));
    }

    @Test
    public void testIsBearOffNoPieces() {
        assertTrue(move.isBearOff(Checker.O));
    }

    @Test
    public void testGetMovesNoValidPositions() {
        Map<Integer, ArrayList<ArrayList<Integer>>> moves =
                move.getMoves(Checker.O, points, 1, 1);
        assertTrue(moves.isEmpty());
    }

    @Test
    public void testMakeMoveHitOpponent() {
        points[23].add(Checker.O);
        points[24].add(Checker.X);
        move.makeMove(points, 23, 24, Checker.O);
        assertEquals(1, points[0].getSize());
        assertEquals(0, points[25].getSize());
        assertFalse(points[24].isEmpty());
    }

    @Test
    public void testGetBearOffMovesOutOfRange() {
        for (int i = 1; i < 6; i++) {
            points[i].add(Checker.O);
            move.validPointPositions.add(i);
        }
        assertTrue(move.isBearOff(Checker.O));
        move.getBearOffMoves(points, -5, -3);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, -1)));
        assertEquals(expected, move.map.get(1));
    }

    @Test
    public void testGetMoveForOffMovesCase() {
        for (int i = 1; i < 6; i++) {
            points[i].add(Checker.O);
            move.validPointPositions.add(i);
        }
        assertTrue(move.isBearOff(Checker.O));
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, -1)));
        assertEquals(expected, move.getMoves(Checker.O, points,-2, -6).get(1));
    }

}
