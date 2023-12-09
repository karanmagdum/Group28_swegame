package game;

public class Board {
    public static void addChecker(Point point, int count, Point.Checker checker){
        for(int i=1;i<=count;i++){
            point.add(checker);
        }
    }
    public static void assignCheckers(Point[] points){
        for(int i=0;i<points.length;i++){
            points[i] = new Point();
        }
        //Player 1
        addChecker(points[1], 2, Point.Checker.X);
        addChecker(points[12], 5, Point.Checker.X);
        addChecker(points[17], 3, Point.Checker.X);
        addChecker(points[19], 5, Point.Checker.X);
        //Player 2
        addChecker(points[8], 3, Point.Checker.O);
        addChecker(points[6], 5, Point.Checker.O);
        addChecker(points[13], 5, Point.Checker.O);
        addChecker(points[24], 2, Point.Checker.O);
    }

    //Display - refactor this
    public static void display(Point[] points){
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
                    System.out.print(Game.getStyledString(upperSection[i][j],"\u001B[31m")+"   ");
                else
                    System.out.print(upperSection[i][j]+"   ");
            }
            System.out.println("\n");
        }

        System.out.println();

        for(int i=0;i<bottomMax;i++){
            for(int j=0;j<13;j++){
                if(lowerSection[bottomMax-1-i][j].equals("X"))
                    System.out.print(Game.getStyledString(lowerSection[bottomMax-1-i][j],"\u001B[31m")+"   ");
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

    public static int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }
}
