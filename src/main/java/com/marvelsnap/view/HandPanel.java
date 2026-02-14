package com.marvelsnap.view;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;
import com.marvelsnap.controller.GameController;

public class HandPanel extends JPanel {

    private GameController controller;

    public HandPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setBackground(new Color(50, 50, 100));
        this.setPreferredSize(new Dimension(800, 220));
    }

    public void setHand(Hand hand) {
        this.removeAll();
        if (hand != null && hand.getCards() != null) {
            for (Card card : hand.getCards()) {
                CardPanel cp = new CardPanel();
                cp.setCard(card);
                // questo è il collegamento di cui parlavo, quando clicco su una carta del mio hand, chiamo il metodo onCardClicked del controller
                cp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if (controller != null) {
                            System.out.println("Click rilevato su carta: " + card.getName());
                            controller.onCardClicked(card);
                            cp.toggleSelection();
                        } else {
                            System.out.println("Controller non settato in HandPanel");
                        }
                    }
                });
                this.add(cp);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
