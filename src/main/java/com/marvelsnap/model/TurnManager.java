package com.marvelsnap.model;

import com.marvelsnap.util.Constants;

/**
 * Manages the turns of the game.
 * It keeps track of current turn number and whose turn it is.
 */
public class TurnManager {
    private int currentTurn;
    private int currentPlayerIndex;
    private boolean cycleComplete = false;
    private int maxTurns;

    /**
     * Class constructor.
     */
    public TurnManager() {
        this.maxTurns = Constants.MAX_TURNS;
        this.currentTurn = 1;
        this.currentPlayerIndex = 0;
    }

    /**
     * Returns the energy of the current turn. It is the same as the turn number.
     * @return Energy amount for the turn.
     */
    public int getEnergyForTurn() {
        return this.currentTurn;
    }

    /**
     * Goes to the nextTurn.
     * It resets player index to 0.
     */
    public void nextTurn() {
        this.currentTurn++;
        this.currentPlayerIndex = 0;
        this.cycleComplete = false;
    }

    /**
     * Switched the player who should play.
     * If player index was 0 (Player 1), it becomes 1 (Player 2). If player index was 1, the cycle is complete.
     */
    public void switchPlayer() {
        if(this.currentPlayerIndex == 0) {
            this.currentPlayerIndex = 1;
        } else {
            this.cycleComplete = true;
        }
    }

    /**
     * Gets the index of active player.
     * @return the index of the current player.
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Gets current turn number. 
     * @return current turn number.
     */
    public int getTurnNumber() {
        return this.currentTurn;
    }

    /**
     * Gets the number of maximum turns.
     * @return max turn number.
     */
    public int getMaxTurns() {
        return this.maxTurns;
    }

    /**
     * Gets the number of current turn.
     * @return current turn number.
     */
    public int getCurrentTurn() {
        return this.currentTurn;
    }
    
    /**
     * Says if both players finished their turn.
     * @return if cycle is complete, e.g. both players finished the turn.
     */
    public boolean isTurnCycleComplete() {
        return this.cycleComplete;
    }

}

