package com.marvelsnap.model;

import com.marvelsnap.util.Constants;

/**
 * Manages the turns of the game.
 * It keeps track of current turn number and whose turn it is.
 */
public class TurnManager {
    private int currentTurn;
    private int currentPlayerIndex;
    private int maxTurns;
    private boolean p1Played = false;
    private boolean p2Played = false;

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
     * 
     * @return Energy amount for the turn.
     */
    public int getEnergyForTurn() {
        return Math.min(this.currentTurn, this.maxTurns);
    }

    /**
     * Goes to the nextTurn.
     * It resets player index to 0.
     */
    public void nextTurn() {
        if (this.currentTurn <= this.maxTurns)
            currentTurn++;
        this.p1Played = false;
        this.p2Played = false;
        this.currentPlayerIndex = 0;
        System.out.println("[DEBUG] NextTurn: Inizia turno " + currentTurn);
    }

    /**
     * Switched the player who should play.
     * If player index was 0 (Player 1), it becomes 1 (Player 2). If player index
     * was 1, the cycle is complete.
     */
    public void switchPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex == 0) ? 1 : 0;
        System.out.println("[DEBUG] SwitchPlayer: Ora tocca a P" + (this.currentPlayerIndex + 1));
    }

    /**
     * Gets the index of active player.
     * 
     * @return the index of the current player.
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Gets current turn number.
     * 
     * @return current turn number.
     */
    public int getTurnNumber() {
        return this.currentTurn;
    }

    /**
     * Gets the number of maximum turns.
     * 
     * @return max turn number.
     */
    public int getMaxTurns() {
        return this.maxTurns;
    }

    /**
     * Gets the number of current turn.
     * 
     * @return current turn number.
     */
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Says if both players finished their turn.
     * 
     * @return if cycle is complete, e.g. both players finished the turn.
     */
    public boolean isTurnCycleComplete() {
        return p1Played && p2Played;
    }

    /**
     * Sets the maximum number of turns.
     * @param maxTurns  maximum number of turns.
     */
    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void registerMove(int playerIdx) {
        if (playerIdx == 0)
            p1Played = true;
        else
            p2Played = true;
        System.out.println("[DEBUG] RegisterMove: P" + (playerIdx+1) + " ha finito. Stati: P1=" + p1Played + ", P2=" + p2Played);
    }
}
