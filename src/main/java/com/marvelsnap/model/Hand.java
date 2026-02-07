package com.marvelsnap.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void add(Card c){
        cards.add(c);
    }

    public void remove(Card c){
        cards.remove(c);
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(this.cards);
    }
}
