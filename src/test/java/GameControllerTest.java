import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.view.IntermissionPanel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

/**
 * Class for GameController tests.
 */
public class GameControllerTest {
    private Game game;
    private GameController controller;
    private TestGamePanel testView = new TestGamePanel();

    /**
     * SetUp method called before each test.
     */
    @BeforeEach
    void setUp() {
        this.game = new Game();
        this.controller = new GameController(this.game, this.testView);

        this.game.startGame("p1", DeckType.AVENGERS, "p2", DeckType.VILLAINS);
    }

    /**
     * Test to check if controller tells the view to show the right panel.
     */
    @Test
    void testIntermissionFlow() {
        this.controller.onEndTurnClicked();
        assertTrue(this.testView.intermissionShown);

        this.controller.onIntermissionReadyClicked();

        assertTrue(this.testView.boardShown);
        assertFalse(this.game.isWaitingForSwap());
        assertTrue(this.testView.updateCalledAfterReady);
    }

    /**
     * Test to check if controller correctly select cards.
     */
    @Test
    void testCardSelection() {
        final Card card1 = this.game.getPlayer1().getHand().getCards().getFirst();
        assertFalse(card1.isSelected());

        /*Select card1 */
        this.controller.onCardClicked(card1);
        assertTrue(card1.isSelected());

        /*Deselect card1 */
        this.controller.onCardClicked(card1);
        assertFalse(card1.isSelected());

        final Card card2 = this.game.getPlayer1().getHand().getCards().getLast();

        /*Select card1 then change the selection and select card2 -> card1 = not selected, card2 = selected */
        this.controller.onCardClicked(card1);
        this.controller.onCardClicked(card2);

        assertFalse(card1.isSelected());
        assertTrue(card2.isSelected());
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
        public void updateView(final Game game) {
            if(this.boardShown) {
                this.updateCalledAfterReady = true;
            }
        }

        public IntermissionPanel getIntermissionPanel() {
            return this.testIntermission;
        }

        @Override
        public int showEndGame(final String winnerName) {
            return 0; 
        }
    }
}
