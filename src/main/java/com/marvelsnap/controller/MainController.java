package com.marvelsnap.controller;

import java.awt.event.ActionEvent;

import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private Game game;

    public MainController() {
        this.mainFrame = new MainFrame();
        initListeners();
    }

    private void initListeners() {
        mainFrame.getMenuPanel().setStartAction((ActionEvent e) -> {
            mainFrame.showScreen("SETUP");
        });

        mainFrame.getMenuPanel().setExitAction((ActionEvent e) -> {
            System.exit(0);
        });

        // setup panel (sto piangendo)
        mainFrame.getSetupPanel().setPlayAction((ActionEvent e) -> {
            String p1Name = mainFrame.getSetupPanel().getP1Name();
            DeckType p1Deck = mainFrame.getSetupPanel().getP1DeckType();
            String p2Name = mainFrame.getSetupPanel().getP2Name();
            DeckType p2Deck = mainFrame.getSetupPanel().getP2DeckType();
            
            // controllo sui nomi
            if (p1Name.trim().isEmpty() || p2Name.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(mainFrame, 
                    "Inserisci i nomi di entrambi i giocatori!", 
                    "Errore Setup", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Se la và alloea parte
            onSetupConfirmed(p1Name, p1Deck, p2Name, p2Deck);
        });

        // intermission panel
        mainFrame.getGamePanel().getIntermissionPanel().setReadyAction((ActionEvent e) -> {
            mainFrame.getGamePanel().onReadyToPlay();
        });
    }
    

    public void startApp() {
        mainFrame.setVisible(true);
        mainFrame.showScreen("MENU");
    }

    public void onSetupConfirmed(String p1Name, DeckType d1, String p2Name, DeckType d2) {
        System.out.println("Avvio partita tra: " + p1Name + " e " + p2Name);
        mainFrame.getGamePanel().setPlayerNames(p1Name, p2Name);

        try {
            this.game = new Game(); 
            game.startGame(p1Name, d1, p2Name, d2); 
            
            GameController gc = new GameController(game, mainFrame.getGamePanel());
            mainFrame.getGamePanel().setController(gc);
            
            mainFrame.getGamePanel().updateView(game);
            
        } catch (Exception e) {
            System.err.println("WARN: Il Backend (Model) non è ancora pronto. Mostro solo la UI vuota.");
            e.printStackTrace(); 
        }

        mainFrame.showScreen("GAME");


        // passa a gamecontroller
    }
}
