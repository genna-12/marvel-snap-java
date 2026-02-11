package com.marvelsnap.model;

import java.util.*;

/**
 * The abstract class that defines the concept of "location", that can 
 * host up to four different cards for each player. Its effect is activated
 * after its disclosure.
 */

public abstract class Location {

    protected String name;
    protected String description;
    protected int capacity = 4;
    protected List<Card> cardsPlayer1;
    protected List<Card> cardsPlayer2;
    protected boolean revealed;

    /**
     * The class constructor.
     * 
     * @param name the location's name.
     * @param description the description of the location's effect. 
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.revealed = false;
        this.cardsPlayer1 = new ArrayList<>();
        this.cardsPlayer2 = new ArrayList<>();
    }

    /**
     * Adds a card to the location. 
     * 
     * @param pIdx the player index.
     * @param c the card to add.
     * @return true if the pIdx is either 0 or 1 and the location isn't full,
     * false otherwise.
     */
    public boolean addCard(int pIdx, Card c) {
        if (pIdx == 0) {
            if (!isFull(pIdx)) {
                this.cardsPlayer1.add(c);
                return true;
            }
        }
        if (pIdx == 1) {
            if (!isFull(pIdx)) {
                this.cardsPlayer2.add(c);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the location is full.
     * 
     * @param pIdx the player index.
     * @return true if the pIdx is either 0 or 1 and the player's number
     * of cards is greater then or equal to the location's capacity,
     * false otherwise.
     */
    public boolean isFull(int pIdx) {
        if (pIdx == 0 && this.cardsPlayer1.size() >= capacity) {
            return true;
        }
        if (pIdx == 1 && this.cardsPlayer2.size() >= capacity) {
                return true;
        }
        return false;
    }

    /**
     * Calculates the total power of one player's side.
     * 
     * @param pIdx the player index.
     * @return the total power of the chosen player.
     */
    public int calculatePower(int pIdx) {
        int power = 0;
        if (pIdx == 0) {
            for (Card c : this.cardsPlayer1) {
                power += c.getPower();
            }
        }
        if (pIdx == 1) {
            for (Card c : this.cardsPlayer2) {
                power += c.getPower();
            }
        }
        return power;
    }

    /**
     * Reveals the location and applies its effect.
     * 
     * @param game the current game to apply the location's effect on.
     */
    public void revealLocation(Game game) {
        this.revealed = true;
        applyEffect(game);
    }

    /**
     * Applies the location's effect.
     * 
     * @param game the current game to apply the location's effect on.
     */
    protected abstract void applyEffect(Game game);

    public String getName() {
        if (!isRevealed()) {
            return "";
        }
        return this.name;
    }

    public String getDescription() {
        if (!isRevealed()) {
            return "Questa location non è stata ancora rivelata";
        }
        return this.description;
    }

    public List<Card> getCards(int pIdx) {
        if (pIdx == 0) {
            return this.cardsPlayer1;
        } else {
            return this.cardsPlayer2;
        }
    }

    public boolean isRevealed() {
        return this.revealed;
    }
}
