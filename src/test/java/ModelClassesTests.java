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
}