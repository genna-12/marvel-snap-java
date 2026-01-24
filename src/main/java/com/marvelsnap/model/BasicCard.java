package com.marvelsnap.model;

public class BasicCard extends Card {

    private String ability;

    public BasicCard(int id, String name, int cost, int power, String description) {
        super(id, name, cost, power, description);
    }

    public BasicCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description);
        this.ability = ability;
    }

    @Override
    public void onReveal(Game game, Location loc) {
        
    }
    
}
