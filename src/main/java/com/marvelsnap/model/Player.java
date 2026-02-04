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

    // costruttore vuoto per test
    public Player() {}

    public void drawCard(){
        
        if(hand.getCards().size() < 7){
            Card drawn = deck.draw();
            if(drawn != null){
                hand.add(drawn);
            }
        }
    }

    public void playCard(Card c){
        hand.remove(c);
    }

    public void resetEnergy(int amount){
        currentEnergy = amount;
    }


    // getters utili
    public Hand getHand(){
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getName() {
        return name;
    }
}
