package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import game.Triangle.Checker;

public class Game{

    //Points[0] - Lower Bar
    //Points[1-24] - Checker Points
    //Points[25] - Upper Bar
    static private Point[]  points= new Point[26];

    static private HashMap<Integer, ArrayList<ArrayList<Integer>>> movesList = new HashMap<>();

    static private Scanner scanner = new Scanner(System.in);

    static private boolean turn;

    static private Move move = new Move();

    static private int currentMatch = 1;

    public static void main(String[] args) throws InterruptedException {

        //Initialise game
        System.out.println("Welcome to the Game!");
        System.out.println();
        boolean quit = false;

        //Get the Game Length
        System.out.print("Enter match length : ");
        int matchLength = Integer.parseInt(scanner.nextLine());

        //Initialise Board
        Board board = new Board();
        board.assignCheckers(points);
        PipCount pip = new PipCount();

        //Add players
        System.out.print("Enter name of Player1 : ");
        User player1 = new User(scanner.nextLine());
        System.out.print("Enter name of Player2 : ");
        User player2 = new User(scanner.nextLine());
        System.out.println();

        //Find which player will start the game
        System.out.println("Rolling to decide who will play first....");
        int player1Roll = Board.rollDice();
        int player2Roll = Board.rollDice();
        System.out.println(player1.getUsername() + " rolled a " + player1Roll+" and "+player2.getUsername() + " rolled a " + player2Roll);
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

        //Run game
        while(!quit && currentMatch<=matchLength) {
            if(FoundWinner(player1, player2)) {
                if(player1.isWinner()) {
                    resetGame(board, player1, player2);
                }
                else {
                    resetGame(board, player2, player1);
                }
                currentMatch++;
                continue;
            }
            board.display(turn, points, player1, player2);


            switch (showMenu(turn ? player1.getUsername():player2.getUsername() )) {
                case 1:
                    int diceRoll1 = Board.rollDice();
                    int diceRoll2 = Board.rollDice();

                    System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
                    System.out.println();
                    //Move the checkers
                    if(turn)
                        //player 1
                        movesList = move.getMoves(Checker.X, points, diceRoll1, diceRoll2);
                    else
                        //player 2
                        movesList = move.getMoves(Checker.O, points, -diceRoll1, -diceRoll2);

                    
                    showMoves();
                    executeMoves();
                    movesList.clear();
                    break;
                case 2:

                    quit = true;
                    break;
                case 3:
                    int[] pipCount = pip.countTotalPip(points);
                    Board.displayPip(player1.getUsername(), player2.getUsername(), pipCount[0], pipCount[1]);
                    turn = !turn;
                    break;
                case 4:
                    System.out.println("Enter custom dice1 value :");
                    int dice1 = scanner.nextInt();
                    System.out.println("Enter custom dice2 value :");
                    int dice2 = scanner.nextInt();
                    if(turn)
                        //player 1
                        movesList = move.getMoves(Checker.X, points, dice1, dice2);
                    else
                        //player 2
                        movesList = move.getMoves(Checker.O, points, -dice1, -dice2);

                    System.out.println(movesList);
                    showMoves();
                    executeMoves();
                    movesList.clear();
                    break;
                case 5:
                    if (turn) {
                        if(player1.isDoubleOwner()){
                            System.out.println(player1.getUsername() + " offers a double.");
                            DoublingCube.runDouble(player2);
                            int doubleChoice = scanner.nextInt();
                            if(doubleChoice == 1){
                                player1.setDoubleOwner(false);
                                player2.setDoubleOwner(true);
                                DoublingCube.doubleAccepted();
                                System.out.println(player2.getUsername() + " accepted to double!!");
                            }
                            else if(doubleChoice == 2){
                                System.out.println(player2.getUsername() + " rejected your double, hence you won this match");
                                currentMatch++;
                                player1.addScore(DoublingCube.getDoubleCount());
                                resetGame(board, player1, player2);
                            }
                            else{
                                //Check again
                                System.out.println("Wrong Command, Moving forward");
                            }
                        }
                        else
                            System.out.println("Sorry!! You are not allowed to offer doubling");
                    }
                    else {
                        if(player2.isDoubleOwner()){
                            System.out.println(player2.getUsername() + " offers a double.");
                            DoublingCube.runDouble(player1);
                            int doubleChoice = scanner.nextInt();
                            if(doubleChoice == 1){
                                player2.setDoubleOwner(false);
                                player1.setDoubleOwner(true);
                                DoublingCube.doubleAccepted();
                                System.out.println(player1.getUsername() + " accepted to double!!");
                            }
                            else if(doubleChoice == 2){
                                System.out.println(player1.getUsername() + " rejected your double, hence you won this match");
                                currentMatch++;
                                player2.addScore(DoublingCube.getDoubleCount());
                                resetGame(board, player2, player1);
                            }
                            else{
                                //Check again
                                System.out.println("Wrong Command, Moving forward");
                            }
                        }
                        else
                            System.out.println("Sorry!! You are not allowed to offer doubling");
                    }
                    DoublingCube.display();
                    turn = !turn;
                    break;
                case 6:
                    TestFile tf = new TestFile();
                    quit = tf.readTestFile(points,player1,player2);
                    turn = !turn;
                    break;
                case 7:
                    currentMatch++;
                    pipCount = pip.countTotalPip(points);
                    if (pipCount[0] > pipCount[1]) {
                        player1.addScore(DoublingCube.getDoubleCount());
                        resetGame(board, player1, player2);
                    } else {
                        player2.addScore(DoublingCube.getDoubleCount());
                        resetGame(board, player2, player1);
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            turn = !turn;
        }
        System.out.println("Thank you playing!!");
        Thread.sleep(1000);
        System.out.println("The game has ended and scores are...");
        System.out.println(player1.getUsername()+": "+player1.getScore()+" and "+player2.getUsername()+": "+player2.getScore());
        scanner.close();
    }

    public static void resetGame(Board board, User Winner, User Loser){
        System.out.println("Congratulations "+Winner.getUsername()+"!!!, current match is over");
        System.out.println("Score Board");
        System.out.println(Winner.getUsername()+": "+Winner.getScore()+" and "+Loser.getUsername()+": "+Loser.getScore());
        points = new Point[26];
        board = new Board();
        board.assignCheckers(points);
        DoublingCube.setDoubleCount(1);
    }

    public static boolean FoundWinner(User player1, User player2){
        boolean player1Won=true, player2Won=true;
        for (int i = 1; i <= 24; i++) {
            if (points[i].getList().contains(Point.Checker.X)) {
                player1Won = false;
            }
            if (points[i].getList().contains(Point.Checker.O)) {
                player2Won = false;
            }
        }
        if(points[0].getList().contains(Checker.X))
            player1Won = false;
        if(points[25].getList().contains(Checker.O))
            player2Won = false;

        if(player1Won) {
            player1.setWinner();
            int defeatType = determineGameResult(Checker.O);
            if(defeatType == 1)
                System.out.println("The Opponent is defeated normally ");
            else if(defeatType == 2)
                System.out.println("The Opponent is Gammoned ");
            else if(defeatType == 3)
                System.out.println("The Opponent is Back-Gammoned ");
            player1.addScore(defeatType*DoublingCube.getDoubleCount());

        }
        else if(player2Won) {
            player2.setWinner();
            int defeatType = determineGameResult(Checker.X);
            if(defeatType == 1)
                System.out.println("The Opponent is defeated normally ");
            else if(defeatType == 2)
                System.out.println("The Opponent is Gammoned ");
            else if(defeatType == 3)
                System.out.println("The Opponent is Back-Gammoned ");
            player2.addScore(defeatType*DoublingCube.getDoubleCount());
        }

        return (player1Won || player2Won);

    }
    public static int showMenu(String playerName) {
        System.out.println();
        System.out.println("Select from 1. Roll a dice 2. Quit Game 3. Pip 4. Dice 5. Double 6. Test File 7. End Current Match");
        System.out.print("Player "+playerName+", enter your choice(1 to 7): ");
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
            for (ArrayList<Integer> moves : selectedMoves) {
                int start = moves.get(0);
                int end = moves.get(1);
                if (turn) {
                    move.makeMove(points, start, end, Checker.X);
                } else {
                    move.makeMove(points, start, end, Checker.O);
                }
            }
        } else {
            System.out.println("Invalid move selection.");
        }
    }

    public static int determineGameResult(Checker checker) {
        boolean hasCheckerOnBar =false;
        int checkerCount = 0;
        boolean hasCheckerOnOtherPlayersHome = false;
        for(int i=1;i<=24;i++){
            if(points[i].getList().contains(checker)){
                checkerCount += points[i].getSize();
            }
        }
        if(points[0].getList().contains(checker)|| points[25].getList().contains(checker))
            hasCheckerOnBar = true;
        //If there is at least one checkers beared off, then same value of doubling cube
        if(checkerCount<15 && !hasCheckerOnBar)
            return 1;
        //Otherwise, if there are any checkers on bar or home quadrant of other player, thrice the value of doubling cube
        if(checker == Checker.X){
            for(int i=19;i<=24;i++)
            {
                if(points[i].getList().contains(checker))
                    hasCheckerOnOtherPlayersHome = true;
            }
        }
        else{
            for(int i=1;i<=6;i++)
            {
                if(points[i].getList().contains(checker))
                    hasCheckerOnOtherPlayersHome = true;
            }
        }

        if(hasCheckerOnBar || hasCheckerOnOtherPlayersHome)
            return 3;

        //Else, twice the value of doubling cube
        return 2;
    }

    //Utility function to print colored Strings
    public static <T> String getStyledString(T input, String style){
        return style+input+"\u001B[0m";
    }
}