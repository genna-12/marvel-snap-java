import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.Player;
import com.marvelsnap.model.WinCondition;

/**
 * Class to check if WinCondition correctly determines the winner.
 */
public class WinConditionTest {
    private List<Location> locs;
    private Player p1;
    private Player p2;

    /**
     * SetUp method called before each test.
     */
    @BeforeEach
    void setUp() {
        this.p1 = new Player("p1", new ArrayList<>());
        this.p2 = new Player("p2", new ArrayList<>());

        this.locs = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            this.locs.add(new NormalLocation("Loc" + i, "", 0, new ArrayList<>(List.of(0))));
        }
    }

    /**
     * Test to check if WinCondition determines the winner in a normal win situation.
     */
    @Test
    void testNormalWin() {
        /*P1 wins */
        this.locs.get(0).addCard(0, new BasicCard(0, "c1", 1, 10, "", ""));
        this.locs.get(1).addCard(0, new BasicCard(1, "c2", 1, 7, "", ""));

        final Player winner = WinCondition.determineWinner(locs, p1, p2);
        assertEquals(p1, winner);
    }

    /**
     * Test to check if WinCondition breaks the tie.
     */
    @Test
    void testTieBreaker() {
        /*Tie on first location */
        this.locs.get(0).addCard(0, new BasicCard(0, "c1", 1, 1, "", ""));
        this.locs.get(0).addCard(1, new BasicCard(1, "c2", 1, 1, "", ""));

        /*P1 wins second location */
        this.locs.get(1).addCard(0, new BasicCard(2, "c3", 1, 10, "", ""));

        /*P1 wins because he has a greater totalPower */
        final Player winner = WinCondition.determineWinner(locs, p1, p2);
        assertEquals(p1, winner);

    }

    /**
     * Test to check the absolute tie.
     */
    @Test
    void testAbsoluteTie() {
        /*We have an absolute tie */
        this.locs.get(0).addCard(0, new BasicCard(0, "c1", 1, 1, "", ""));
        this.locs.get(0).addCard(1, new BasicCard(1, "c2", 1, 1, "", ""));

        final Player winner = WinCondition.determineWinner(locs, p1, p2);
        assertNull(winner);
    }
}
