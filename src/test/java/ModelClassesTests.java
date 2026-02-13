import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.view.IntermissionPanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Player;
import com.marvelsnap.model.WinCondition;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.ReducedCostLocation;
import java.util.*;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.util.LocationFactory;

/**
 * Class for main tests.
 */
public class ModelClassesTests {
    
    private Game game;
    private GameController controller;
    private TestGamePanel testView;

    /**
     * Setup method called before every test. 
     */
    @BeforeEach
    void setUp() {
        this.game = new Game();
        this.testView = new TestGamePanel();
        this.controller = new GameController(this.game, this.testView);

        this.game.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
    }

    /** 
     * Test Game & TurnManager. 
     */
    @Test
    void testSecondAndThirdLocationAreUnrevealed() {
        /*At the very beginning of the game, only the first location is revealed. */
        assertEquals(true, this.game.getLocations().get(0).isRevealed());
        assertEquals(false, this.game.getLocations().get(1).isRevealed());
        assertEquals(false, this.game.getLocations().get(2).isRevealed());
    }

    @Test
    void testEnergyAndTurnFlow() {
        /*First turn */
        assertEquals(0, this.game.getTurnManager().getCurrentPlayerIndex());
        
        assertEquals(true, this.game.getLocations().get(0).isRevealed());

        game.endTurn(); /*P2 switch */
        assertEquals(1, this.game.getTurnManager().getCurrentPlayerIndex());
        
        game.endTurn(); /*Next turn */

        /*Second turn */
        assertEquals(2, this.game.getTurnManager().getTurnNumber());

        assertEquals(true, this.game.getLocations().get(1).isRevealed());

        game.endTurn(); /*P2 switch */
        game.endTurn(); /*End second turn */

        /*Third turn */
        assertEquals(3, this.game.getTurnManager().getCurrentTurn());

        assertEquals(true, this.game.getLocations().get(2).isRevealed());
    }
    
    @Test
    void testCardRevealAfterTurnCycle() {
        this.game.getPlayer1().resetEnergy(10);
        this.game.getPlayer2().resetEnergy(10);
        Card cardP1 = this.game.getPlayer1().getHand().getCards().getFirst();
        this.game.playCard(cardP1, 0);
        this.game.endTurn();

        assertFalse(cardP1.isRevealed());

        Card cardP2 = this.game.getPlayer2().getHand().getCards().getFirst();
        this.game.playCard(cardP2, 2);
        assertFalse(cardP2.isRevealed());

        this.game.endTurn();

        assertTrue(cardP1.isRevealed());
        assertTrue(cardP2.isRevealed());
    }

    /**
     * Test InputState & View Interaction. It checks if controller tells the view to show the right panel.
     */
    @Test
    void testIntermissionFlow() {
        this.controller.onEndTurnClicked();
        assertTrue(this.testView.intermissionShown);

        this.controller.onIntermissionReadyClicked();

        assertTrue(this.testView.boardShown);
        assertFalse(this.game.isWaitingForSwap());
        assertTrue(testView.updateCalledAfterReady);
    }

    /**
     * Test WinCondition tiebreaker management. Since the game just started, it should be a tie.
     */
    @Test
    void testWinConditionTieBreaker() {
        Player winner = WinCondition.determineWinner(game.getLocations(), game.getPlayer1(), game.getPlayer2());
        assertNull(winner);
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
    
    @Test
    void testReducedCostLocationProperties() {
        Location reducedCostLocation = new ReducedCostLocation("Nome", "Descrizione", 1, List.of(1));
        this.game.getPlayer1().getHand().add(new BasicCard(2, "Test", 1, 0, "Descrizione", "Nessuna"));
        this.game.getPlayer1().getHand().add(new BasicCard(3, "Test", 6, 0, "Descrizione", "Nessuna"));

        reducedCostLocation.revealLocation(this.game);

        int handSize = this.game.getPlayer1().getHand().getCards().size();
        assertEquals(0, this.game.getPlayer1().getHand().getCards().get(handSize - 2).getCost()); // verifica che la riduzione di costo sia avvenuta
        assertEquals(6, this.game.getPlayer1().getHand().getCards().get(handSize - 1).getCost()); // verifica che la modifica di costo non riguardi
        // la carta di costo 6, ma solo quella di costo 1
    }

    @Test
    void testLocationPolymorphism () {
        Location testNormalLocation = new NormalLocation("Nome", "Descrizione", 0, List.of(0));
        assertTrue(testNormalLocation instanceof Location);
        Location testReducedLocation = new ReducedCostLocation("Nome", "Descrizione", 0, List.of(0));
        assertTrue(testReducedLocation instanceof Location);
    }

    @Test
    void testLocationFactory() {
        LocationFactory locationFactory = new LocationFactory();
        List<Location> locations = new ArrayList<>(locationFactory.createLocations());
        assertEquals(3, locations.size());
        assertNotEquals(locations.get(0), locations.get(1));
        assertNotEquals(locations.get(0), locations.get(2));
        assertNotEquals(locations.get(1), locations.get(2));
    }
}


