package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Move {

    static ArrayList<Integer> validPointPositions = new ArrayList<>();
    static HashMap<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();

    public static boolean isOff(){
        boolean bearOffPlayer1 = true, bearOffPlayer2 = true;
        for(int i:validPointPositions){
            if(i<19)
                bearOffPlayer1 = false;
            else if(i>6 )
                bearOffPlayer2 = false;
        }
        return bearOffPlayer1 || bearOffPlayer2;
    }

    static void showOffMoves(Point[] points, int roll1, int roll2){
        //If same role dice
        int mapKey  = 1;
        if(roll1 == roll2){
            HashSet<String> set = new HashSet<>();
            for (int i: validPointPositions){
                for(int j: validPointPositions){
                    for(int k: validPointPositions){
                        for (int l: validPointPositions){
                            HashMap<Integer, Integer> pointsCountMap = new HashMap<>();
                            pointsCountMap.put(i, pointsCountMap.getOrDefault(i,0)+1);
                            pointsCountMap.put(j, pointsCountMap.getOrDefault(j,0)+1);
                            pointsCountMap.put(k, pointsCountMap.getOrDefault(k,0)+1);
                            pointsCountMap.put(l, pointsCountMap.getOrDefault(l,0)+1);
                            StringBuilder temp = new StringBuilder();
                            ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();

                            if (pointsCountMap.size() == 1) {
                                //Same point move roll1 * 4 times
                                for (int key : pointsCountMap.keySet())
                                    if ((key + roll1 + roll1 + roll1 + roll1) > 24 || (key + roll1 + roll1 + roll1 + roll1) < 1) {
                                        temp.append("Point ").append(key).append("BearOFF");
                                        moves.add(new ArrayList<Integer>(Arrays.asList(key,-1)));
                                    }
                            } else if (pointsCountMap.size() == 2) {
                                //Two points move roll1 * 2 times each
                                for (int key : pointsCountMap.keySet())
                                    if ((key + roll1 + roll1) < 1 || (key + roll1 + roll1) > 24) {
                                        temp.append("Point ").append(key).append("BearOFF");
                                        moves.add(new ArrayList<Integer>(Arrays.asList(key, -1)));
                                    }
                            } else if (pointsCountMap.size() == 3) {
                                //One point move roll1 * 2 times and remaining points roll1 times
                                for (int key : pointsCountMap.keySet()) {
                                    if (pointsCountMap.get(key) > 1) {
                                        if ((key + roll1 + roll1) < 1  || (key + roll1 + roll1) > 24) {
                                            temp.append("Point ").append(key).append("BearOFF");
                                            moves.add(new ArrayList<Integer>(Arrays.asList(key,-1)));
                                        }
                                    } else if ((key + roll1) < 1 || (key + roll1) > 24 ) {
                                        temp.append("Point ").append(key).append("BearOFF");
                                        moves.add(new ArrayList<Integer>(Arrays.asList(key,-1)));
                                    }
                                }
                            }
                            else {
                                // All points i, j, k and l move roll1 times
                                for (int key : pointsCountMap.keySet()) {
                                    if((key+roll1) < 1 || (key+roll1) > 24){
                                        temp.append("Point ").append(key).append("BearOFF");
                                        moves.add(new ArrayList<Integer>(Arrays.asList(key,-1)));
                                    }
                                }
                            }
                            if(!temp.isEmpty() && !set.contains(temp.toString())) {
                                set.add(temp.toString());
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
                    map.put(mapKey, new ArrayList<ArrayList<Integer>>());
                    if(i==j) {
                        if ((i + roll1 + roll2) > 24 || (i + roll1 + roll2) < 1)
                            map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(i,-1)));
                    }
                    else {
                        if((i+roll1) >24 || (i+roll1) <1)
                            map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(i,-1)));
                        if((j+roll2) >24 || (j+roll2) <1)
                            map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(j,-1)));
                    }
                    mapKey++;

                }
            }
        }
    }
    public static HashMap getMoves(String type, Point[] points, int roll1, int roll2){
        //find points of given value of "type" parameter
        for(int i=0;i<points.length;i++){
            if(!points[i].isEmpty() && points[i].getType().equals(type)){

                validPointPositions.add(i);
            }
        }
//        System.out.println("ValidPositions:"+validPointPositions);
        int mapKey = 1;
        //Rule - Off
        if(isOff()){
            showOffMoves(points, roll1, roll2);
        }
        else {

            if(roll1 == roll2){
                System.out.println("same dice");
                HashSet<String> set = new HashSet<>();
                //Rule - Same dice roll
                // Four points could be moved with value of roll1
                for (int i=0; i<validPointPositions.size();i++)
                    for (int j=i; j<validPointPositions.size();j++)
                        for (int k=j; k<validPointPositions.size();k++)
                            for(int l=k; l<validPointPositions.size();l++){
//                                if(type.equals("X") && !points[0].isEmpty() && (i!=0 || j!=0 || k!=0 || l!=0)) {
//                                    continue;
//                                }
//                                else if(type.equals("O") && !points[25].isEmpty() && (i!=25 || j!=25 || k!=25 || l!=25)) {
//                                    continue;
//                                }

                                HashMap<Integer, Integer> pointsCountMap = new HashMap<>();
                                pointsCountMap.put(validPointPositions.get(i), pointsCountMap.getOrDefault(validPointPositions.get(i),0)+1);
                                pointsCountMap.put(validPointPositions.get(j), pointsCountMap.getOrDefault(validPointPositions.get(j),0)+1);
                                pointsCountMap.put(validPointPositions.get(k), pointsCountMap.getOrDefault(validPointPositions.get(k),0)+1);
                                pointsCountMap.put(validPointPositions.get(l), pointsCountMap.getOrDefault(validPointPositions.get(l),0)+1);

                                StringBuilder temp = new StringBuilder();
                                ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
                                    if (pointsCountMap.size() == 1) {
                                        //Same point move roll1 * 4 times
                                        for (int key : pointsCountMap.keySet())
                                            if ((key + roll1 + roll1 + roll1 + roll1) >= 1 && (key + roll1 + roll1 + roll1 + roll1) <= 24) {
                                                temp.append("Point").append(key).append("->Point").append(key + (roll1 * 4));
                                                moves.add(new ArrayList<Integer>(Arrays.asList(key,(key + roll1 * 4))));
                                            }
                                    } else if (pointsCountMap.size() == 2) {
                                        //Two points move roll1 * 2 times each
                                        for (int key : pointsCountMap.keySet())
                                            if ((key + roll1 + roll1) >= 1 && (key + roll1 + roll1) <= 24)
                                                moves.add(new ArrayList<Integer>(Arrays.asList(key,(key + roll1 * 2))));
                                    } else if (pointsCountMap.size() == 3) {
                                        //One point move roll1 * 2 times and remaining points roll1 times
                                        for (int key : pointsCountMap.keySet()) {
                                            if (pointsCountMap.get(key) > 1) {
                                                if ((key + roll1 + roll1) >= 1 && (key + roll1 + roll1) <= 24)
                                                    moves.add(new ArrayList<Integer>(Arrays.asList(key,(key + (roll1 * 2)))));
                                            } else if ((key + roll1) >= 1 && (key + roll1) <= 24)
                                                moves.add(new ArrayList<Integer>(Arrays.asList(key,(key + roll1))));
                                        }
                                    }
                                    else {
                                        // All points i, j, k and l move roll1 times
                                        for (int key : pointsCountMap.keySet()) {
                                            if((key+roll1) >= 1 && (key+roll1) <= 24)
                                                moves.add(new ArrayList<Integer>(Arrays.asList(key,(key + roll1))));
                                        }
                                    }
                                    if(!temp.isEmpty() && !set.contains(temp.toString())) {
                                        set.add(temp.toString());
                                        map.put(mapKey++, moves);
                                    }
                            }

//                System.out.println(map);
            }
            else {
                for (int i : validPointPositions) {
                    for (int j : validPointPositions) {
                        if (isValidMove(points, i, roll1, j, roll2)) {
//                            System.out.println(points[0].getSize()+" and "+ points[25].getSize());
                            if(points[0].isEmpty() && (i==0 && j==0)) {
                                continue;
                            }
                            else if(points[25].isEmpty() && (i==25 || j==25)) {
                                continue;
                            }

                            map.put(mapKey, new ArrayList<ArrayList<Integer>>());
                            if (i == j) {
                                // same point could be played with dice values
                                map.get(mapKey).add(new ArrayList<Integer>(Arrays.asList(i,i + roll1 + roll2)));
                            } else {
                                // Point1,Point2 will be played with roll1 and roll2 respectively
//                                System.out.println("Point " + i + " -> Point " + (i + roll1) +
//                                        " and Point " + j + " -> Point " + (j + roll2));
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

    public static boolean isValidMove(Point[] points, int firstPoint, int roll1, int secondPoint, int roll2){
            //play points forward
            if(firstPoint == secondPoint){
                //same points moves two times
                if((firstPoint+roll1+roll2) >=1 && (firstPoint+roll1+roll2) <=24){
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
                if((firstPoint+roll1) >=1 && (firstPoint+roll1) <=24){
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
                if((secondPoint+roll2) >=1 && (secondPoint+roll2) <=24){
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

    public static boolean makeMove(Point[] points,int start, int end,String type)
    {
        boolean markedHit = false;
        if(end != -1){
            //If end==-1 then BearOFF
            if(points[end].getSize()==1) {
                //marked hit
                markedHit = true;
                points[end].getList().pop();
            }
            points[end].add(type);

        }
        points[start].getList().pop();

        return markedHit;
    }

}
