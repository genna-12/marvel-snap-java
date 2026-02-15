
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.view.IntermissionPanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.ReducedCostLocation;
import java.util.*;
import com.marvelsnap.model.BasicCard;

public class LocationTest {
    private Game game;
    private GameController controller;
    private TestGamePanel testView;

    @BeforeEach
    void setUp() {
        this.game = new Game();
        this.testView = new TestGamePanel();
        this.controller = new GameController(this.game, this.testView);

        this.game.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
    }

    /**
    * Tests the main getters and setters for NormalLocation. It also tests if the power increase works properly.
    */
    @Test
    void testNormalLocationProperties() {
        Location normalLocation = new NormalLocation("Nome", "Descrizione", 10, List.of(0, 1, 2, 3, 4, 5, 6));

        assertEquals("", normalLocation.getName());
        assertEquals("Questa location non è stata ancora rivelata", normalLocation.getDescription());
        assertFalse(normalLocation.isRevealed());
        assertEquals(new ArrayList<Card>(), normalLocation.getCards(0));
        assertEquals(0, normalLocation.calculatePower(0));
        assertFalse(normalLocation.isFull(0));

        normalLocation.addCard(0, new BasicCard(1, "Test", 1, 0, "Descrizione", "Nessuna"));
        assertEquals(1, normalLocation.getCards(0).size());

        normalLocation.revealLocation(this.game);
        assertTrue(normalLocation.isRevealed());
        assertEquals("Nome", normalLocation.getName());
        assertEquals("Descrizione", normalLocation.getDescription());
        assertEquals(10, normalLocation.getCards(0).getFirst().getPower());
        for (int i = 0; i < 3; i++) {
            normalLocation.addCard(0, new BasicCard(1, "Test", 1, 0, "Descrizione", "Nessuna"));
        }
        assertTrue(normalLocation.isFull(0));
    }
    
    /**
     * Tests whether the cost reduction of ReducedCostLocation works properly and if the cost boundaries
     * of cards targeted by the effect are respected.
     */
    @Test
    void testReducedCostLocationProperties() {
        Location reducedCostLocation = new ReducedCostLocation("Nome", "Descrizione", 1, List.of(1));
        this.game.getPlayer1().getHand().add(new BasicCard(2, "Test", 1, 0, "Descrizione", "Nessuna"));
        this.game.getPlayer1().getHand().add(new BasicCard(3, "Test", 6, 0, "Descrizione", "Nessuna"));

        reducedCostLocation.revealLocation(this.game);

        int handSize = this.game.getPlayer1().getHand().getCards().size();
        assertEquals(0, this.game.getPlayer1().getHand().getCards().get(handSize - 2).getCost());
        assertEquals(6, this.game.getPlayer1().getHand().getCards().get(handSize - 1).getCost()); // verifies that 
        // the 6 cost card isn't afflicted by the cost reduction
    }

    /**
     * Tests whether different location types are all instances of the class Location.
     */
    @Test
    void testLocationPolymorphism () {
        Location testNormalLocation = new NormalLocation("Nome", "Descrizione", 0, List.of(0));
        assertTrue(testNormalLocation instanceof Location);
        Location testReducedLocation = new ReducedCostLocation("Nome", "Descrizione", 0, List.of(0));
        assertTrue(testReducedLocation instanceof Location);
    }
    
    /**
     * Utility class for tests. It simulates a GamePanel.
     */
    private static class TestGamePanel extends GamePanel {
        public boolean intermissionShown = false;
        public boolean boardShown = false;
        public boolean updateCalledAfterReady = false;
        private IntermissionPanel testIntermission = new IntermissionPanel();

        @Override
        public void showIntermission() {
            this.intermissionShown = true;
        }

        @Override
        public void showBoard() {
            this.boardShown = true;
        }

        @Override
        public void updateView(Game game) {
            if(this.boardShown) {
                this.updateCalledAfterReady = true;
            }
        }

        public IntermissionPanel getIntermissionPanel() {
            return this.testIntermission;
        }

        @Override
        public int showEndGame(String winnerName) {
            return 0; 
        }
    }
}