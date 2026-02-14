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

    /*Utility flag */
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
        final LocationFactory lf = new LocationFactory();
        this.locations = lf.createLocations();

        /*First location is revealed at the beginning of the game */
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
        final Player currentPlayer = this.players[this.turnManager.getCurrentPlayerIndex()];
        final Location targetLoc = this.locations.get(locationIdx);

        if (currentPlayer.getCurrentEnergy() >= card.getCost()
                && !targetLoc.isFull(this.turnManager.getCurrentPlayerIndex())) {
            currentPlayer.playCard(card);
            targetLoc.addCard(this.turnManager.getCurrentPlayerIndex(), card);
            card.setRevealed(false);

            notifyObserver();
            return true;
        }
       
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
            this.waitingForSwap = false;

            this.revealPhase();

            /*Check endGame before going to nextTurn */
            if(this.turnManager.getCurrentTurn() >= this.turnManager.getMaxTurns()) {
                final Player winner = this.checkWinCondition();
                for(final GameObserver obs : this.observers) {
                    obs.onGameOver(winner != null ? winner.getName() : "Pareggio");
                }
                return;
            } else {
                this.turnManager.nextTurn();
                
                for(final Player player : this.players) {
                    player.drawCard();
                    player.resetEnergy(this.turnManager.getEnergyForTurn()); /*Reset energy for the next turn */
                }

                /*Second and third location revelation */
                if(this.turnManager.getCurrentTurn() == 2) {
                    this.locations.get(1).revealLocation(this);
                } else if(this.turnManager.getCurrentTurn() == 3) {
                    this.locations.get(2).revealLocation(this);
                }
            }
        } else {
            this.waitingForSwap = true;
            this.turnManager.switchPlayer();
        }

        /* Notify observers */
        notifyObserver();
    }

    /**
     * Helper method to reveal the cards when both players end the turn.
     * First, it reveals the cards played by player 1, then those played by player 2. 
     */
    private void revealPhase() {
        for(final Location loc : this.locations) {
            /*Let's reveal the card played by player 1 */
            for(final Card c : loc.getCards(0)) {
                if(!c.isRevealed()) {
                    c.setRevealed(true);
                    c.onReveal(this, loc);
                }
            }

            /*Let's reveal the card played by player 2 */
            for(final Card c : loc.getCards(1)) {
                if(!c.isRevealed()) {
                    c.setRevealed(true);
                    c.onReveal(this, loc);
                }
            }
        }
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
    public void addObserver(final GameObserver obs) {
        this.observers.add(obs);
    }

    /**
     * Removes an observer.
     * @param observer the observer to remove.
     */
    public void removeObserver(final GameObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifies all the observers.
     */
    private void notifyObserver() {
        for(final GameObserver obs : observers) {
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
    public Player getPlayer(final int index) {
        return this.players[index];
    }

    /**
     * Checks if the game is waiting for players to switch. 
     * It is used to show the intermission screen.
     * 
     * @return true if players are switching, false otherwise.
     */
    public boolean isWaitingForSwap() {
        return this.waitingForSwap;
    }

    /**
     * Sets the swap state of the game.
     * 
     * @param val the new state.
     */
    public void setWaitingForSwap(final boolean val) {
        this.waitingForSwap = val;
    }
}
