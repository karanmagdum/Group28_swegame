package game;


public class Game{

    static Point[] points = new Point[24];

    public static void  main(String[] args){
        System.out.println("Inside Game");
        assignCheckers();
        display();
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
        addChecker(points[7], 3, "O");
        addChecker(points[11], 5, "X");

        addChecker(points[12], 5, "O");
        addChecker(points[16], 3, "X");
        addChecker(points[18], 5, "X");
        addChecker(points[23], 2, "O");
    }

    public static void display(){
        //
        int bottomMax=0, topMax=0;
        for(int i=0;i< 12; i++){
            bottomMax = Math.max(bottomMax, points[i].list.size());
        }
        for(int i=12;i< 24; i++){
            topMax = Math.max(topMax, points[i].list.size());
        }

        String[][] console1 = new String[topMax][12];
        String[][] console2 = new String[bottomMax][12];


        for(int i=0; i<topMax ;i++){
            for(int j=0; j<12; j++) {
                console2[i][j] = "|";
            }
        }
        for(int i=0; i<bottomMax ;i++){
            for(int j=0; j<12; j++) {
                console1[i][j] = "|";
            }
        }

        for(int i=11;i>-1;i--) {
            for (int j = 0; j < points[i].list.size(); j++) {
                console1[j][11-i] = points[i].list.get(j);
            }
        }
        for(int i=12;i<24;i++) {
            for (int j = 0; j< points[i].list.size(); j++) {
                console2[j][i-12] = points[i].list.get(j);
            }
        }

        for(int i=0;i<topMax;i++){
        for(int j=0;j<12;j++){
                if(console2[i][j].equals("X"))
                    System.out.print(getStyledString(console2[i][j],"\u001B[31m")+"  ");
                else
                    System.out.print(console2[i][j]+"  ");
            }
            System.out.println("\n");
        }

        System.out.println();

        for(int i=0;i<bottomMax;i++){
            for(int j=0;j<12;j++){
                if(console1[bottomMax-1-i][j].equals("X"))
                    System.out.print(getStyledString(console1[bottomMax-1-i][j],"\u001B[31m")+" ");
                else
                    System.out.print(console1[bottomMax-1-i][j]+"  ");
            }
            System.out.println("\n");
        }


    }
}