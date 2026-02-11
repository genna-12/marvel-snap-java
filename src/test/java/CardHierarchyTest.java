import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.DebuffCard;

public class CardHierarchyTest {

    @Test
    public void testCardProperties() {
        // verifico le proprietà base di una carta
        Card c1 = new BasicCard(1, "Test", 2, 5, "Desc", "None");
        assertEquals(2, c1.getCost());
        assertEquals(5, c1.getPower());
        assertEquals("Test", c1.getName());
    }

    @Test
    public void testPolymorphism() {
        // verifica che i vari tipi di card siano istanze di Card 
        Card c2 = new BonusCard(2, "Bonus", 1, 1, "D", "A");
        assertTrue(c2 instanceof Card, "BonusCard deve estendere Card");
        
        Card c3 = new DebuffCard(3, "Debuff", 1, 1, "D", "A");
        assertTrue(c3 instanceof Card, "DebuffCard deve estendere Card");

        Card c4 = new BasicCard(4, "Basic", 1, 1, "D", "A");
        assertTrue(c4 instanceof Card, "BasicCard deve estendere Card");
    }
    
    @Test
    public void testSetPower() {
        Card c = new BasicCard(1, "Hulk", 6, 12, "Smash", "None");
       
        c.setPower(20);
        assertEquals(20, c.getPower(), "setPower deve aggiornare il valore");
        
        c.addPower(-5);
        assertEquals(15, c.getPower(), "addPower deve sommare correttamente (anche negativi)");
    }
}