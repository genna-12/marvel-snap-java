package com.marvelsnap.model;

public class ReducedCostLocation extends Location {

    private int costToReduce;

    public ReducedCostLocation(String name, String description, int costToReduce) {
        super(name, description);
        this.costToReduce = costToReduce;
    }

    public void applyEffect(Game game) {
        
    }
}