import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Player;
import com.marvelsnap.model.WinCondition;

/**
 * Class for main tests.
 */
public class Tests {
    
    private Game game;
    private GameController controller;
    private TestGamePanel testView;

    /**
     * Setup method called before every test. 
     */
    @BeforeEach
    void setUp() {
        game = new Game();
        testView = new TestGamePanel();
        controller = new GameController(game, testView);

        game.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
    }

    /** 
     * Test Game & TurnManager. 
     */
    @Test
    void testEnergyAndTurnFlow() {
        assertEquals(1, game.getTurnManager().getEnergyForTurn());
        assertEquals(1, game.getPlayer1().getCurrentEnergy());

        game.endTurn(); /*P2 switch */
        game.endTurn(); /*Next turn */

        assertEquals(2, game.getTurnManager().getTurnNumber());
        assertEquals(2, game.getPlayer1().getCurrentEnergy());
    }

    /**
     * Test InputState & View Interaction. It checks if controller tells the view to show the right panel.
     */
    @Test
    void testIntermissionFlow() {
        
        controller.onEndTurnClicked();

        
        assertTrue(testView.intermissionCalled);

        
        controller.onIntermissionReadyClicked();

        assertTrue(testView.boardCalled);
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
        boolean intermissionCalled = false;
        boolean boardCalled = false;
        boolean updateCalledAfterReady = false;

        @Override
        public void showIntermission() {
            this.intermissionCalled = true;
        }

        @Override
        public void showBoard() {
            this.boardCalled = true;
        }

        @Override
        public void updateView(Game game) {
            if (!intermissionCalled || boardCalled) {
                updateCalledAfterReady = true;
            }
        }

        @Override
        public void showEndGame(String winnerName) {
        }
    }
}