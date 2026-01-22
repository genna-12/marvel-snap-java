package com.marvelsnap.model;

public class TurnManager {
    private int currentTurn = 1;
    private int currentPlayerIndex = 0;
    private int maxTurns = 0;

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
