package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game{

    //Points[0] - Lower Bar
    //Points[1-24] - Checker Points
    //Points[25] - Upper Bar
    static Point[]  points= new Point[26];

    static HashMap<Integer, ArrayList<ArrayList<Integer>>> movesList = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    private static User player1;
    private static User player2;

    private static boolean turn;

    public static void main(String[] args){

        //Initialise Board
        boolean quit = false;
        Board.assignCheckers(points);
        //Assign players
        System.out.println("Welcome to the Game!");
        System.out.println();
        System.out.print("Enter name of Player1 : ");
        String username1 = scanner.nextLine();
        player1 = new User(username1);
        System.out.print("Enter name of Player2 : ");
        String username2 = scanner.nextLine();
        player2 = new User(username2);
        System.out.println();
        System.out.println("Rolling to decide who will play first....");
        int player1Roll = Board.rollDice();
        int player2Roll = Board.rollDice();
        System.out.println(username1 + " rolled a " + player1Roll+" and "+username2 + " rolled a " + player2Roll);
        System.out.println();

        if(player1Roll >= player2Roll){
            turn = true;
            System.out.println(player1.getUsername() + " plays first");
        }
        else{
            turn = false;
            System.out.println(player2.getUsername() + " plays first");
        }
        System.out.println();

        while(!quit) {
            Board.display(points);
            switch (showMenu(turn ? player1.getUsername():player2.getUsername() )) {
                case 1:
                    int diceRoll1 = Board.rollDice();
                    int diceRoll2 = Board.rollDice();

                    System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
                    System.out.println();
                    //Move the checkers
                    if(turn)
                        //player 1
                        movesList = Move.getMoves(Point.Checker.X, points, diceRoll1, diceRoll2);
                    else
                        //player 2
                        movesList = Move.getMoves(Point.Checker.O, points, -diceRoll1, -diceRoll2);

                    System.out.println(movesList);
                    showMoves();
                    executeMoves();
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

    //Utility function to print colored Strings
    public static <T> String getStyledString(T input, String style){
        return style+input+"\u001B[0m";
    }
    public static int showMenu(String playerName) {
        System.out.println();
        System.out.println("1. Roll a dice");
        System.out.println("2. Quit");
        System.out.print("Player "+playerName+", enter your choice: ");
        return scanner.nextInt();
    }

    public static void showMoves(){
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
    }

    public static void executeMoves(){
        System.out.println("Enter the command number from the list to make a move !!");
        int moveChoice = scanner.nextInt();
        if (movesList.containsKey(moveChoice)) {
            ArrayList<ArrayList<Integer>> selectedMoves = movesList.get(moveChoice);
            for (ArrayList<Integer> move : selectedMoves) {
                int start = move.get(0);
                int end = move.get(1);
                if (turn) {
                    Move.makeMove(points, start, end, Point.Checker.X);
                } else {
                    Move.makeMove(points, start, end, Point.Checker.O);
                }
            }
        } else {
            System.out.println("Invalid move selection.");
        }
    }
}