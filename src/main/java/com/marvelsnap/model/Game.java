package com.marvelsnap.model;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.util.*;

public class Game {
    private TurnManager turnManager = new TurnManager();
    private List<Location> locations = new ArrayList<>();
    private Player[] players = new Player[2];
    private List<GameObserver> observers = new ArrayList<>();



    public void startGame(final String p1Name, final DeckType d1, final String p2Name, final DeckType d2) {
        return;
    }

    public boolean playCard(final Card card, final int locationIdx) {
        return false;
    }

    public void endTurn() {
        return;
    }

    public Player checkWinCondition() {
        return new Player();
    }

    public void addObserver(final GameObserver obs) {
        return;
    }

    private void notifyObserver() {
        return;
    }
}
