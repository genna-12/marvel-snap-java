package com.marvelsnap.model;
public interface GameObserver {
    void onGameUpdated();
    void onTurnChanged(int playerIndex);
    void onGameOver(String winnerName);
}