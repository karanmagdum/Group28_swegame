package game;

import org.junit.Before;
import org.junit.Test;
import game.Triangle.Checker;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("testUser");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", user.getUsername());
    }
    @Test
    public void testIsDoubleOwner() {
        assertTrue( user.isDoubleOwner());
    }

    @Test
    public void testIsDoubleOwnerSetToFalse() {
        user.setDoubleOwner(false);
        assertFalse( user.isDoubleOwner());
    }
}
