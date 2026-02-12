import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.view.SetupPanel;

/**
 * This class tests the SetupPanel to make sure player names can be set and stuff.
 * Like, checking if names start empty and if we can put names in.
 */
public class SetupPanelTest {

    /**
     * This test checks if the player name fields start empty when the panel is created.
     * Like, making sure P1 and P2 names are blank at the beginning.
     */
    @Test
    public void testDefaultValues() {
        SetupPanel panel = new SetupPanel();

        assertEquals("", panel.getP1Name(), "Il nome P1 dovrebbe essere vuoto all'avvio");
        assertEquals("", panel.getP2Name(), "Il nome P2 dovrebbe essere vuoto all'avvio");
    }

    /**
     * This test simulates entering player names and checks if they get set correctly.
     * Like, setting P1 to Mario and P2 to Luigi, then verifying they match.
     */
    @Test
    public void testUserEntry() {
        SetupPanel panel = new SetupPanel();

        panel.setP1Name("Mario");
        panel.setP2Name("Luigi");

        assertEquals("Mario", panel.getP1Name());
        assertEquals("Luigi", panel.getP2Name());
    }
}