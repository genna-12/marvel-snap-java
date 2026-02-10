package com.marvelsnap.view;

import java.awt.*;
import javax.swing.JPanel;

import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;
import com.marvelsnap.controller.GameController;

public class HandPanel extends JPanel {

    public HandPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setBackground(new Color(50, 50, 100));
        this.setPreferredSize(new Dimension(800, 220));
    }

    public void setHand(Hand hand, GameController controller) {
        this.removeAll();
        if (hand != null && hand.getCards() != null) {
            for (Card card : hand.getCards()) {
                CardPanel cp = new CardPanel();
                cp.setCard(card);
                cp.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (controller != null) {
                            controller.onCardClicked(card);
                            revalidate();
                            repaint();
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
}
