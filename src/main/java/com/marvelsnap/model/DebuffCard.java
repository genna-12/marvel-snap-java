package com.marvelsnap.model;

public class DebuffCard extends Card {

    public DebuffCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description, ability);
    }

    @Override
    public void onReveal(Game game, Location loc) {
    }
    
}
