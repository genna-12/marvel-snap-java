package com.marvelsnap.model;

public class BonusCard extends Card {

    public BonusCard(int id, String name, int cost, int power, String description) {
        super(id, name, cost, power, description);
    }

    @Override
    public void onReveal(Game game, Location loc) {
        
    }
}

