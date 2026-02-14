package com.marvelsnap.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents list cards currently held by a player.
 * Provides methods to add and remove cards during the game.
 */
public class Hand {
    private List<Card> cards = new ArrayList<>();

    /**
     * Adds a card to the player's hand.
     * 
     * @param c 
     */
    public void add(Card c){
        cards.add(c);
    }

    /**
     * Removes a card from the player's hand.
     * 
     * @param c
     */
    public void remove(Card c){
        cards.remove(c);
    }

    /**
     * Gets cards in the hand.
     * 
     * @return a list containing the cards that are currently in the hand.
     */
    public List<Card> getCards(){
        return Collections.unmodifiableList(this.cards);
    }
}
