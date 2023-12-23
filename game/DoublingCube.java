package game;

public class DoublingCube {
    private static int doubleCount = 1;

    public static int getDoubleCount() {
        return doubleCount;
    }

    public static void setDoubleCount(int count) {
        doubleCount = count;
    }

    public static void runDouble(User user){
        System.out.println(user.getUsername()+", Choose if you want to accept or refuse double.");
        System.out.println("1. Accept");
        System.out.println("2. Refuse");
    }

    public static void doubleAccepted() {
        doubleCount *= 2;
    }

    // Method to display the current state of the doubling cube
    public static void display() {
        System.out.println("Doubling Cube Value : " + doubleCount);
    }
}
