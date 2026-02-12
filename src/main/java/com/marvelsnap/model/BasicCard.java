package com.marvelsnap.model;

/**
 * Represents a basic card with no special effects.
 * Usually has higher base power since it doesn't provide abilities.
 */
public class BasicCard extends Card {

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
    public BasicCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description, ability);
    }

    /**
     * This method will be called when the card is played, but since it's a basic
     * card, it has no special effect to apply.
     * The actual implementation will depend on the game mechanics, but for a basic
     * card, it might simply do nothing or trigger a standard reveal animation.
     */
    @Override
    public void onReveal(Game game, Location loc) {

    }

}
