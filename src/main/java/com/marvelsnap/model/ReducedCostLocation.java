package com.marvelsnap.model;

import java.util.List;

public class ReducedCostLocation extends Location {

    private int costToReduce;
    private List<Integer> targetedCosts;

    public ReducedCostLocation(String name, String description, int costToReduce, List<Integer> targetedCosts) {
        super(name, description);
        this.costToReduce = costToReduce;
        this.targetedCosts = targetedCosts;
    }

    public void applyEffect(Game game) {
        for (Card c : game.getPlayer1().getHand().getCards()) {
            if (this.targetedCosts.contains(c.getCost())) {
                c.setCost(c.getCost() - this.costToReduce);
            }
        }
        for (Card c : game.getPlayer2().getHand().getCards()) {
            if (this.targetedCosts.contains(c.getCost())) {
                c.setCost(c.getCost() - this.costToReduce);
            }
        }
    }
}