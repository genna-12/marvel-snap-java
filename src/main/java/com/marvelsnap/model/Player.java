package com.marvelsnap.model;

import java.util.List;

public class Player {
    private String name;
    private int currentEnergy;
    private int totalScore;
    private Deck deck;
    private Hand hand;

    public Player(String name, List<Card> deck) {
        this.name = name;
        this.deck = new Deck(deck);
        this.deck.shuffle();
        this.hand = new Hand();
    }

    //costruttore vuoto per test
    public Player() {}

    public void drawCard(){}

    public void playCard(Card c){}

    public void resetEnergy(int amount){}


    // getters utili
    public Hand getHand(){
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }
}
