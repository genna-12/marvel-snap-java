package com.marvelsnap.model;

import java.util.*;

/**
 * A common Location's implementation, which has the effect of adding (or subtracting)
 * power to cards that are already placed on it, on the moment of its disclosure. 
 */
public class NormalLocation extends Location {

    private int powerToAdd;
    private List<Integer> targetedCosts;

    /**
     * The class contructor.
     * 
     * @param name the location's name.
     * @param description the description of the location's effect.
     * @param powerToAdd the amount of power to add (or subtract) to the cards.
     * @param targetedCosts the costs of the cards targeted by this effect.
     */
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
