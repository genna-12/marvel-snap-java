package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.marvelsnap.model.Card;

/**
 * This class displays the card's name, cost, power, and abilities.
 * It also handles the visual state for the selection of a card.
 */
public class CardPanel extends JPanel {
    private Card card;
    private boolean isSelected = false;
    private Runnable onClickAction;

    private static final Color BG_NORMAL = new Color(210, 210, 210);
    private static final Color BG_SELECTED = new Color(180, 255, 180);
    private static final Border BORDER_NORMAL = BorderFactory.createLineBorder(Color.BLACK, 2);
    private static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.YELLOW, 5);

    /**
     * Constructs a CardPanel with default dimensions, layout, background and boards.
     */
    public CardPanel() {
        this.setPreferredSize(new Dimension(120, 160));
        this.setLayout(new BorderLayout());
        this.setBackground(BG_NORMAL);
        this.setBorder(BORDER_NORMAL);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                toggleSelection();
            }
        });
    }

    /**
     * Updates the panel to show the specified card's name, cost, and power.
     * 
     * @param c
     */
    public void setCard(Card c) {
        this.setName("CardPanel" + c.getId());
        this.card = c;
        this.removeAll();
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel costLbl = createStatLabel(" " + c.getCost() + " ", new Color(50, 100, 255));
        JLabel powerLbl = createStatLabel(" " + c.getPower() + " ", new Color(255, 140, 0));
        header.add(costLbl, BorderLayout.WEST);
        header.add(powerLbl, BorderLayout.EAST);
        this.add(header, BorderLayout.NORTH);

        JLabel nameLbl = new JLabel("<html><center>" + c.getName() + "</center></html>");
        nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        nameLbl.setFont(new Font("Arial", Font.BOLD, 12));
        nameLbl.setForeground(Color.BLACK);
        this.add(nameLbl, BorderLayout.CENTER);

        this.isSelected = c.isSelected();
        toggleSelection();
        
        this.setToolTipText("<html><b>" + c.getName() + "</b><br>Descrizione: " + c.getDescription() + "</b><br>Effetto: " + c.getAbility() + "</html>");
    }


    /**
     * Creates a styled label for card's cost and power.
     * 
     * @param text 
     * @param bgColor 
     * @return a JLabel.
     */
    private JLabel createStatLabel(String text, Color bgColor) {
        JLabel lbl = new JLabel(text);
        lbl.setOpaque(true);
        lbl.setBackground(bgColor);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    /**
     * Toggles the selection state of the card and updates its visual borders and colors.
     */
    public void toggleSelection() {
        if (isSelected) {
            this.setBackground(BG_SELECTED);
            this.setBorder(BORDER_SELECTED);
        } else {
            this.setBackground(BG_NORMAL);
            this.setBorder(BORDER_NORMAL);
        }
        this.repaint();

    }

    /**
     * Sets an action to be executed when the card is clicked.
     * 
     * @param action
     */
    public void setOnClickAction(Runnable action) {
        this.onClickAction = action;
    }

    /**
     * Handles the mouse click event.
     * 
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        if (onClickAction != null)
            onClickAction.run();
        toggleSelection();
    }

    /**
     * Gets the state of selection of the card.
     * 
     * @return true if selected, false otherwise.
     */
    public boolean isSelected() {
        return isSelected;
    }
    
    /**
     * Gets the Card in this panel.
     * 
     * @return the card displayed in this panel.
     */
    public Card getCard() {
        return card;
    }
}