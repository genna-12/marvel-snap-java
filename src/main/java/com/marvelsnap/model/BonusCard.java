package com.marvelsnap.model;

public class BonusCard extends Card {

    private String ability;

    public BonusCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description);
        this.ability = ability;
    }

    @Override
    public void onReveal(Game game, Location loc) {
        
    }
}

