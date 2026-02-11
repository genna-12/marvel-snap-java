import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.view.SetupPanel;

public class SetupPanelTest {

    @Test
    public void testDefaultValues() {
        SetupPanel panel = new SetupPanel();

        // controlla che i campi di testo dei nomi siano vuoti
        assertEquals("", panel.getP1Name(), "Il nome P1 dovrebbe essere vuoto all'avvio");
        assertEquals("", panel.getP2Name(), "Il nome P2 dovrebbe essere vuoto all'avvio");
    }

    @Test
    public void testUserEntry() {
        SetupPanel panel = new SetupPanel();

        // simulo l'inserimento dei nomi dei giocatori
        panel.setP1Name("Mario");
        panel.setP2Name("Luigi");

        // verifico che siano quelli che ho inserito
        assertEquals("Mario", panel.getP1Name());
        assertEquals("Luigi", panel.getP2Name());
    }
}