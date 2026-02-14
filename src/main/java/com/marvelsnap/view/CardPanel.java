package com.marvelsnap.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
<<<<<<< Updated upstream
import java.awt.event.MouseAdapter;
=======
>>>>>>> Stashed changes
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;

/**
 * This class displays the card's name, cost, power, and abilities.
 * It also handles the visual state for the selection of a card.
 */
public class CardPanel extends JPanel {
    private Card card;
    private boolean isSelected = false;

<<<<<<< Updated upstream
    public CardPanel(){
        this.setPreferredSize(new Dimension(150, 190));
        this.setBackground(new Color(210, 210, 210));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
=======
    private static final Color BG_NORMAL = new Color(210, 210, 210);
    private static final Color BG_SELECTED = new Color(180, 255, 180);
    private static final Border BORDER_NORMAL = BorderFactory.createLineBorder(Color.BLACK, 2);
    private static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.YELLOW, 5);

    /**
     * Constructs a CardPanel with default dimensions, layout, background and boards.
     */
    public CardPanel() {
        this.setPreferredSize(new Dimension(120, 160));
>>>>>>> Stashed changes
        this.setLayout(new BorderLayout());

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                toggleSelection();
            }
        });
    }

<<<<<<< Updated upstream
    public void setCard(Card c){
=======
    /**
     * Updates the panel to show the specified card's name, cost, and power.
     * 
     * @param c
     */
    public void setCard(Card c) {
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public void toggleSelection(){
=======
    /**
     * Helper method to create labels for cost and power.
     * 
     * @param text 
     * @param bgColor the background color of the label.
     * @return a jlabel.
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
>>>>>>> Stashed changes
        isSelected = !isSelected;
        if(isSelected == true){
            this.setBackground(Color.GRAY);
        }
<<<<<<< Updated upstream
        else{
            this.setBackground(new Color(210, 210, 210));
        }
        repaint();
        
=======
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
>>>>>>> Stashed changes
    }
}
