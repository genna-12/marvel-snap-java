package com.marvelsnap.model;

/**
 * Abstract class representing a generic card in the game.
 * All specific types of cards (Basic, Bonus, Debuff) must extend this class.
 * It contains common attributes like power, cost, and name.
 */
public abstract class Card {
    protected int id;
    protected String name;
    protected int cost;
    protected int power;
    protected String description;
    protected String ability;
    protected boolean revealed = false;
    protected boolean selected = false;

    /**
     * Constructor for the Card class.
     * 
     * @param id          unique identifier for the card instance
     * @param name        display name of the character
     * @param cost        energy required to play the card
     * @param power       base strength of the card
     * @param description flavor text or lore
     * @param ability     text describing the card's effect
     */
    public Card(int id, String name, int cost, int power, String description, String ability) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.power = power;
        this.description = description;
        this.ability = ability;
    }

    /**
     * Abstract method that triggers the card's special effect when played.
     * 
     * @param game the current game instance
     * @param loc  the location where the card was played
     */
    public abstract void onReveal(Game game, Location loc);

    /**
     * Checks if the card can be played based on the current energy.
     * 
     * @param availableEnergy the amount of energy the player has
     * @return true if the cost is lower or equal to available energy
     */
    public boolean isPlayable(int availableEnergy) {
        return cost <= availableEnergy;
    }

    /** @return the energy cost to play the card */
    public int getCost() {
        return cost;
    }

    /** @return the current power of the card */
    public int getPower() {
        return power;
    }

    /** @return the display name of the card */
    public String getName() {
        return name;
    }

    /** @return the lore/description of the card */
    public String getDescription() {
        return description;
    }

    /** @return the unique ID of the card instance */
    public int getId() {
        return id;
    }

    /** @return the ability effect description */
    public String getAbility() {
        return this.ability;
    }

    /**
     * Checks if the card's effect has been revealed (activated).
     * @return true if the card's effect has been revealed, false otherwise
     */
    public boolean isRevealed() {
        return revealed;
    }

    /** @param revealed the new revealed state to set */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    /**
     * Checks if a card is selected.
     * @return true if the card is selected, false otherwise.
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Sets the selected state of the card.
     * @param selected the new state to set.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /** @param cost the new energy cost to set */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /** @param power the new power value to set */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Increases or decreases the card's power.
     * 
     * @param add the amount to add (can be negative)
     */
    public void addPower(int add) {
        this.power += add;
    }

    /**
     * Comparison logic for cards.
     * Two cards are considered equal if they share the same name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        // Due carte sono uguali se hanno lo stesso nome (e magari costo/potere)
        return name.equals(card.name);
    }
}
