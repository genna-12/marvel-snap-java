package com.marvelsnap.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;

public class HandPanel extends JPanel {
    private List<CardPanel> cardPanels = new ArrayList<>();

    public HandPanel() {
        // finche non implementate la classe scrivo il seguente codice di debug
        setBackground(new Color(50, 50, 100));
        setPreferredSize(new Dimension(800, 200));
        add(new JLabel("AREA MANO"));
    }

    public void setHand(Hand hand) {
        //System.out.println(hand.getCards().size());

        for(Card card : hand.getCards()){
            cardPanels.add(new CardPanel());
            cardPanels.get(cardPanels.size() - 1).setCard(card);

            this.add(cardPanels.get(cardPanels.size() - 1));
        }
        
        revalidate();
        repaint();
    }
}
