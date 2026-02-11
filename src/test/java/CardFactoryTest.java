import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.marvelsnap.model.Card;
import com.marvelsnap.util.CardFactory;
import com.marvelsnap.util.DeckType;
import java.util.List;

public class CardFactoryTest {

    @Test
    public void testCreateAvengersDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.AVENGERS);

        // test per dimensione mazzo
        assertNotNull(deck, "Il mazzo Avengers non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo Avengers deve avere 12 carte");

        // test per capire se ho il mazzo giusto (controllo presenza di una carta
        // chiave)
        boolean hasIronMan = deck.stream().anyMatch(c -> c.getName().equals("Iron Man"));
        assertTrue(hasIronMan, "Il mazzo Avengers deve contenere Iron Man");
    }

    @Test
    public void testCreateVillainsDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.VILLAINS);

        // verifico dimensione
        assertNotNull(deck, "Il mazzo Villains non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo Villains deve avere 12 carte");

        // verifico la carta chiave Thanos
        boolean hasThanos = deck.stream().anyMatch(c -> c.getName().equals("Thanos"));
        assertTrue(hasThanos, "Il mazzo Villains deve contenere Thanos");
    }

    @Test
    public void testCreateXmenDeck() {
        CardFactory factory = new CardFactory();
        List<Card> deck = factory.createDeck(DeckType.XMEN);

        // verifico dimensione
        assertNotNull(deck, "Il mazzo X-Men non deve essere null");
        assertEquals(12, deck.size(), "Il mazzo X-Men deve avere 12 carte");

        // verifico la carta chiave Wolverine
        boolean hasWolverine = deck.stream()
                .anyMatch(c -> c.getName().equals("Wolverine"));

        assertTrue(hasWolverine, "Il mazzo X-Men deve contenere Wolverine");
    }

    @Test
    public void testDecksAreDifferent() {
        CardFactory factory = new CardFactory();
        List<Card> avengers = factory.createDeck(DeckType.AVENGERS);
        List<Card> xmen = factory.createDeck(DeckType.XMEN);

        // controllo che i mazzi siano diversi
        assertNotEquals(avengers.get(0).getName(), xmen.get(0).getName(),
                "I mazzi dovrebbero essere mescolati e diversi");
    }
}