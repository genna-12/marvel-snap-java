package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;

public class CardPanel extends JPanel {
    private Card card;
    private boolean isSelected = false;

    public CardPanel(){
        this.setPreferredSize(new Dimension(150, 190));
        this.setBackground(new Color(210, 210, 210));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
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
        cardNameLabel.setForeground(Color.BLACK);
        cardNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cardNameLabel.setHorizontalAlignment(JLabel.CENTER); 

        JLabel cardCostLabel = new JLabel();
        cardCostLabel.setText("COST = " + card.getCost()); 
        cardCostLabel.setForeground(Color.BLACK); 
        cardCostLabel.setHorizontalAlignment(JLabel.CENTER);
        cardCostLabel.setPreferredSize(new Dimension(100, 40));

        JLabel cardPowerLabel = new JLabel();
        cardPowerLabel.setText("POWER = " + card.getPower()); 
        cardPowerLabel.setForeground(Color.BLACK);   
        cardPowerLabel.setHorizontalAlignment(JLabel.CENTER); 
        cardPowerLabel.setPreferredSize(new Dimension(100, 40));
        
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
            this.setBackground(new Color(210, 210, 210));
        }
        repaint();
        
    }
}
