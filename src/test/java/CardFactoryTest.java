import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.model.Card;
import com.marvelsnap.util.CardFactory;
import com.marvelsnap.util.DeckType;
import java.util.List;

/**
 * This class tests the CardFactory to make sure it creates decks correctly for different types.
 * Like, checking if the decks have the right size and contain the expected cards.
 */
public class CardFactoryTest {

    /**
     * This test checks if creating an Avengers deck works properly.
     * Like, making sure it's not null, has 12 cards, and includes Iron Man.
     */
    @Test
    public void testCreateAvengersDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.AVENGERS);

        assertNotNull(deck, "Il mazzo Avengers non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo Avengers deve avere 12 carte");

        boolean hasIronMan = deck.stream().anyMatch(c -> c.getName().equals("Iron Man"));
        assertTrue(hasIronMan, "Il mazzo Avengers deve contenere Iron Man");
    }

    /**
     * This test checks if creating a Villains deck works properly.
     * Like, making sure it's not null, has 12 cards, and includes Thanos.
     */
    @Test
    public void testCreateVillainsDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.VILLAINS);

        assertNotNull(deck, "Il mazzo Villains non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo Villains deve avere 12 carte");

        boolean hasThanos = deck.stream().anyMatch(c -> c.getName().equals("Thanos"));
        assertTrue(hasThanos, "Il mazzo Villains deve contenere Thanos");
    }

    /**
     * This test checks if creating an XMen deck works properly.
     * Like, making sure it's not null, has 12 cards, and includes Wolverine.
     */
    @Test
    public void testCreateXmenDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.XMEN);

        assertNotNull(deck, "Il mazzo X-Men non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo X-Men deve avere 12 carte");

        boolean hasWolverine = deck.stream()
                .anyMatch(c -> c.getName().equals("Wolverine"));

        assertTrue(hasWolverine, "Il mazzo X-Men deve contenere Wolverine");
    }

    /**
     * This test checks if different deck types create different decks.
     * Like, making sure Avengers and X-Men decks aren't the same.
     */
    @Test
    public void testDecksAreDifferent() {
        CardFactory factory = new CardFactory();
        List<Card> avengers = factory.createDeck(DeckType.AVENGERS);
        List<Card> xmen = factory.createDeck(DeckType.XMEN);

        assertNotEquals(avengers.get(0).getName(), xmen.get(0).getName(),
                "I mazzi dovrebbero essere mescolati e diversi");
    }
}