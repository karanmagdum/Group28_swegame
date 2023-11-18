package game;

import java.util.Scanner;

import static game.Move.getMoves;

public class Game{

    static Point[]  points= new Point[25];
    static Point[] bars = new Point[3];
    static Scanner scanner = new Scanner(System.in);

    public static void  main(String[] args){

        boolean quit = false;
        boolean turn = true;

        assignCheckers();
        //Assign players
        System.out.println("Welcome to the Game!");
        System.out.print("Enter name of Player1 : ");
        String username1 = scanner.nextLine();

        System.out.print("Enter name of Player2 : ");
        String username2 = scanner.nextLine();
        System.out.println("\n");

        while(!quit) {
            display();

            String currentPlayer = turn ? username1 : username2;
            System.out.println("\n");
            System.out.println(currentPlayer + "'s turn:");
    
            switch (showMenu()) {
                case 1:
                    int diceRoll1 = rollDice1();
                    int diceRoll2 = rollDice2();

                    System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
                    System.out.println();
                    //Move the checkers
                    if(turn)
                        //player 1
                        getMoves("X", points, diceRoll1, diceRoll2, true);
                    else
                        //player 2
                        getMoves("O", points, diceRoll1, diceRoll2, false);
                    System.out.println();

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
        for(int i=0;i<bars.length;i++){
            bars[i] = new Point();
        }


        addChecker(points[1], 2, "X");
        addChecker(points[6], 5, "O");
        addChecker(bars[1], 5, "=");
        addChecker(points[8], 3, "O");
        addChecker(points[12], 5, "X");

        addChecker(points[13], 5, "O");
        addChecker(points[17], 3, "X");
        addChecker(bars[2], 5, "=");
        addChecker(points[19], 5, "X");
        addChecker(points[24], 2, "O");
    }

    public static int showMenu() {
        System.out.println("\n1. Roll a dice");
        System.out.println("2. Quit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        return choice;
    }

    //Display - refactor this
    public static void display(){
        int bottomMax=0, topMax=0;

        for(int i=13;i<=24; i++){
            topMax = Math.max(topMax, points[i].getSize());
        }

        for(int i=1;i<=12; i++){
            bottomMax = Math.max(bottomMax, points[i].getSize());
        }


        String[][] lowerSection = new String[bottomMax][13];
        String[][] upperSection = new String[topMax][13];


        for(int i=0; i<topMax ;i++){
            for(int j=0; j<13; j++) {
                upperSection[i][j] = "|";
            }
        }

        for(int i=0; i<bottomMax ;i++){
            for(int j=0; j<13; j++) {
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
        for(int i=0;i< bars[1].getSize();i++){
            lowerSection[i][6] = bars[1].list.get(i);
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
        for(int i=0;i< bars[2].getSize();i++){
            upperSection[i][6] = bars[2].list.get(i);
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