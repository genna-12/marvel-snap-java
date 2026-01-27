package com.marvelsnap.model;


import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class Deck {
    private Stack<Card> cards;

    public Deck(List<Card> deck) {
        this.cards = new Stack<>();
        this.cards.addAll(deck);
        this.shuffle();
    }

    public void shuffle(){
        // questo comando mescola le carte nel mazzo
        Collections.shuffle(cards);
    }

    public Card draw(){
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }
}
