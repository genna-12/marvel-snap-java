package com.marvelsnap.view;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;
import com.marvelsnap.controller.GameController;

/**
 * Panel that shows the player's current hand.
 * It manages the creation of CardPanel components and 
 * the interaction with the user.
 */
public class HandPanel extends JPanel {
    private GameController controller;

    /**
     * Constructs the hand panel.
     */
    public HandPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setBackground(new Color(50, 50, 100));
        this.setPreferredSize(new Dimension(800, 220));
    }

    /**
     * Updates the panel to display the cards currently in the player's hand.
     * For each card, it creates a CardPanel and a mouse listener 
     * to notify the controller when a card is selected with a click.
     * 
     * @param hand 
     * @param controller
     */
    public void setHand(Hand hand) {
        this.removeAll();
        if (hand != null && hand.getCards() != null) {
            for (Card card : hand.getCards()) {
                CardPanel cp = new CardPanel();
                cp.setCard(card);

                cp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if (controller != null) {
                            System.out.println("Click rilevato su carta: " + card.getName());
                            controller.onCardClicked(card);
                        } else {
                            System.out.println("Controller non settato in HandPanel");
                        }
                    }
                });
                this.add(cp);
            }
        }
        revalidate();
        repaint(); 
    }

    /**
     * Sets the controller
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }
}
