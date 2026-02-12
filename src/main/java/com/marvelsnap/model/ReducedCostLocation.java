package com.marvelsnap.model;

import java.util.List;

/**
 * A common Location's implementation, which has the effect of reducing (or increasing)
 * the cost of cards in each player hand, on the moment of its disclosure.
 */

public class ReducedCostLocation extends Location {

    private int costToReduce;
    private List<Integer> targetedCosts;

    /**
     * The class constructor.
     * 
     * @param name the location's name.
     * @param description the description of the location's effect.
     * @param costToReduce the amount of cost reduction (or increasement).
     * @param targetedCosts the costs of the cards targeted by this effect.
     */
    public ReducedCostLocation(String name, String description, int costToReduce, List<Integer> targetedCosts) {
        super(name, description);
        this.costToReduce = costToReduce;
        this.targetedCosts = targetedCosts;
    }

    @Override
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