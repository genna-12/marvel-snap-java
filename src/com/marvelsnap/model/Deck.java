package com.marvelsnap.model;


import java.util.Stack;


public class Deck {
    private Stack<Card> cards = new Stack<>();

    public void shuffle(){}

    public Card draw(){
        return new Card();
    }

}
