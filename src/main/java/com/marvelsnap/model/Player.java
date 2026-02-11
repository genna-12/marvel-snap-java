package com.marvelsnap.model;

import java.util.List;
import com.marvelsnap.util.Constants;

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
        this.currentEnergy = 0;
    }

    public void drawCard(){
        
        if(hand.getCards().size() < Constants.MAX_HAND_SIZE){
            Card drawn = deck.draw();
            if(drawn != null){
                hand.add(drawn);
            }
        }
    }

    public void playCard(Card c){
        if (hand.getCards().contains(c)) {
            hand.remove(c);
            //mancava questo calcolo dell'energia residua dopo aver giocato la carta
            this.currentEnergy = this.currentEnergy - c.getCost();
            //debug
            System.out.println("[PLAYER] " + name + " gioca " + c.getName() + ". Energia residua: " + currentEnergy);
        }
    }

    public void resetEnergy(int amount){
        this.currentEnergy = amount;
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
