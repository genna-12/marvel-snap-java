import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;

/**
 * Class for game tests.
 */
public class GameTest {
    private Game game;

    /**
     * Setup method called before each test.
     */
    @BeforeEach
    void setUp() {
        this.game = new Game();

        this.game.startGame("p1", DeckType.AVENGERS, "p2", DeckType.VILLAINS);
    }

    /**
     * Test to check if second and third location are unrevealed at the beginning of the game.
     */
    @Test
    void testSecondAndThirdLocationAreUnrevealed() {
        /*At the very beginning of the game, only the first location is revealed. */
        assertEquals(true, this.game.getLocations().get(0).isRevealed());
        assertEquals(false, this.game.getLocations().get(1).isRevealed());
        assertEquals(false, this.game.getLocations().get(2).isRevealed());
    }

    /**
     * Test to check the correct flow of the game.
     */
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
        assertEquals(0, this.game.getTurnManager().getCurrentPlayerIndex());

        assertEquals(true, this.game.getLocations().get(1).isRevealed());

        game.endTurn(); /*P2 switch */
        game.endTurn(); /*End second turn */

        /*Third turn */
        assertEquals(3, this.game.getTurnManager().getCurrentTurn());

        assertEquals(true, this.game.getLocations().get(2).isRevealed());
    }
    
    /**
     * Test to check the correct revelation of the cards.
     */
    @Test
    void testCardRevealAfterTurnCycle() {
        this.game.getPlayer1().resetEnergy(10);
        this.game.getPlayer2().resetEnergy(10);
        final Card cardP1 = this.game.getPlayer1().getHand().getCards().getFirst();
        this.game.playCard(cardP1, 0);
        this.game.endTurn();

        assertFalse(cardP1.isRevealed());

        final Card cardP2 = this.game.getPlayer2().getHand().getCards().getFirst();
        this.game.playCard(cardP2, 2);
        assertFalse(cardP2.isRevealed());

        this.game.endTurn();

        assertTrue(cardP1.isRevealed());
        assertTrue(cardP2.isRevealed());
    }
}
