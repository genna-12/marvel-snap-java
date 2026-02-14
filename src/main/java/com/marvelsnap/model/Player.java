package com.marvelsnap.model;

import java.util.List;
import com.marvelsnap.util.Constants;

/**
 * Represents a player in the game.
 * Manages the player's name, energy, score, deck, and hand of cards.
 */
public class Player {
    private String name;
    private int currentEnergy;
    private int totalScore;
    private Deck deck;
    private Hand hand;

    /**
     * Constructs a new Player with a name and a deck of cards.
     *
     * @param name the name of the player.
     * @param deck the list of cards to be used as the player's deck.
     */
    public Player(String name, List<Card> deck) {
        this.name = name;
        this.deck = new Deck(deck);
        this.deck.shuffle();
        this.hand = new Hand();
        this.currentEnergy = 0;
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     */

    public void drawCard(){
        if(hand.getCards().size() < Constants.MAX_HAND_SIZE){
            Card drawn = deck.draw();
            if(drawn != null){
                hand.add(drawn);
            }
        }
    }

    /**
     * Plays a card from the player's hand by removing it from the hand
     * and updates the energy still available.
     * 
     * @param c the card to be played.
     */
    public void playCard(Card c){
        if (hand.getCards().contains(c)) {
            hand.remove(c);
            this.currentEnergy = this.currentEnergy - c.getCost();
        }
    }

    /**
     * Sets the player's current energy to a specific amount.
     * 
     * @param amount the amount of energy that the player will be able to spend.
     */
    public void resetEnergy(int amount){
        this.currentEnergy = amount;
    }


     /**
     * Returns the player's current hand.
     * 
     * @return the hand object containing the player's cards.
     */
    public Hand getHand(){
        return hand;
    }

    /**
     * Returns the player's deck.
     * 
     * @return the deck object used by the player.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Returns the current energy available to the player.
     * 
     * @return the current energy value.
     */
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * Returns the total score achieved by the player.
     * 
     * @return the total score value.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns the name of the player.
     * 
     * @return the player's name.
     */
    public String getName() {
        return name;
    }
}