package com.marvelsnap.controller;

import java.awt.event.ActionEvent;

import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private GameController gameController;
    private Game game;

    public MainController() {
        this.mainFrame = new MainFrame();
        initListeners();
    }

    private void initListeners() {
        mainFrame.getMenuPanel().setStartAction((ActionEvent e) -> {
            mainFrame.showScreen("Setup");
        });

        mainFrame.getMenuPanel().setExitAction((ActionEvent e) -> {
            System.exit(0);
        });

        // daa implementare setup panel
    }

    public void startApp() {
        mainFrame.setVisible(true);
        mainFrame.showScreen("MENU");
    }

    public void onSetupConfirmed(String p1Name, DeckType d1, String p2Name, DeckType d2) {
        System.out.println("Avvio partita tra: " + p1Name + " e " + p2Name);
        // passa a gamecontroller
    }
}
