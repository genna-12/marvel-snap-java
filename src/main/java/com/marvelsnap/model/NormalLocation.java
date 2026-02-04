package com.marvelsnap.model;

import java.util.ArrayList;

public class NormalLocation extends Location {

    public NormalLocation() {
        super("NormalLocation", "Tutte le carte qui guadagnano 1 di forza");
    }

    public void applyEffect(Game game) {
        for (Card c : this.cardsPlayer1) {
            c.addPower(1);
        }
        for (Card c : this.cardsPlayer2) {
            c.addPower(1);
        }
    }
}
