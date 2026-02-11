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
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.marvelsnap.model.Card;

public class CardPanel extends JPanel {
    private Card card;
    private boolean isSelected = false;
    private Runnable onClickAction;

    private static final Color BG_NORMAL = new Color(210, 210, 210);
    private static final Color BG_SELECTED = new Color(180, 255, 180); // Verde chiaro
    private static final Border BORDER_NORMAL = BorderFactory.createLineBorder(Color.BLACK, 2);
    private static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.YELLOW, 5);

    public CardPanel() {
        this.setPreferredSize(new Dimension(120, 160));
        this.setLayout(new BorderLayout());
        this.setBackground(BG_NORMAL);
        this.setBorder(BORDER_NORMAL);
    }

    public void setCard(Card c) {
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

        this.setToolTipText("<html><b>" + c.getName() + "</b><br>Descrizione: " + c.getDescription() + "</b><br>Effetto: " + c.getAbility() + "</html>");
    }


    // metodo helper per creare le etichette dei costi e poteri con uno stile coerente
    private JLabel createStatLabel(String text, Color bgColor) {
        JLabel lbl = new JLabel(text);
        lbl.setOpaque(true);
        lbl.setBackground(bgColor);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    public void toggleSelection() {
        isSelected = !isSelected;
        if (isSelected) {
            this.setBorder(BORDER_SELECTED);
            this.setBackground(BG_SELECTED);
        } else {
            this.setBorder(BORDER_NORMAL);
            this.setBackground(BG_NORMAL);
        }
        this.repaint();

    }

    public void setOnClickAction(Runnable action) {
        this.onClickAction = action;
    }

    public void mouseClicked(MouseEvent e) {
        if (onClickAction != null)
            onClickAction.run();
        toggleSelection();
    }

    public boolean isSelected() {
        return isSelected;
    }
    
    public Card getCard() {
        return card;
    }
}
