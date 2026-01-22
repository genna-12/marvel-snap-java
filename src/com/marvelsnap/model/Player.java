package com.marvelsnap.model;

public class Player {
    private String name;
    private int currentEnergy;
    private int totalScore;

    public void drawCard(){}

    public void playCard(Card c){}

    public void resetEnergy(int amount){}

    public Hand getHand(){
        return new Hand();
    }
}
