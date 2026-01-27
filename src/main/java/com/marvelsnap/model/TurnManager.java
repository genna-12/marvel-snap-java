package com.marvelsnap.model;

import com.marvelsnap.util.Constants;

public class TurnManager {
    private int currentTurn;
    private int currentPlayerIndex;
    private boolean cycleComplete = false;
    private int maxTurns;

    public TurnManager() {
        this.maxTurns = Constants.MAX_TURNS;
        this.currentTurn = 1;
        this.currentPlayerIndex = 0;
    }

    public int getEnergyForTurn() {
        return this.currentTurn;
    }

    public void nextTurn() {
        this.currentTurn++;
        this.currentPlayerIndex = 0;
        this.cycleComplete = false;
    }

    public void switchPlayer() {
        if(this.currentPlayerIndex == 0) {
            this.currentPlayerIndex = 1;
        } else {
            this.cycleComplete = true;
        }
    }
    
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /* Utility to get currentTurn number */
    public int getTurnNumber() {
        return this.currentTurn;
    }

    /*Utility to get maxTurns number */
    public int getMaxTurns() {
        return this.maxTurns;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }
    
    public boolean isTurnCycleComplete() {
        return this.cycleComplete;
    }

}

