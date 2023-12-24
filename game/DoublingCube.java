package game;

/**
 * The DoublingCube class represents the doubling cube in a Backgammon game.
 * It includes methods for getting and setting the current doubling cube value,
 * running the doubling process, and displaying the current state of the doubling cube.
 */
public class DoublingCube {

    /**
     * The current value of the doubling cube.
     */
    private static int doubleCount = 1;

    /**
     * Gets the current value of the doubling cube.
     *
     * @return The current value of the doubling cube.
     */
    public static int getDoubleCount() {
        return doubleCount;
    }

    /**
     * Sets the value of the doubling cube to the specified count.
     *
     * @param count The new value to set for the doubling cube.
     */
    public static void setDoubleCount(int count) {
        doubleCount = count;
    }

    /**
     * Initiates the doubling process by prompting the user to accept or refuse the double.
     *
     * @param user The User object representing the player who initiated the double.
     */
    public static void runDouble(User user){
        System.out.println(user.getUsername()+", Choose if you want to accept or refuse double.");
        System.out.println("1. Accept");
        System.out.println("2. Refuse");
    }

    /**
     * Updates the doubling cube value when the double is accepted.
     */
    public static void doubleAccepted() {
        doubleCount *= 2;
    }

    /**
     * Displays the current state of the doubling cube, including its current value.
     */
    public static void display() {
        System.out.println("Doubling Cube Value : " + doubleCount);
    }
}
