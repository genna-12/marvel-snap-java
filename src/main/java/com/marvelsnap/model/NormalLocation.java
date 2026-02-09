package com.marvelsnap.model;

import java.util.*;

public class NormalLocation extends Location {

    private int powerToAdd;

    public NormalLocation(String name, String description, int powerToAdd) {
        super(name, description);
        this.powerToAdd = powerToAdd;
    }

    public void applyEffect(Game game) {
        for (Card c : cardsPlayer1) {
            c.addPower(powerToAdd);
        }
        for (Card c : cardsPlayer2) {
            c.addPower(powerToAdd);
        }
    };
}
