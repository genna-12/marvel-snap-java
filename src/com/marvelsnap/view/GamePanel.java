package com.marvelsnap.view;

import com.marvelsnap.model.GameObserver;
import javax.swing.JFrame;

public class GamePanel extends JFrame implements GameObserver {
    @Override public void onGameUpdated() {}
    @Override public void onTurnChanged(int playerIndex) {}
    @Override public void onGameOver(String winnerName) {}
}