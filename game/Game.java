package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static game.Move.getMoves;
import static game.Move.makeMove;

public class Game{

    static Point[]  points= new Point[26];
    //Points[0] - Lower Bar
    //Points[1-24] - Checker Points
    //Points[25] - Upper Bar
    static HashMap<Integer, ArrayList<ArrayList<Integer>>> movesList = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    public static void  main(String[] args){
        User player1 = new User();
        User player2 = new User();


        boolean quit = false;
        boolean turn = true;

        assignCheckers();
        //Assign players
        System.out.println("Welcome to the Game!");
        System.out.print("Enter name of Player1 : ");
        String username1 = scanner.nextLine();
        player1.setUsername(username1);
        player1.setType("X");

        System.out.print("Enter name of Player2 : ");
        String username2 = scanner.nextLine();
        System.out.println();
        player2.setUsername(username2);
        player2.setType("O");
        System.out.println("Rolling to decide who will play first");

        int player1Roll = rollDice1();
        System.out.println(username1 + " rolled a " + player1Roll);

        int player2Roll = rollDice1();
        System.out.println(username2 + " rolled a " + player2Roll);
        System.out.println();

        if(player1Roll >= player2Roll){
            turn = true;
            System.out.println(username1 + " plays first");
        }
        else{
            turn = false;
            System.out.println(username2 + " plays first");
        }
        System.out.println();

        while(!quit) {
            display();
            switch (showMenu(turn ? player1.getUsername():player2.getUsername() )) {
                case 1:
                    int diceRoll1 = rollDice1();
                    int diceRoll2 = rollDice2();
                    if(diceRoll1==diceRoll2)
                        diceRoll2= diceRoll2+1;

                    System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
                    System.out.println();
                    //Move the checkers
                    if(turn)
                        //player 1
                        movesList = getMoves("X", points, diceRoll1, diceRoll2);
                    else
                        //player 2
                        movesList = getMoves("O", points, -diceRoll1, -diceRoll2);

                    System.out.println(movesList);
                    for(int key:movesList.keySet()){
                        String command = new String(key+".");

                        for(int i=0;i<movesList.get(key).size();i++){
                            if(i>0)
                                command+= " and";

                            if(movesList.get(key).get(i).get(1)==-1)
                                command+= (" Point"+movesList.get(key).get(i).get(0)+" BearOff");
                            else {
                                if(movesList.get(key).get(i).get(0) == 0 || movesList.get(key).get(i).get(0) == 25)
                                    command += (" From Bar -> Point" + movesList.get(key).get(i).get(1));
                                else
                                    command += (" Point" + movesList.get(key).get(i).get(0) + " -> Point" + movesList.get(key).get(i).get(1));
                            }

                        }
                        System.out.println(command);
                    }

                    System.out.println("Enter the command number from the list to make a move !!");
                    int movechoice = scanner.nextInt();
                    if (movesList.containsKey(movechoice)) {
                        ArrayList<ArrayList<Integer>> selectedMoves = movesList.get(movechoice);
                        for (ArrayList<Integer> move : selectedMoves) {
                            int start = move.get(0);
                            int end = move.get(1);

                            if (turn) {
                                boolean hit = makeMove(points, start, end, "X");
                                if(hit) points[25].add("O");
                            } else {
                                boolean hit = makeMove(points, start, end, "O");
                                if(hit) points[0].add("X");
                            }
                        }
                    } else {
                        System.out.println("Invalid move selection.");
                    }

                    movesList.clear();
                    break;
                case 2:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            turn = !turn;
        }

        scanner.close();

    }

    public static void addChecker(Point point, int count, String checker){
        for(int i=1;i<=count;i++){
            point.add(checker);
        }
    }

    public static <T> String getStyledString(T input, String style){
        return style+input+"\u001B[0m";
    }

    public static void assignCheckers(){
        for(int i=0;i<points.length;i++){
            points[i] = new Point();
        }

        addChecker(points[1], 2, "X");
        addChecker(points[6], 5, "O");
        addChecker(points[8], 3, "O");
        addChecker(points[12], 5, "X");

        addChecker(points[13], 5, "O");
        addChecker(points[17], 3, "X");
        addChecker(points[19], 5, "X");
        addChecker(points[24], 2, "O");
    }

    public static int showMenu(String playerName) {
        System.out.println();
        System.out.println("1. Roll a dice");
        System.out.println("2. Quit");
        System.out.print(playerName+", enter your choice: ");

        int choice = scanner.nextInt();
        return choice;
    }

    //Display - refactor this
    public static void display(){
        int bottomMax=0, topMax=0;

        for(int i=13;i<=25; i++){
            topMax = Math.max(topMax, points[i].getSize());
        }
//        topMax = Math.max(topMax, bars[2].getSize());

        for(int i=0;i<=12; i++){
            bottomMax = Math.max(bottomMax, points[i].getSize());
        }
//        bottomMax = Math.max(bottomMax, bars[1].getSize());

        String[][] lowerSection = new String[bottomMax][13];
        String[][] upperSection = new String[topMax][13];

        for(int i=0; i<topMax ;i++){
            for(int j=0; j<13; j++) {
                if(j==6)
                    upperSection[i][j] = "=";
                else
                    upperSection[i][j] = "|";
            }
        }

        for(int i=0; i<bottomMax ;i++){
            for(int j=0; j<13; j++) {
                if(j==6)
                    lowerSection[i][j] = "=";
                else
                    lowerSection[i][j] = "|";
            }
        }

        for(int j=12;j>=7;j--) {
            for (int i = 0; i < points[j].list.size(); i++) {
                lowerSection[i][12-j] = points[j].list.get(i);
            }
        }
        for(int j=6;j>=1;j--) {
            for (int i = 0; i < points[j].list.size(); i++) {
                lowerSection[i][12-j+1] = points[j].list.get(i);
            }
        }
        for(int i=0;i< points[0].getSize();i++){
            lowerSection[i][6] = points[0].list.get(i);
        }


        for(int j=13;j<=18;j++) {
            for (int i = 0; i< points[j].list.size(); i++) {
                upperSection[i][j-13] = points[j].list.get(i);
            }
        }
        for(int j=19;j<=24;j++) {
            for (int i = 0; i< points[j].list.size(); i++) {
                upperSection[i][j-13+1] = points[j].list.get(i);
            }
        }
        for(int i=0;i< points[25].getSize();i++){
            upperSection[i][6] = points[25].list.get(i);
        }

        System.out.print("13");
        for(int i = 14; i<26;i++)
        {
            String st = "";
            if(i == 19)
            {
                st +="BAR";
            }
            else if( i >19)
            {
                st += (i-1);
            }
            else
            {
                st += i;
            }
            String formatted = String.format("%"+ 4 +"s", st);
            System.out.print(formatted);
        }

        System.out.print(" OFF");

        System.out.println();

        System.out.println();

        for(int i=0;i<topMax;i++){
            for(int j=0;j<13;j++){
                if(upperSection[i][j].equals("X"))
                    System.out.print(getStyledString(upperSection[i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(upperSection[i][j]+"   ");
            }
            System.out.println("\n");
        }

        System.out.println();

        for(int i=0;i<bottomMax;i++){
            for(int j=0;j<13;j++){
                if(lowerSection[bottomMax-1-i][j].equals("X"))
                    System.out.print(getStyledString(lowerSection[bottomMax-1-i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(lowerSection[bottomMax-1-i][j]+"   ");
            }
            System.out.println("\n");
        }

        System.out.print("12");

        for(int i = 11; i>-1;i--)
        {
            String st = "";
            if(i == 6)
            {
                st += "BAR";
            }
            else if(i < 6)
            {
                st += "0"+ (i+1);
            }
            else
            {
                if(i <= 9)
                {
                    st += "0";
                }
                st += i;
            }
            String formatted = String.format("%"+ 4 +"s", st);
            System.out.print(formatted);
        }

        System.out.print(" OFF");
    }

    public static int rollDice1() {
        return (int)(Math.random() * 6) + 1;
    }

    public static int rollDice2() {
        return (int)(Math.random() * 6) + 1;
    }

}