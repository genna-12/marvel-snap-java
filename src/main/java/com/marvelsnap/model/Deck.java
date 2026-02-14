package com.marvelsnap.model;


import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Represents a deck of playing cards.
 * This class manages a deck of cards with methods to shuffle  and draw cards.
 */
public class Deck {
    private Stack<Card> cards;

    /**
     * Constructs a new deck from a list of cards and shuffles it.
     * 
     * @param deck the initial list of cards to put in the deck.
     */
    public Deck(List<Card> deck) {
        this.cards = new Stack<>();
        this.cards.addAll(deck);
        this.shuffle();
    }

    /**
     * Randomly reorders the cards within the deck.
     */
    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * Draws the top card from the deck.
     * 
     * @return the card drawn from the deck, or null if the deck is empty.
     */
    public Card draw(){
        if(cards.isEmpty()){
            return null;
        }
        return cards.pop();
    }
}
