package game;

import game.Triangle.Checker;


/**
 * This class is responsible for calculating the pip count in a game of Backgammon.
 * Pip count is a measure of how far a player's checkers are from being borne off.
 */
public class PipCount {

    /**
     * Calculates the individual pip count for each point on the board for the current player.
     * 
     * @param points An array representing the points on the Backgammon board.
     * @param turn A boolean indicating the current player's turn (true for Player X, false for Player O).
     * @return An array of integers representing the pip count for each point on the board.
     */

    public static int[] countIndividualPip(Point[] points, boolean turn){
        int[] pipCount = new int[26];
        for (int i = 1; i <= 24; i++) {
                if (turn && points[i].equals(Checker.X)) {
                    pipCount[i] = (points[i].getSize() * i);
                } else if(!turn && points[i].equals(Checker.O)){
                    pipCount[i] = (points[i].getSize() * (25 - i));
                }
        }
        return pipCount;
    }

    /**
     * Calculates the total pip count for both players.
     * 
     * @param points An array representing the points on the Backgammon board.
     * @return An array of two integers where the first element is the total pip count for Player X 
     *         and the second element is the total pip count for Player O.
     */

    public int[] countTotalPip(Point[] points) {
        int x_pip = 0, o_pip = 0;
        for (int i = 1; i <= 24; i++) {
            if (points[i].equals(Checker.X)) {
                x_pip += (points[i].getSize() * i);
            } else {
                o_pip += (points[i].getSize() * (25 - i));
            }
        }
        return new int[]{x_pip, o_pip};
    }
}

