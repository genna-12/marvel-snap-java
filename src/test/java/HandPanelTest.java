import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.marvelsnap.model.Hand;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.view.CardPanel;
import com.marvelsnap.view.HandPanel;

/**
 * Tests for the HandPanel view class.
 */
class HandPanelTest {

    private HandPanel handPanel;
    private Hand hand;

    /**
     * Sets up the panel and a hand before each test.
     */
    @BeforeEach
    void setUp() {
        handPanel = new HandPanel();
        hand = new Hand();
        
        hand.add(new BonusCard(1, "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale."));
        hand.add(new BonusCard(2, "Ant-Man", 1, 1, "Non dirgli che è troppo piccolo",
                        "On Reveal: +3 Forza se questa location ha già 3 carte."));
        hand.add(new BasicCard(3, "Hulk", 6, 12, "HULK SPACCA!", "Nessuna"));
    }

    /**
     * Test if the panel correctly creates a CardPanel for each card in the hand.
     */
    @Test
    void testNormalSetHand() {
        handPanel.setHand(hand);

        int componentCount = handPanel.getComponentCount();
        assertEquals(3, componentCount);
    }

    /**
     * Tests that the panel clears old cards before adding new ones.
     */
    @Test
    void testClearOldCards() {
        handPanel.setHand(hand);
        
        Hand newHand = new Hand();
        newHand.add(new BasicCard(3, "Hulk", 6, 12, "HULK SPACCA!", "Nessuna"));
        handPanel.setHand(newHand);
    
        assertEquals(1, handPanel.getComponentCount());
    }

    /**
     * Tests if handpanel has no component with a null hand.
     */
    @Test
    void testSetHandWithNull() {
        handPanel.setHand(null);
        
        assertEquals(0, handPanel.getComponentCount());
    }

    /**
     * Tests if the card click works when it is in hand.
     */
    @Test
    void testClickInHand() {
        handPanel.setHand(hand);
        
        CardPanel cp = (CardPanel) handPanel.getComponent(0);
        
        assertFalse(cp.isSelected());
        cp.mouseClicked(null);
        assertTrue(cp.isSelected());
    }
}