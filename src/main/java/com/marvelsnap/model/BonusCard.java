package com.marvelsnap.model;

/**
 * Represents a card that provides a positive effect (buff) when played.
 * These effects usually increase the power of other cards or the card itself.
 */
public class BonusCard extends Card {

    /**
     * Constructor for the DebuffCard class.
     * 
     * @param id          unique identifier for the card instance
     * @param name        display name of the character
     * @param cost        energy required to play the card
     * @param power       base strength of the card (can be negative for debuffs)
     * @param description flavor text or lore
     * @param ability     text describing the card's effect
     */
    public BonusCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description, ability);
    }

    /*
     * This method will be called when the card is played, applying its buff effect.
     * The actual implementation will depend on the specific buff mechanics.
     */
    @Override
    public void onReveal(Game game, Location loc) {

    }
}
