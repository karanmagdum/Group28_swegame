package game;

import game.Triangle.Checker;

/**
 * The Board class represents the game board in a Backgammon game.
 * It includes methods for adding checkers, assigning initial checkers to points,
 * and displaying the game board with status, moves, scores and pip counts.
 */
public class Board {

    /**
     * Adds a specified number of checkers to a given point on the board.
     *
     * @param point   The point to which checkers will be added.
     * @param count   The number of checkers to add.
     * @param checker The checker type to be added (X or O).
     */
    public void addChecker(Point point, int count, Checker checker){
        for(int i=1;i<=count;i++){
            point.add(checker);
        }
    }

    /**
     * Assigns initial checkers to the game board points.
     *
     * @param points The array of points representing the game board.
     */
    public void assignCheckers(Point[] points){
        for(int i=0;i<points.length;i++){
            points[i] = new Point();
        }
        //Player 1
        addChecker(points[1], 2, Checker.X);
        addChecker(points[12], 5, Checker.X);
        addChecker(points[17], 3, Checker.X);
        addChecker(points[19], 5, Checker.X);
        //Player 2
        addChecker(points[8], 3, Checker.O);
        addChecker(points[6], 5, Checker.O);
        addChecker(points[13], 5, Checker.O);
        addChecker(points[24], 2, Checker.O);
    }

    /**
     * Displays the game board with the current checkers' distribution.
     *
     * @param flag    A boolean flag representing the current player's turn.
     * @param points  The array of points representing the game board.
     * @param player1 The User object representing player 1.
     * @param player2 The User object representing player 2.
     */
    public void display(boolean flag, Point[] points, User player1, User player2){

        int bottomMax=0, topMax=0;

        for(int i=13;i<=25; i++){
            topMax = Math.max(topMax, points[i].getSize());
        }

        for(int i=0;i<=12; i++){
            bottomMax = Math.max(bottomMax, points[i].getSize());
        }

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
            for (int i = 0; i < points[j].getList().size(); i++) {
                lowerSection[i][12-j] = points[j].getList().get(i).toString();
            }
        }
        for(int j=6;j>=1;j--) {
            for (int i = 0; i < points[j].getList().size(); i++) {
                lowerSection[i][12-j+1] = points[j].getList().get(i).toString();
            }
        }
        for(int i=0;i< points[0].getSize();i++){
            lowerSection[i][6] = points[0].getList().get(i).toString();
        }


        for(int j=13;j<=18;j++) {
            for (int i = 0; i< points[j].getList().size(); i++) {
                upperSection[i][j-13] = points[j].getList().get(i).toString();
            }
        }
        for(int j=19;j<=24;j++) {
            for (int i = 0; i< points[j].getList().size(); i++) {
                upperSection[i][j-13+1] = points[j].getList().get(i).toString();
            }
        }
        for(int i=0;i< points[25].getSize();i++){
            upperSection[i][6] = points[25].getList().get(i).toString();
        }

        System.out.println("\t========================GAME=BOARD========================|=====SCORE=BOARD=====");

        System.out.print("\t\t13");
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

        System.out.print(" OFF|");
        System.out.println("\t"+player1.getUsername()+": "+player1.getScore()+" | "+player2.getUsername()+": "+player2.getScore());
        displayUpperPips(points, flag);

        System.out.println();

        for(int i=0;i<topMax;i++){
            System.out.print("\t\t");
            for(int j=0;j<13;j++){
                if(upperSection[i][j].equals("X"))
                    System.out.print(Game.getStyledString(upperSection[i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(upperSection[i][j]+"   ");
            }

            System.out.println();
        }

        System.out.println();

        for(int i=0;i<bottomMax;i++){
            System.out.print("\t\t");
            for(int j=0;j<13;j++){
                if(lowerSection[bottomMax-1-i][j].equals("X"))
                    System.out.print(Game.getStyledString(lowerSection[bottomMax-1-i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(lowerSection[bottomMax-1-i][j]+"   ");
            }

            System.out.println();
        }
        displayLowerPips(points, flag);
        System.out.println();
        System.out.print("\t\t12");

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

        System.out.print(" OFF|");
        System.out.println();
    }

    /**
     * Displays the pip count for a specific player.
     *
     * @param player1Name The name of player 1.
     * @param player2Name The name of player 2.
     * @param player1Pip  The pip count for player 1.
     * @param player2Pip  The pip count for player 2.
     */
    public static void displayPip(String player1Name, String player2Name, int player1Pip, int player2Pip){
        System.out.println("Pip count for "+player1Name+" = "+player1Pip);
        System.out.println("Pip count for "+player2Name+" = "+player2Pip);
        System.out.println("\n");
    }

    /**
     * Simulates the rolling of a six-sided die and returns the result.
     *
     * @return A random integer between 1 and 6, inclusive.
     */
    public static int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }
    /**
     * Displays the pip counts for the upper section of the board.
     *
     * @param points The array of points representing the game board.
     * @param flag   A boolean flag representing the current player's turn.
     */
     public static void displayUpperPips(Point[] points, boolean flag){
         int[] pipCounts = PipCount.countIndividualPip(points, flag);
         System.out.print("\tPIP ");
         if(pipCounts[13]>0)
             System.out.print(pipCounts[13]);
         else
             System.out.print(" ");

         for(int i=14;i<=18;i++){
             System.out.print(String.format("%"+ 4 +"s", pipCounts[i]>0?pipCounts[i]:" "));
         }
         if(pipCounts[13]>0)
            System.out.print(String.format("%"+ 3 +"s","="));
         else
            System.out.print(String.format("%"+ 4 +"s","="));


         for(int i=19;i<=24;i++){
             System.out.print(String.format("%"+ 4 +"s", pipCounts[i]>0?pipCounts[i]:" "));
         }
     }

    /**
     * Displays the pip counts for the upper section of the board.
     *
     * @param points The array of points representing the game board.
     * @param flag   A boolean flag representing the current player's turn.
     */
     public static void displayLowerPips(Point[] points, boolean flag){
         int[] pipCounts = PipCount.countIndividualPip(points, flag);
         System.out.print("\tPIP ");
         if(pipCounts[12]>0)
             System.out.print(pipCounts[12]);
         else
             System.out.print(" ");

         for(int i=11;i>=7;i--){
             System.out.print(String.format("%"+ 4 +"s", pipCounts[i]>0?pipCounts[i]:" "));
         }
         if(pipCounts[12]>0)
             System.out.print(String.format("%"+ 3 +"s","="));
         else
             System.out.print(String.format("%"+ 4 +"s","="));

         for(int i=6;i>=1;i--) {
             System.out.print(String.format("%" + 4 + "s", pipCounts[i]>0?pipCounts[i]:" "));
         }

    }

}

