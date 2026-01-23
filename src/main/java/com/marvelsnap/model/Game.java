package com.marvelsnap.model;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.util.*;

public class Game {
    private TurnManager turnManager = new TurnManager();
    private List<Location> locations = new ArrayList<>();
    private Player[] players = new Player[2];
    private List<GameObserver> observers = new ArrayList<>();

    public Game() {
        this.turnManager = new TurnManager();
        this.observers = new ArrayList<>();
        this.locations = new ArrayList<>();

        this.players = new Player[2];
        this.players[0] = new Player();
        this.players[1] = new Player();
    }

    public void startGame(final String p1Name, final DeckType d1, final String p2Name, final DeckType d2) {
        //logica da implementare
        
        notifyObserver();
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
        observers.add(obs);
    }

    private void notifyObserver() {
        for (GameObserver obs : observers) {
            obs.onGameUpdated();
        }
    }
}
