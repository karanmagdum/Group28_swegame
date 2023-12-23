package game;

public class User {

    private String username;
    private boolean doubleowner = true;

    private boolean isWinner = false;
    private int score;

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public User(String name) { this.username = name; this.score=0; }
    public String getUsername()
    {
        return username;
    }

    public boolean isDoubleOwner()
    {
        return doubleowner;
    }

    public void setDoubleOwner(boolean flag)
    {
        doubleowner = flag;
    }

    public boolean isWinner()
    {
        return isWinner;
    }

    public void setWinner() {
        isWinner = true;
    }

}
