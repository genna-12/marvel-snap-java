package com.marvelsnap.model;

public class BonusCard extends Card {

    public BonusCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description, ability);
    }

    @Override
    public void onReveal(Game game, Location loc) {
        
    }
}

