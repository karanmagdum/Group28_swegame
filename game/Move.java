package game;

import java.util.ArrayList;

public class Move {
    public static void getMoves(String type, Point[] points, int roll1, int roll2, boolean forward){
        //find points of given value of "type" parameter
        ArrayList<Integer> validPointPositions = new ArrayList<>();
        for(int i=1;i<points.length;i++){
            if(points[i].getType().equals(type)){
                validPointPositions.add(i);
            }
        }

        for(int i=0;i<validPointPositions.size();i++){
            for(int j=0;j<validPointPositions.size();j++){
                    if(isValidMove(points, validPointPositions.get(i), roll1, validPointPositions.get(j), roll2, forward)){
                        if(forward) {
                            //for first player
                            if (validPointPositions.get(i) == validPointPositions.get(j)) {
                                // same point could be played with dice values
                                System.out.println("Point " + validPointPositions.get(i) + " -> Point " + (validPointPositions.get(i) + roll1 + roll2));
                            } else {
                                // Point1,Point2 will be played with roll1 and roll2 respectively
                                System.out.println("Point " + validPointPositions.get(i) + " -> Point " + (validPointPositions.get(i) + roll1) +
                                        " and Point " + validPointPositions.get(j) + " -> Point " + (validPointPositions.get(j) + roll2));
                            }
                        }
                        else{
                            //for second player
                            if(validPointPositions.get(i) == validPointPositions.get(j)){
                                // same point could be played with dice values
                                System.out.println("Point "+validPointPositions.get(i) +" -> Point "+ (validPointPositions.get(i)-roll1-roll2));
                            }
                            else {
                                // Point1,Point2 will be played with roll1 and roll2 respectively
                                System.out.println("Point " + validPointPositions.get(i) + " -> Point " + (validPointPositions.get(i) - roll1) +
                                        " and Point " + validPointPositions.get(j) + " -> Point " + (validPointPositions.get(j) - roll2));
                            }
                        }
                    }
            }
        }
    }

    public static boolean isValidMove(Point[] points, int firstPoint, int roll1, int secondPoint, int roll2, boolean forward){
        if(forward){
            //play points forward
            if(firstPoint == secondPoint){
                //same points moves two times
                if(firstPoint+roll1+roll2 <=24){
                    if (points[firstPoint+roll1+roll2].isEmpty())
                        return true;
                    else if (points[firstPoint].getType().equals(points[firstPoint+roll1+roll2].getType()))
                        return true;
                    else{
                        //checkers to move in different type of points
                        return points[firstPoint + roll1 + roll2].getSize() == 1;
                    }
                }
                else
                    return false;
            }
            else{
                // movement of two points
                boolean firstMoveValid = false;
                if(firstPoint+roll1 <=24){
                    if (points[firstPoint+roll1].isEmpty())
                        firstMoveValid =  true;
                    else if (points[firstPoint].getType().equals(points[firstPoint+roll1].getType()))
                        firstMoveValid = true;
                    else{
                        //checkers to move in different type of points
                        firstMoveValid = points[firstPoint + roll1].getSize() == 1;
                    }
                }

                boolean secondMoveValid = false;
                if(secondPoint+roll2 <=24){
                    if (points[secondPoint+roll2].isEmpty())
                        secondMoveValid =  true;
                    else if (points[secondPoint].getType().equals(points[secondPoint+roll2].getType()))
                        secondMoveValid = true;
                    else{
                        //checkers to move in different type of points
                        secondMoveValid = points[secondPoint + roll2].getSize() == 1;
                    }
                }

                return firstMoveValid && secondMoveValid;
            }
        }
        else{
            //play points backward
            if(firstPoint == secondPoint){
                //same points moves two times
                if(firstPoint-roll1-roll2 >= 1){
                    if (points[firstPoint-roll1-roll2].isEmpty())
                        return true;
                    else if (points[firstPoint].getType().equals(points[firstPoint-roll1-roll2].getType()))
                        return true;
                    else{
                        //checkers to move in different type of points
                        return points[firstPoint - roll1 - roll2].getSize() == 1;
                    }
                }
                else
                    return false;
            }
            else{
                // movement of two points
                boolean firstMoveValid = false;
                if(firstPoint-roll1 >=1){
                    if (points[firstPoint-roll1].isEmpty())
                        firstMoveValid =  true;
                    else if (points[firstPoint].getType().equals(points[firstPoint-roll1].getType()))
                        firstMoveValid = true;
                    else{
                        //checkers to move in different type of points
                        firstMoveValid = points[firstPoint - roll1].getSize() == 1;
                    }
                }

                boolean secondMoveValid = false;
                if(secondPoint-roll2 >= 1){
                    if (points[secondPoint-roll2].isEmpty())
                        secondMoveValid =  true;
                    else if (points[secondPoint].getType().equals(points[secondPoint-roll2].getType()))
                        secondMoveValid = true;
                    else{
                        //checkers to move in different type of points
                        secondMoveValid = points[firstPoint - roll2].getSize() == 1;
                    }
                }
                return firstMoveValid && secondMoveValid;
            }
        }
    }
}
