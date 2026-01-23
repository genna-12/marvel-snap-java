package com.marvelsnap.model;

import java.util.*;

public abstract class Location {

    protected String name;
    protected String description;
    protected int capacity = 4;
    protected List<Card> cardsPlayer1;
    protected List<Card> cardsPlayer2;
    protected boolean revealed; //se la location è stata svelata è una varabile che logicamente potrebbe servire, l'aggiungo all'UML

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        revealed = true; //dummy

        this.cardsPlayer1 = new ArrayList<>();
        this.cardsPlayer2 = new ArrayList<>();
    }

    public boolean addCard(int pIdx, Card c) {
        return false;
    }

    public boolean isFull(int pIdx) {
        return false;
    }

    public int calculatePower(int pIdx) {
        return 0;
    }

    public abstract void applyEffect(Game game);

    // Getters utili (spesso sottointesi nell'UML ma potrebbero servire)
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Card> getCards(int pIdx) {
        if (pIdx == 0) return cardsPlayer1;
        else return cardsPlayer2;
    }
}
