package com.marvelsnap.model;
public interface GameObserver {
    /*Signal to refresh the gameboard */
    void onGameUpdated();

    /*Signal to change turn */
    void onTurnChanged(int playerIndex);

    /*Signal endGame and final popup */
    void onGameOver(String winnerName);
}