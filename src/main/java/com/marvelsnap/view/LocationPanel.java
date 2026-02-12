package com.marvelsnap.view;

import javax.swing.*;
import com.marvelsnap.model.Location;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class LocationPanel extends JPanel {

    private Location location;
    private JPanel p1CardsArea;
    private JPanel p2CardsArea;
    private JLabel infoLabel;
    private int locationIndex;
    private JPanel infoArea;
    private JLabel p1PowerLabel;
    private JLabel p2PowerLabel;
    private GameController controller;
    private List<JPanel> p1Cells;
    private List<JPanel> p2Cells;
    

    public LocationPanel(int locIndex) {
        this.locationIndex = locIndex;
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        this.p1CardsArea = new JPanel(new GridLayout(2, 2));
        this.p1CardsArea.setBackground(new Color(40, 40, 40)); // l ho messo grigio scuro che mi sembra piu carino
        this.p1CardsArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Player 1"));
        this.p1Cells = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JPanel cell = new JPanel(new BorderLayout());
            cell.setOpaque(false);
            this.p1Cells.add(cell);
            this.p1CardsArea.add(cell);
        }

        this.p2CardsArea = new JPanel(new GridLayout(2, 2));
        this.p2CardsArea.setBackground(new Color(40, 40, 40));
        this.p2CardsArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Player 2"));
        this.p2Cells = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JPanel cell = new JPanel(new BorderLayout());
            cell.setOpaque(false);
            this.p2Cells.add(cell);
            this.p2CardsArea.add(cell);
        }

        this.infoArea = new JPanel(new BorderLayout());
        this.infoArea.setBackground(new Color(20, 20, 50));

        this.infoLabel = new JLabel("Info");
        this.infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.infoLabel.setForeground(Color.WHITE);
        this.infoLabel.setFont(new Font("Arial", Font.BOLD, 14));

        this.p1PowerLabel = new JLabel("0");
        this.p1PowerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.p1PowerLabel.setForeground(Color.CYAN); // cyan è il nome ricco dell'azzurro
        this.p1PowerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        this.p2PowerLabel = new JLabel("0");
        this.p2PowerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.p2PowerLabel.setForeground(Color.ORANGE);
        this.p2PowerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        this.infoArea.add(this.p2PowerLabel, BorderLayout.NORTH);
        this.infoArea.add(this.p1PowerLabel, BorderLayout.SOUTH);
        this.infoArea.add(this.infoLabel, BorderLayout.CENTER);

        this.add(this.p2CardsArea);
        this.add(this.infoArea);
        this.add(this.p1CardsArea);

        propagateMouseListener();
    }

    public void setLocation(Location loc) {
        this.location = loc;
        if (this.location != null)
            this.infoLabel.setText(loc.getName());
    }

    public void refresh() {
        if (this.location == null)
            return;

        this.p1PowerLabel.setText("P1: " + this.location.calculatePower(0));
        this.p2PowerLabel.setText("P2: " + this.location.calculatePower(1));

        this.infoLabel.setText("<html>" + "<font size = '5'> <b>" +
            this.location.getName() + "</b></font><br> " +
            this.location.getDescription() + "</html>");
                
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            this.p1Cells.get(i).removeAll();
        }
        for (Card c : this.location.getCards(0)) {
            CardPanel newCard = new CardPanel();
            newCard.setCard(c);
            this.p1Cells.get(counter).add(newCard);
            counter++;
        }

        counter = 0;
        for (int i = 0; i < 4; i++) {
            this.p2Cells.get(i).removeAll();
        }
        for (Card c : this.location.getCards(1)) {
            CardPanel newCard = new CardPanel();
            newCard.setCard(c);
            this.p2Cells.get(counter).add(newCard);
            counter++;
        }
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void propagateMouseListener() {
        MouseAdapter clicker = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controller != null)
                    controller.onLocationClicked(locationIndex);
            }
        };

        this.p1CardsArea.addMouseListener(clicker);
        this.p2CardsArea.addMouseListener(clicker);
        this.infoArea.addMouseListener(clicker);
    }

    // reset di location panel serviva sempre al mio GamePanel P2
    public void reset() {
        infoLabel.setText("Location");
        
        p1CardsArea.removeAll();
        p2CardsArea.removeAll();
        
        revalidate();
        repaint();
    }
}