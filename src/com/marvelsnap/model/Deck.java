package com.marvelsnap.model;


import java.util.Stack;


public class Deck {
    private Stack<Card> cards = new Stack<>();

    public void shuffle(){}

    public Card draw(){
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }

}
