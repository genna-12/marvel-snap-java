package com.marvelsnap.view;

import javax.swing.*;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.Card;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LocationPanel extends JPanel {

    private Location location;
    private JPanel p1CardsArea;
    private JPanel p2CardsArea;
    //Probabilmente servirà infoLabel devo aggiungerla all'UML
    private JLabel infoLabel;
    //Potrebbe anche servire per il controller un locationIndex
    private int locationIndex;
    private JPanel locationArea;
    private JLabel p1PowerLabel;
    private JLabel p2PowerLabel;
    private List<CardPanel> p1Cards;
    private List<CardPanel> p2Cards;

    public LocationPanel(int locIndex) {
        this.locationIndex = locIndex;
        this.setLayout(new GridLayout(3, 1));

        this.p1CardsArea = new JPanel(new GridLayout(2, 2));
        this.p1CardsArea.setBackground(Color.BLACK);

        this.p2CardsArea = new JPanel(new GridLayout(2, 2));
        this.p2CardsArea.setBackground(Color.BLACK);

        this.locationArea = new JPanel(new BorderLayout());

        this.infoLabel = new JLabel("Info");
        this.infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.infoLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.p1PowerLabel = new JLabel("0");
        this.p1PowerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.p1PowerLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.p2PowerLabel = new JLabel("0");
        this.p2PowerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.p2PowerLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.p1Cards = new ArrayList<>();
        this.p2Cards = new ArrayList<>();

        this.locationArea.add(this.p2PowerLabel, BorderLayout.NORTH);
        this.locationArea.add(this.p1PowerLabel, BorderLayout.SOUTH);
        this.locationArea.add(this.infoLabel, BorderLayout.CENTER);

        this.add(this.p2CardsArea);
        this.add(this.locationArea);
        this.add(this.p1CardsArea);
    }

    public void setLocation(Location loc) {
        this.location = loc;
        this.infoLabel.setText("" + this.location.getDescription());
    }

    public void refresh() {
        this.p1PowerLabel.setText("" + this.location.calculatePower(0));
        this.p2PowerLabel.setText("" + this.location.calculatePower(1));

        this.infoLabel.setText("" + this.location.getDescription());

        this.p1CardsArea.removeAll();
        for (Card c : this.location.getCards(0)) {
            CardPanel newCard = new CardPanel();
            newCard.setCard(c);
            this.p1CardsArea.add(newCard);
        }

        this.p2CardsArea.removeAll();
        for (Card c : this.location.getCards(1)) {
            CardPanel newCard = new CardPanel();
            newCard.setCard(c);
            this.p2CardsArea.add(newCard);
        }
    }       
}