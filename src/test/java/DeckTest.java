import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import com.marvelsnap.model.Deck;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;

/**
 * Tests for the Deck class.
 * It verifies deck initialization and drawing mechanics.
 */
class DeckTest {

    private Deck deck;
    private List<Card> cardList;

    /**
     * Creates a list of cards and initializes the deck with it.
     */
    @BeforeEach
    void setUp() {
        cardList = new ArrayList<>();
        cardList.add(new BonusCard(1, "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale."));
        cardList.add(new BonusCard(2, "Ant-Man", 1, 1, "Non dirgli che è troppo piccolo",
                        "On Reveal: +3 Forza se questa location ha già 3 carte."));
        cardList.add(new BasicCard(3, "Hulk", 6, 12, "HULK SPACCA!", "Nessuna"));
        
        deck = new Deck(cardList);
    }

    /**
     * Checks if the deck is initialized with the correct number of cards.
     */
    @Test
    void testInitialSize() {
        int count = 0;
        while (deck.draw() != null) {
            count++;
        }
        assertEquals(3, count);
    }

    /**
     * Verifies that drawing a card removes it from the deck and returns it.
     */
    @Test
    void testDrawCard() {
        Card drawn = deck.draw();
        
        assertNotNull(drawn);
        assertTrue(cardList.contains(drawn));
    }

    /**
     * Verifies that drawing from an empty deck returns null.
     */
    @Test
    void testDrawFromEmptyDeck() {
        deck.draw();
        deck.draw();
        deck.draw();
        
        Card drawn = deck.draw();
        assertNull(drawn);
    }
}