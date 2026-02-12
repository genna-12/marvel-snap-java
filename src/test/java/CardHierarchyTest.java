import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.DebuffCard;

/**
 * This class tests the card hierarchy to make sure inheritance and polymorphism
 * work.
 * Like, checking if different card types are instances of Card and if methods
 * work correctly.
 */
public class CardHierarchyTest {

    @Test
    public void testCardProperties() {
        Card c1 = new BasicCard(1, "Test", 2, 5, "Desc", "None");
        assertEquals(2, c1.getCost());
        assertEquals(5, c1.getPower());
        assertEquals("Test", c1.getName());
    }

    /**
     * This test checks if different card types are instances of Card.
     * Like, verifying that BonusCard, DebuffCard, and BasicCard all extend Card.
     */
    @Test
    public void testPolymorphism() {
        Card c2 = new BonusCard(2, "Bonus", 1, 1, "D", "A");
        assertTrue(c2 instanceof Card, "BonusCard deve estendere Card");

        Card c3 = new DebuffCard(3, "Debuff", 1, 1, "D", "A");
        assertTrue(c3 instanceof Card, "DebuffCard deve estendere Card");

        Card c4 = new BasicCard(4, "Basic", 1, 1, "D", "A");
        assertTrue(c4 instanceof Card, "BasicCard deve estendere Card");
    }

    /**
     * This test checks if the setPower and addPower methods work correctly.
     * Like, setting power to 20 and then adding -5 should result in 15.
     */
    @Test
    public void testSetPower() {
        Card c = new BasicCard(1, "Hulk", 6, 12, "Smash", "None");

        c.setPower(20);
        assertEquals(20, c.getPower(), "setPower deve aggiornare il valore");

        c.addPower(-5);
        assertEquals(15, c.getPower(), "addPower deve sommare correttamente (anche negativi)");
    }
}