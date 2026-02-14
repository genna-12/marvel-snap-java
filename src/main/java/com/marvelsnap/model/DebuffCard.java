package com.marvelsnap.model;

/**
 * Represents a card that applies negative effects (debuffs).
 * Usually targets the opponent's cards to reduce their total power.
 */
public class DebuffCard extends Card {

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
    public DebuffCard(int id, String name, int cost, int power, String description, String ability) {
        super(id, name, cost, power, description, ability);
    }

    /* 
     * This method will be called when the card is played, applying its debuff effect.
     * The actual implementation will depend on the specific debuff mechanics.
     */
    @Override
    public void onReveal(Game game, Location loc) {
    }

}
