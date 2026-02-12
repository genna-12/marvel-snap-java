package com.marvelsnap.model;

import java.util.ArrayList;
import java.util.List;
import com.marvelsnap.util.*;

/**
 * Main class for the game logic.
 * It manages players, turns, locations and checks if game is over.
 * It notifies the observers when something changes.
 */
public class Game {
    private TurnManager turnManager;
    private List<Location> locations;
    private final Player[] players;
    private final List<GameObserver> observers;

    // flag utility
    private boolean waitingForSwap = false;

    /**
     * Class constructor.
     */
    public Game() {
        this.observers = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.players = new Player[2];
    }

    /**
     * Starts the game. It creates players, decks, hands and locations. It also sets
     * the initial energy for the players.
     * 
     * @param p1Name Name of player 1.
     * @param d1     Deck chosen by player 1.
     * @param p2Name Name of player 2.
     * @param d2     Deck chosen by player 2.
     */
    public void startGame(final String p1Name, final DeckType d1, final String p2Name, final DeckType d2) {
        this.turnManager = new TurnManager();

        /* Players inizialization */
        final CardFactory cf = new CardFactory();
        this.players[0] = new Player(p1Name, cf.createDeck(d1));
        this.players[1] = new Player(p2Name, cf.createDeck(d2));

        /* Hands initialization */
        for (int i = 0; i < 3; i++) {
            this.players[0].drawCard();
            this.players[1].drawCard();
        }

        /* Energy initialization */
        this.players[0].resetEnergy(1);
        this.players[1].resetEnergy(1);

        /* Locations inizialization */
        LocationFactory lf = new LocationFactory();
        this.locations = lf.createLocations();

        this.locations.get(0).revealLocation(this);

        notifyObserver();
    }

    /**
     * Tries to play a card on a location.
     * It checks if card is playable and if the location is full. Then plays the
     * card.
     * 
     * @param card        Card to play.
     * @param locationIdx Index of location (0, 1, 2).
     * @return true if the card is played, false otherwise.
     */
    public boolean playCard(final Card card, final int locationIdx) {
        Player currentPlayer = this.players[turnManager.getCurrentPlayerIndex()];
        Location targetLoc = this.locations.get(locationIdx);

        System.out.println("[DEBUG] Tentativo giocata: " + card.getName() + " su Loc " + locationIdx);
        if (currentPlayer.getCurrentEnergy() >= card.getCost()
                && !targetLoc.isFull(turnManager.getCurrentPlayerIndex())) {
            currentPlayer.playCard(card);
            targetLoc.addCard(turnManager.getCurrentPlayerIndex(), card);
            card.onReveal(this, targetLoc);

            System.out.println("[DEBUG] Giocata RIUSCITA. Energia residua: " + currentPlayer.getCurrentEnergy());
            notifyObserver();
            return true;
        }
        System.out.println("[DEBUG] Giocata FALLITA. Energia: " + currentPlayer.getCurrentEnergy() + " Costo: " + card.getCost());
        return false;
    }

    /**
     * Ends the turn for the current player.
     * If both players finished the turn, it goes to the next turn and updates
     * energy/hand.
     * If only one player finished his turn, it switches to the other player.
     */
    public void endTurn() {

        this.turnManager.registerMove(this.turnManager.getCurrentPlayerIndex());
        /* Both players finished the turn */
        if (this.turnManager.isTurnCycleComplete()) {
            System.out.println("[DEBUG] Fine Ciclo. Risoluzione turno.");
            this.waitingForSwap = false;

            this.turnManager.nextTurn();

            for (final Player player : this.players) {
                player.drawCard();
                player.resetEnergy(this.turnManager.getEnergyForTurn()); /* Reset Energy for next turn */
            }

            if(this.turnManager.getCurrentTurn() == 2) {
                this.locations.get(1).revealLocation(this);
            } else if(this.turnManager.getCurrentTurn() == 3) {
                this.locations.get(2).revealLocation(this);
            }

            /* Check endgame */
            if (this.turnManager.getCurrentTurn() > this.turnManager.getMaxTurns()) {
                Player winner = this.checkWinCondition();
                for (GameObserver obs : this.observers) {
                    obs.onGameOver(winner != null ? winner.getName() : "Pareggio");
                }
                return;
            }
        } else {
            System.out.println("[DEBUG] Fine turno parziale. Attivazione Swap.");
            this.waitingForSwap = true;
            this.turnManager.switchPlayer();
        }

        /* Notify observers */
        notifyObserver();
    }

    /**
     * Checks which player won the game.
     * 
     * @return the player who won or null if is a tie.
     */
    public Player checkWinCondition() {
        return WinCondition.determineWinner(locations, players[0], players[1]);
    }

    /**
     * Adds an observer for the GUI.
     * 
     * @param obs Observer to add.
     */
    public void addObserver(GameObserver obs) {
        this.observers.add(obs);
    }

    /**
     * Notifies all the observers.
     */
    private void notifyObserver() {
        for (GameObserver obs : observers) {
            obs.onGameUpdated();
        }
    }

    /**
     * Gets the locations.
     * 
     * @return the list of locations.
     */
    public List<Location> getLocations() {
        return this.locations;
    }

    /**
     * Gets the turn manager.
     * 
     * @return the turn manager.
     */
    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    /**
     * Gets player1.
     * 
     * @return player1.
     */
    public Player getPlayer1() {
        return this.players[0];
    }

    /**
     * Gets player2.
     * 
     * @return player2.
     */
    public Player getPlayer2() {
        return this.players[1];
    }

    /**
     * Gets a player at a specific index.
     * 
     * @param index the index of the player to get.
     * @return the player at @param index.
     */
    public Player getPlayer(int index) {
        return this.players[index];
    }

    public boolean isWaitingForSwap() {
        return waitingForSwap;
    }

    public void setWaitingForSwap(boolean val) {
        this.waitingForSwap = val;
    }
}
