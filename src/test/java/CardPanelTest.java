import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.view.CardPanel;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Test for the CardPanel class.
 * Checks if the card data is correctly displayed and if the selection feature works.
 */
class CardPanelTest {

    private CardPanel cardPanel;
    private Card card1;
    private Card card2;

    /**
     * Sets up a card panel.
     */
    @BeforeEach
    void setUp() {
        cardPanel = new CardPanel();
        card1 = new BasicCard(3, "Hulk", 6, 12, "HULK SPACCA!", "Nessuna");
        card2 = new BonusCard(1, "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale.");

    }

    /**
     * Test for removeAll() inside setCard()
     */
    @Test
    void testSetCardUpdatesCorrectly() {
        cardPanel.setCard(card1);
        assertEquals(2, cardPanel.getComponentCount());

        cardPanel.setCard(card2);
        assertEquals(2, cardPanel.getComponentCount());
        assertEquals("Iron Man", cardPanel.getCard().getName());
    }

    /**
     * Test if the card is correctly shown in the panel, respecting the assigned layout.
     */
    @Test
    void testSetCard() {
        cardPanel.setCard(card1);
         assertEquals(card1, cardPanel.getCard());

        BorderLayout layout = (BorderLayout) cardPanel.getLayout();
        JPanel header = (JPanel) layout.getLayoutComponent(BorderLayout.NORTH);
        BorderLayout headerLayout = (BorderLayout) header.getLayout();
        JLabel costLabel = (JLabel) headerLayout.getLayoutComponent(BorderLayout.WEST);
        JLabel powerLabel = (JLabel) headerLayout.getLayoutComponent(BorderLayout.EAST);
        JLabel nameLabel = (JLabel) layout.getLayoutComponent(BorderLayout.CENTER);
        
        assertTrue(costLabel.getText().contains(String.valueOf(card1.getCost())));
        assertTrue(powerLabel.getText().contains(String.valueOf(card1.getPower())));
        assertTrue(nameLabel.getText().contains(card1.getName()));

        String tooltip = cardPanel.getToolTipText();
        assertTrue(tooltip.contains(card1.getName()));
        assertTrue(tooltip.contains(card1.getDescription()));
    }

    /**
     * Test the selection feature.
     */
    @Test
    void testToggleSelection() {
        assertFalse(cardPanel.isSelected());
        
        cardPanel.toggleSelection();
        
        assertTrue(cardPanel.isSelected());
        assertEquals(new Color(180, 255, 180), cardPanel.getBackground());
        assertEquals(new Color(180, 255, 180), cardPanel.getBackground());

        
        cardPanel.toggleSelection();
        assertFalse(cardPanel.isSelected());
    }

    /**
     * Checks if the mouse click triggers the selection correctly.
     */
    @Test
    void testClickSelection() {
        assertFalse(cardPanel.isSelected());

        cardPanel.mouseClicked(null);
        assertTrue(cardPanel.isSelected());

        cardPanel.mouseClicked(null);
        assertFalse(cardPanel.isSelected());
    }
}