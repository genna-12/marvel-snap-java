import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Player;
import com.marvelsnap.model.WinCondition;
public class Tests {
    
    private Game game;
    private GameController controller;
    private TestGamePanel testView;

    @BeforeEach
    void setUp() {
        game = new Game();
        testView = new TestGamePanel();
        controller = new GameController(game, testView);

        game.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
    }

    /*Test Game & TurnManager*/

    @Test
    void testEnergyAndTurnFlow() {
        assertEquals(1, game.getTurnManager().getEnergyForTurn());
        assertEquals(1, game.getPlayer1().getCurrentEnergy());

        game.endTurn(); // Switch a P2
        game.endTurn();

        assertEquals(2, game.getTurnManager().getTurnNumber());
        assertEquals(2, game.getPlayer1().getCurrentEnergy(), "L'energia deve essere 2 al turno 2");
    }

    /*Test InputState & View Interaction*/

    @Test
    void testIntermissionFlow() {
        
        controller.onEndTurnClicked();

        
        assertTrue(testView.intermissionCalled);

        
        controller.onIntermissionReadyClicked();

        assertTrue(testView.boardCalled);
        assertTrue(testView.updateCalledAfterReady);
    }

    @Test
    void testWinConditionTieBreaker() {
        Player winner = WinCondition.determineWinner(game.getLocations(), game.getPlayer1(), game.getPlayer2());
        assertNull(winner);
    }
    
    /*Utility class for tests */
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
            // Se la board è attiva, segnamo che l'update è avvenuto
            if (!intermissionCalled || boardCalled) {
                updateCalledAfterReady = true;
            }
        }

        @Override
        public void showEndGame(String winnerName) {
        }
    }
}