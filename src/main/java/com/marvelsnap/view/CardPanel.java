package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        this.card = c;
        this.removeAll();

        JLabel cardNameLabel = new JLabel(card.getName()); 
        cardNameLabel.setForeground(Color.BLACK);
        cardNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cardNameLabel.setHorizontalAlignment(JLabel.CENTER); 

        JLabel cardCostLabel = new JLabel("COST: " + card.getCost()); 
        cardCostLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel cardPowerLabel = new JLabel("POWER: " + card.getPower()); 
        cardPowerLabel.setHorizontalAlignment(JLabel.CENTER); 
        
        this.add(cardNameLabel, BorderLayout.CENTER);
        this.add(cardCostLabel, BorderLayout.NORTH);
        this.add(cardPowerLabel, BorderLayout.SOUTH);
        
        this.revalidate();
        this.repaint();
    }

    /**
     * Toggles the selection state of the card and updates its visual borders and colors.
     */
    public void toggleSelection() {
        isSelected = !isSelected;
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