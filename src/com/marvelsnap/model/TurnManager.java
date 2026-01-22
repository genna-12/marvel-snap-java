package com.marvelsnap.model;

public class TurnManager {
    private int currentTurn;
    private int currentPlayerIndex;
    private int maxTurns;

    public TurnManager() {
        this.maxTurns = 6;
        this.currentTurn = 1;
        this.currentPlayerIndex = 0;
    }

    public int getEnergyForTurn() {
        return 0;
    }

    public void nextTurn() {
        return;
    }

    public void switchPlayer() {
        return;
    }
    
    public int getCurrentPlayerIndex() {
        return 0;
    }
    
    public boolean isTurnCycleComplete() {
        return false;
    }
}
