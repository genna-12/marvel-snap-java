package com.marvelsnap.model;

import java.util.*;

public abstract class Location {

    protected String name;
    protected String description;
    protected int capacity = 4;
    protected List<Card> cardsPlayer1;
    protected List<Card> cardsPlayer2;
    protected boolean revealed; //se la location è stata svelata è una varabile che logicamente potrebbe servire, l'aggiungo all'UML

    // costruttore dummy
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.revealed = false; //dummy
        this.cardsPlayer1 = new ArrayList<>();
        this.cardsPlayer2 = new ArrayList<>();
    }

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

    public boolean isFull(int pIdx) {
        if (pIdx == 0 && this.cardsPlayer1.size() == capacity) {
            return true;
        }
        if (pIdx == 1 && this.cardsPlayer2.size() == capacity) {
                return true;
        }
        return false;
    }

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

    public void revealLocation() {
        this.revealed = true;
    }

    public abstract void applyEffect(Game game);

    // Getters utili (spesso sottointesi nell'UML ma potrebbero servire)
    public String getName() {
        return this.name;
    }

    public String getDescription() {
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
