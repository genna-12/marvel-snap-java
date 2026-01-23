package com.marvelsnap.model;

public abstract class Card {
    protected int id;
    protected String name;
    protected int cost;
    protected int power;
    protected String description;

    public Card(int id, String name, int cost, int power, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.power = power;
        this.description = description;
    }

    public abstract void onReveal(Game game, Location loc);

    public boolean isPlayable(int availableEnergy) {
        return cost <= availableEnergy;
    }

    // Getters
    public int getCost() {
        return cost;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
