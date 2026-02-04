package com.marvelsnap.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;

public class CardPanel extends JPanel {
    private Card card;
    private boolean isSelected = false;

    public CardPanel(){
        this.setPreferredSize(new Dimension(100, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                toggleSelection();
            }
        });
    }

    public void setCard(Card c){
        this.card = c;
        JLabel cardNameLabel = new JLabel();
        cardNameLabel.setText(card.getName());  
        JLabel cardCostLabel = new JLabel();
        cardCostLabel.setText("COST = " + card.getCost());  
        JLabel cardPowerLabel = new JLabel();
        cardPowerLabel.setText("POWER = " + card.getPower());  
        this.add(cardNameLabel, BorderLayout.CENTER);
        this.add(cardCostLabel, BorderLayout.SOUTH);
        this.add(cardPowerLabel, BorderLayout.NORTH);
    }

    public void toggleSelection(){
        isSelected = !isSelected;

        if(isSelected == true){
            this.setBackground(Color.GRAY);
        }
        else{
            this.setBackground(Color.WHITE);
        }

        repaint();
        
    }
}
