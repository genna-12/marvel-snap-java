package com.marvelsnap.model;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.util.*;

public class Game {
    private TurnManager turnManager;
    private List<Location> locations;
    private final Player[] players;
    private final List<GameObserver> observers;

    public Game() {
        this.observers = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.players = new Player[2];
    }

    public void startGame(final String p1Name, final DeckType d1, final String p2Name, final DeckType d2) {
        this.turnManager = new TurnManager();

        /*Players inizialization */
        final CardFactory cf = new CardFactory();
        this.players[0] = new Player(p1Name, cf.createDeck(d1));
        this.players[1] = new Player(p2Name, cf.createDeck(d2));

        /*Locations inizialization */
        final LocationFactory lf = new LocationFactory();
        this.locations = lf.createLocations();

        notifyObserver();
    }

    public boolean playCard(final Card card, final int locationIdx) {
        final Player currentPlayer = this.players[turnManager.getCurrentPlayerIndex()];
        final Location targetLoc = this.locations.get(locationIdx);

        if(card.isPlayable(turnManager.getEnergyForTurn()) && !targetLoc.isFull(turnManager.getCurrentPlayerIndex())) {
            currentPlayer.playCard(card);
            targetLoc.addCard(turnManager.getCurrentPlayerIndex(), card);
            card.onReveal(this, targetLoc);
            
            notifyObserver();
            return true;
        }

        return false;
    }

    public void endTurn() {
        if(turnManager.isTurnCycleComplete()) {
            for(final Location loc : this.locations) {
                loc.applyEffect(this);
            }
            turnManager.nextTurn();

            for(final Player player : this.players) {
                player.drawCard();
            }

            /*Check endgame */
            if(turnManager.getTurnNumber() > this.turnManager.getMaxTurns()) {
                final Player winner = this.checkWinCondition();
                for(final GameObserver obs : this.observers) {
                    obs.onGameOver(null); /*Da rivedere -> inserire nella classe Player dei getter per i campi, 
                                                        per favore  */
                }
                return;
            }
        } else {
            turnManager.switchPlayer();
        }

        /*Notify observers */
        for(final GameObserver obs : this.observers) {
            obs.onTurnChanged(this.turnManager.getCurrentPlayerIndex());
        }
        notifyObserver();
    }

    public Player checkWinCondition() {
        return WinCondition.determineWinner(locations, players[0], players[1]);
    }

    public void addObserver(final GameObserver obs) {
        observers.add(obs);
    }

    private void notifyObserver() {
        for (GameObserver obs : observers) {
            obs.onGameUpdated();
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }
}
