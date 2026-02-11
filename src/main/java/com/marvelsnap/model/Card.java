package com.marvelsnap.model;

public abstract class Card {
    protected int id;
    protected String name;
    protected int cost;
    protected int power;
    protected String description;
    protected String ability; 

    public Card(int id, String name, int cost, int power, String description, String ability) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.power = power;
        this.description = description;
        this.ability = ability;
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

    // Setters
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void addPower(int add) {
        this.power += add;
        System.out.println("[DEBUG MODEL] " + this.name + " forza cambiata a " + this.power);
    }

    public String getAbility() {
        return this.ability; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        // Due carte sono uguali se hanno lo stesso nome (e magari costo/potere)
        return name.equals(card.name); 
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
