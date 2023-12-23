package game;

import game.Triangle.Checker;

public class PipCount {

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

