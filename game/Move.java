package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Move {

    private static ArrayList<Integer> validPointPositions = new ArrayList<>();
    private static HashMap<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();

    public static boolean isBearOff(){
        boolean bearOffPlayer1 = true, bearOffPlayer2 = true;
        for(int i:validPointPositions){
            if(i<19 || i>24)
                bearOffPlayer1 = false;
            if(i<1 || i>6)
                bearOffPlayer2 = false;
        }
        return bearOffPlayer1 || bearOffPlayer2;
    }

    private static void getBearOffMoves(Point[] points, int roll1, int roll2){
        //If same role dice
        int mapKey  = 1;
        if(roll1 == roll2){
            for (int i: validPointPositions){
                for(int j: validPointPositions){
                    for(int k: validPointPositions){
                        for (int l: validPointPositions){
                            HashMap<Integer, Integer> pointsCountMap = new HashMap<>();
                            pointsCountMap.put(i, pointsCountMap.getOrDefault(i,0)+1);
                            pointsCountMap.put(j, pointsCountMap.getOrDefault(j,0)+1);
                            pointsCountMap.put(k, pointsCountMap.getOrDefault(k,0)+1);
                            pointsCountMap.put(l, pointsCountMap.getOrDefault(l,0)+1);
                            ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
                            for (int key : pointsCountMap.keySet()){
                                int numOfRolls = pointsCountMap.get(key);
                                if((key+roll1*numOfRolls)<1 || (key+roll1*numOfRolls)>24){
                                    moves.add(new ArrayList<Integer>(Arrays.asList(key,-1)));
                                }
                            }
                            if(moves.size() == pointsCountMap.size()) {
                                map.put(mapKey++, moves);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int i : validPointPositions) {
                for (int j : validPointPositions) {
                    if(i==j) {
                        if ((i + roll1 + roll2)<1 || (i + roll1 + roll2)>24) {
                            map.put(mapKey, new ArrayList<ArrayList<Integer>>());
                            map.get(mapKey++).add(new ArrayList<Integer>(Arrays.asList(i, -1)));
                        }
                    }
                    else {
                        ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
                        boolean foundAtleastOneMove = false;
                        if((i+roll1) >24 || (i+roll1) <1) {
                            moves.add(new ArrayList<Integer>(Arrays.asList(i, -1)));
                            foundAtleastOneMove = true;
                        }
                        if((j+roll2) >24 || (j+roll2) <1) {
                            moves.add(new ArrayList<Integer>(Arrays.asList(j, -1)));
                            foundAtleastOneMove = true;
                        }
                        if(foundAtleastOneMove){
                            map.put(mapKey++, moves);
                        }
                    }

                }
            }
        }
    }
    public static HashMap getMoves(Point.Checker checker, Point[] points, int roll1, int roll2){
        //clear old context
        validPointPositions.clear();
        map.clear();
        int mapKey = 1;

        //find points of given checker type
        for(int i=0;i<points.length;i++){
            if(points[i].equals(checker)){
                validPointPositions.add(i);
            }
        }

        //Rule - Off
        if(isBearOff()){
            getBearOffMoves(points, roll1, roll2);
        }
        else {

            if(roll1 == roll2){
                System.out.println("same dice:"+roll1);
                HashSet<String> set = new HashSet<>();
                //Rule - Same dice roll
                // Four points could be moved with value of roll1
                for (int i=0; i<validPointPositions.size();i++)
                    for (int j=i; j<validPointPositions.size();j++)
                        for (int k=j; k<validPointPositions.size();k++)
                            for(int l=k; l<validPointPositions.size();l++){
                                HashMap<Integer, Integer> pointsCountMap = new HashMap<>();
                                pointsCountMap.put(validPointPositions.get(i), pointsCountMap.getOrDefault(validPointPositions.get(i),0)+1);
                                pointsCountMap.put(validPointPositions.get(j), pointsCountMap.getOrDefault(validPointPositions.get(j),0)+1);
                                pointsCountMap.put(validPointPositions.get(k), pointsCountMap.getOrDefault(validPointPositions.get(k),0)+1);
                                pointsCountMap.put(validPointPositions.get(l), pointsCountMap.getOrDefault(validPointPositions.get(l),0)+1);

                                ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
                                for (int key : pointsCountMap.keySet()){
                                    int numOfRolls = pointsCountMap.get(key);
                                    if(isValidMove(points, key, roll1*numOfRolls) && areBarsClearable(key)){
                                        moves.add(new ArrayList<Integer>(Arrays.asList(key,key+roll1*numOfRolls)));
                                    }
                                }
                                if(moves.size() == pointsCountMap.size()) {
                                    map.put(mapKey++, moves);
                                }
                            }
            }
            else {
                for (int i : validPointPositions) {
                    for (int j : validPointPositions) {
                        if (isValidMove(points, i, roll1, j, roll2) && (areBarsClearable(i) || areBarsClearable(j))) {
                            map.put(mapKey, new ArrayList<ArrayList<Integer>>());
                            if (i == j) {
                                // same point could be played with dice values
                                map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(i,i + roll1 + roll2)));
                            } else {
                                // Point1,Point2 will be played with roll1 and roll2 respectively
                                map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(i,i + roll1)));
                                map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(j,j + roll2)));
                            }
                            mapKey++;
                        }
                    }
                }
            }
        }

        //Rule - Hit

        //Clear stored validPoints context
        validPointPositions.clear();
        return map;
    }

    private static boolean isValidMove(Point [] points, int position, int roll){
        boolean isValid = false;
        //move points[position] with roll value
        if((position+roll) >=1 && (position+roll) <=24){
            if (points[position+roll].isEmpty())
                isValid = true;
            else if (points[position].equals(points[position+roll]))
                isValid = true;
            else{
                //checkers to move across different type of points
                isValid = points[position+roll].getSize() == 1;
            }
        }
        return isValid;
    }

    private static boolean areBarsClearable(int position){
        //Check if in hit condition, all checkers should be cleared from Bar
        return (validPointPositions.contains(0) == (position == 0)) && (validPointPositions.contains(25) == (position == 25)) ;
    }

    private static boolean isValidMove(Point[] points, int firstPoint, int roll1, int secondPoint, int roll2){
        boolean isValid = false;
        //move one point forward with roll1 and roll2
        if(firstPoint == secondPoint){
            //same points moves two times
            isValid = isValidMove(points, firstPoint, roll1+roll2) && areBarsClearable(firstPoint);
        }
        else{
            // movement of two points
            boolean firstMoveValid = isValidMove(points, firstPoint, roll1) ;
            boolean secondMoveValid = isValidMove(points, secondPoint, roll2);;
            isValid = firstMoveValid && secondMoveValid;
        }
        return isValid;
    }

    public static void makeMove(Point[] points,int start, int end,Point.Checker checker)
    {
        //If end==-1 then BearOFF directly
        //else check if Hitting condition is met and
        if(end != -1){
            //If marked hit then move destination point checker to Bar
            if(!points[start].equals(points[end]) && points[end].getSize()==1) {
                if(points[end].equals(Point.Checker.X)){
                    points[0].add(points[end].getList().pop());
                }
                else{
                    points[25].add(points[end].getList().pop());
                }
            }
            points[end].add(checker);
        }
        points[start].getList().pop();
    }

}
