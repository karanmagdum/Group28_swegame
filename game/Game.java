package game;

import java.util.Scanner;

public class Game{

    static Point[] points = new Point[26];
    static Scanner scanner = new Scanner(System.in);

   

    public static void  main(String[] args){
        System.out.println("Welcome to the Game!");
        System.out.print("Enter name of Player1 : ");
        String username1 = scanner.nextLine();

        System.out.print("Enter name of Player2 : ");
        String username2 = scanner.nextLine();
        System.out.println("\n");

        boolean quit = false;
        boolean turn = true; 

        while(!quit) {
            assignCheckers();
            display();

            String currentPlayer = turn ? username1 : username2;
            System.out.println("\n");
            System.out.println(currentPlayer + "'s turn:");
    
            switch (showMenu()) {
                case 1:
                    int diceRoll1 = rollDice1();
                    int diceRoll2 = rollDice2();
                    
                    System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
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
        addChecker(points[0], 2, "X");
        addChecker(points[5], 5, "O");
        addChecker(points[6], 5, "=");
        addChecker(points[8], 3, "O");
        addChecker(points[12], 5, "X");

        addChecker(points[13], 5, "O");
        addChecker(points[17], 3, "X");
        addChecker(points[19], 5, "=");
        addChecker(points[20], 5, "X");
        addChecker(points[25], 2, "O");
    }

    public static int showMenu() {
        System.out.println("\n1. Roll a dice");
        System.out.println("2. Quit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        return choice;
    }

    public static void display(){
        //
        int bottomMax=0, topMax=0;
        for(int i=0;i< 13; i++){
            bottomMax = Math.max(bottomMax, points[i].list.size());
        }
        for(int i=13;i< 26; i++){
            topMax = Math.max(topMax, points[i].list.size());
        }

        String[][] console1 = new String[topMax][13];
        String[][] console2 = new String[bottomMax][13];


        for(int i=0; i<topMax ;i++){
            for(int j=0; j<13; j++) {

                console2[i][j] = "|";
            
            }
        }

        for(int i=0; i<bottomMax ;i++){
            for(int j=0; j<13; j++) {
                console1[i][j] = "|";
            }
        }

        for(int i=12;i>-1;i--) {
            for (int j = 0; j < points[i].list.size(); j++) {

                    console1[j][12-i] = points[i].list.get(j);
                
            }
        }
        for(int i=13;i<26;i++) {
            for (int j = 0; j< points[i].list.size(); j++) {

                
                console2[j][i-13] = points[i].list.get(j);
            }
        }

        System.out.print("13");
        for(int i = 14; i<26;i++)
        {
            String st = "";
            if(i == 19)
            {
                st +="BAR";
                // System.out.print("B ");
            }
            else if( i >19)
            {
                st += (i-1);
                // System.out.print((i-1)+ " ");
            }
            else
            {
                st += i;
                // System.out.print(i+ " ");
            }
            
            String formatted = String.format("%"+ 4 +"s", st);
            System.out.print(formatted);
        }
        System.out.print(" OFF");
        System.out.println();
        System.out.println();

        for(int i=0;i<topMax;i++){
        for(int j=0;j<13;j++){
    

                if(console2[i][j].equals("X"))
                    System.out.print(getStyledString(console2[i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(console2[i][j]+"   ");
            }

            System.out.println("\n");
        }

        System.out.println();

        for(int i=0;i<bottomMax;i++){
            for(int j=0;j<13;j++){

                if(console1[bottomMax-1-i][j].equals("X"))
                    System.out.print(getStyledString(console1[bottomMax-1-i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(console1[bottomMax-1-i][j]+"   ");
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
                // System.out.print("B ");
            }
            else if(i < 6)
            {
                st += "0"+ (i+1); 
                // System.out.print((i+1)+ " ");
            }
            else
            {
                if(i <= 9)
                {
                   st += "0";
                }
                st += i;
                // System.out.print(i+ " ");
             
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