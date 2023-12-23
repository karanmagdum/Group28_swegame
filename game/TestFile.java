package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestFile {

    public enum Action {
        ROLL,
        QUIT,
        END,
        PIP
    }

    public boolean readTestFile(Point[]  points, User player1, User player2) {
        String csvFilePath = "game\\Test.csv"; // Replace with the actual path to your CSV file

        Path path = Paths.get(csvFilePath);

        boolean end_flag = false;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            if (line != null) {
                List<String> actions = Arrays.asList(line.split(","));
                for (String actionString : actions) {
                    Action action = parseAction(actionString);
                    if (action != null && !end_flag) {
                        System.out.println("Read action from CSV: " + action);
                        // Perform actions based on the enum values
                        switch (action) {
                            case ROLL:
                                // Handle ROLL action
                                // Dice dice = new Dice();

                                int diceRoll1 = Board.rollDice();
                                int diceRoll2 = Board.rollDice();

                                System.out.println("\nRolled Dice values are: "+ diceRoll1 +"," + diceRoll2);
                                System.out.println();
                                break;
                            case QUIT:
                                // Handle QUIT action
                                return true;
                            case END:
                                // Handle END action
                                end_flag = true;
                                break;
                            case PIP:
                                // Handle PIP action
                                PipCount pip = new PipCount();
                                int[] pipCount = pip.countTotalPip(points);
                                System.out.println("Pip count for "+player1.getUsername()+" = "+pipCount[0]);
                                System.out.println("Pip count for "+player2.getUsername()+" = "+pipCount[1]);
                                System.out.println("=========================================================");
                                break;
                        }
                    } else {
                        System.out.println("Invalid action in CSV: " + actionString);
                    }

                    if(end_flag)
                        break;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Action parseAction(String actionString) {
        try {
            return Action.valueOf(actionString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Enum constant not found
            return null;
        }
    }
}



