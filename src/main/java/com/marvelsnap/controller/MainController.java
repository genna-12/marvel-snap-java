package com.marvelsnap.controller;

import java.awt.event.ActionEvent;

import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.MainFrame;

/**
 * Main controller of the application.
 * It manages the transitions between different screens (Menu, Setup, Game)
 * and initializes the main game components.
 */
public class MainController {
    private MainFrame mainFrame;
    private Game game;

    /**
     * Initializes the controller by creating the main frame and setting up
     * the action listeners for the various UI components.
     */
    public MainController() {
        this.mainFrame = new MainFrame();
        initListeners();
    }

    /**
     * Sets up the event listeners for all the panels in the main frame.
     * This includes menu navigation, setup confirmation, and returning to the menu.
     */
    private void initListeners() {
        // Menu navigation
        mainFrame.getMenuPanel().setStartAction((ActionEvent e) -> {
            mainFrame.showScreen("SETUP");
        });

        mainFrame.getMenuPanel().setExitAction((ActionEvent e) -> {
            System.exit(0);
        });

        // Rules Panel
        mainFrame.getMenuPanel().setRulesAction((ActionEvent e) -> {
            mainFrame.showScreen("RULES");
        });

        mainFrame.getRulesPanel().setBackAction((ActionEvent e) -> {
            mainFrame.showScreen("MENU");
        });

        // Setup Panel
        mainFrame.getSetupPanel().setPlayAction((ActionEvent e) -> {
            String p1Name = mainFrame.getSetupPanel().getP1Name();
            DeckType p1Deck = mainFrame.getSetupPanel().getP1DeckType();
            String p2Name = mainFrame.getSetupPanel().getP2Name();
            DeckType p2Deck = mainFrame.getSetupPanel().getP2DeckType();

            // Validation check on player names
            if (p1Name.trim().isEmpty() || p2Name.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(mainFrame,
                        "Inserisci i nomi di entrambi i giocatori!",
                        "Errore Setup",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            onSetupConfirmed(p1Name, p1Deck, p2Name, p2Deck);
        });

        // Intermission Panel
        mainFrame.getGamePanel().getIntermissionPanel().setReadyAction((ActionEvent e) -> {
            mainFrame.getGamePanel().onReadyToPlay();
        });

        // Return to main menu after the game ends
        mainFrame.getGamePanel().setBackToMenuAction(() -> {
            System.out.println("Torno al Menu Principale...");
            mainFrame.getGamePanel().resetView();

            this.game = null;
            startApp();
        });
    }

    /**
     * Shows the main window and displays the initial menu screen.
     */
    public void startApp() {
        mainFrame.setVisible(true);
        mainFrame.showScreen("MENU");
    }

    /**
     * Handles the game start after players have confirmed their names and decks.
     * It initializes the Game model and the GameController.
     * 
     * @param p1Name Name of the first player
     * @param d1     Deck type for the first player
     * @param p2Name Name of the second player
     * @param d2     Deck type for the second player
     */
    public void onSetupConfirmed(String p1Name, DeckType d1, String p2Name, DeckType d2) {
        System.out.println("Avvio partita tra: " + p1Name + " e " + p2Name);

        mainFrame.getGamePanel().resetView();

        mainFrame.getGamePanel().setPlayerNames(p1Name, p2Name);

        try {
            this.game = new Game();
            game.startGame(p1Name, d1, p2Name, d2);

            // Link the model and the view through a new GameController
            GameController gc = new GameController(game, mainFrame.getGamePanel());
            mainFrame.getGamePanel().setController(gc);

            //this.game.addObserver(mainFrame.getGamePanel());

            mainFrame.getGamePanel().updateView(game);

        } catch (Exception e) {
            System.err.println("errore prolly è il model");
            e.printStackTrace();
        }

        mainFrame.showScreen("GAME");
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
