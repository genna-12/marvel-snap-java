package com.marvelsnap.model;

import java.util.*;

public class NormalLocation extends Location {

    private int powerToAdd;
    private List<Integer> targetedCosts;

    public NormalLocation(String name, String description, int powerToAdd, List<Integer> targetedCosts) {
        super(name, description);
        this.powerToAdd = powerToAdd;
        this.targetedCosts = targetedCosts;
    }

    @Override
    public void applyEffect(Game game) {
        for (Card c : this.cardsPlayer1) {
            if (this.targetedCosts.contains(c.getCost())) {
                c.addPower(powerToAdd);
            }
        }
        for (Card c : this.cardsPlayer2) {
            if (this.targetedCosts.contains(c.getCost())) {
                c.addPower(powerToAdd);
            }
        }
    }
}
