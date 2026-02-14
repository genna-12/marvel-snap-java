import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;
import com.marvelsnap.model.BonusCard;

/**
 * Tests for the class Hand.
 */
class HandTest {

    private Hand hand;
    private Card card1;
    private Card card2;

    /**
     * Initializes the hand and two cards.
     */
    @BeforeEach
    void setUp() {
        hand = new Hand();
        card1 = new BonusCard(1, "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale.");
        card2 = new BonusCard(2, "Ant-Man", 1, 1, "Non dirgli che è troppo piccolo",
                        "On Reveal: +3 Forza se questa location ha già 3 carte.");
    }

    /**
     * Checks the addition of a card to the hand.
     */
    @Test
    void testAdd() {
        hand.add(card1);
        assertEquals(1, hand.getCards().size());
        assertTrue(hand.getCards().contains(card1));
        hand.add(card2);
        assertEquals(2, hand.getCards().size());
        assertTrue(hand.getCards().contains(card2));
    }

    /**
     * Checks the removal of a card from the hand.
     */
    @Test
    void testRemove() {
        hand.add(card1);
        hand.add(card2);
        
        hand.remove(card1);
        
        assertEquals(1, hand.getCards().size());
        assertFalse(hand.getCards().contains(card1));
        assertTrue(hand.getCards().contains(card2));
    }
}