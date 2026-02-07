package com.marvelsnap.model;
/**
 * Interface for the observers. It is implemented by the View to know when to update the GUI.
 */
public interface GameObserver {
    /**
     * Called when the game state changes. 
     */
    void onGameUpdated();

    /**
     * Called when turn changes or player switches.
     * @param playerIndex the index of the new player.
     */
    void onTurnChanged(int playerIndex);

    /**
     * Called when the game ends.
     * @param winnerName the name of the winner.
     */
    void onGameOver(String winnerName);
}