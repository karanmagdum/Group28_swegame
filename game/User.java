package game;


/**
 * Represents a player in the game. This class maintains information about a user such as username, 
 * score, double ownership status, and winner status.
 */
public class User {

    private String username;
    private boolean doubleowner = true;

    private boolean isWinner = false;
    private int score;

    /**
     * Gets the user's current score.
     *
     * @return The current score of the user.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds the specified score to the user's current score.
     *
     * @param score The score to be added.
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Constructs a new User with the specified username.
     * Initializes the score to 0.
     *
     * @param name The username of the user.
     */

    public User(String name) { this.username = name; this.score=0; }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Checks if the user is the current owner of the double.
     *
     * @return true if the user is the double owner, false otherwise.
     */
    public boolean isDoubleOwner()
    {
        return doubleowner;
    }

    /**
     * Sets the user's status as the double owner.
     *
     * @param flag The double owner status to set.
     */
    public void setDoubleOwner(boolean flag)
    {
        doubleowner = flag;
    }

    /**
     * Checks if the user has won the game.
     *
     * @return true if the user is the winner, false otherwise.
     */
    public boolean isWinner()
    {
        return isWinner;
    }

    /**
     * Marks the user as the winner of the game.
     */
    public void setWinner() {
        isWinner = true;
    }

}
