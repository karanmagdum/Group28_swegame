package test;
import game.DoublingCube;
import game.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DoublingCubeTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRunDouble() {
        User user = new User("TestUser");
        DoublingCube.runDouble(user);

        String expectedOutput = "TestUser, Choose if you want to accept or refuse double.\n" +
                "1. Accept\n" +
                "2. Refuse\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testDoubleAccepted() {
        DoublingCube.setDoubleCount(1);
        DoublingCube.doubleAccepted();
        assertEquals(2, DoublingCube.getDoubleCount());

        DoublingCube.doubleAccepted();
        assertEquals(4, DoublingCube.getDoubleCount());
    }

    @Test
    public void testDisplay() {
        DoublingCube.setDoubleCount(2);
        DoublingCube.display();

        String expectedOutput = "Doubling Cube Value : 2\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}