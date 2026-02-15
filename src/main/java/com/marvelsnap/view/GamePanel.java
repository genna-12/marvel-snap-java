package com.marvelsnap.view;

import javax.swing.*;
import javax.swing.border.Border;

import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;
import com.marvelsnap.model.Player;
import com.marvelsnap.util.Constants;

import java.awt.*;

/**
 * Main game screen that displays the board, the hand, and game info.
 * It implements GameObserver to react to model changes automatically.
 */
public class GamePanel extends JPanel implements GameObserver {

    private IntermissionPanel intermissionPanel;
    private BoardPanel boardPanel;
    private HandPanel handPanel;
    private CardLayout cardLayout;
    private JPanel infoPanel;
    private GameController controller;
    private JPanel activeGameContainer;
    private String p1Name = "Player 1";
    private String p2Name = "Player 2";
    private JLabel lblTurnInfo;
    private JLabel lblEnergyInfo;
    private JLabel lblPlayerName;
    private Runnable backToMenuAction;

    /**
     * Constructor for GamePanel.
     * Initializes the sub-panels (Board, Hand, Intermission) and sets up the
     * turn-end button logic.
     */
    public GamePanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        intermissionPanel = new IntermissionPanel();
        boardPanel = new BoardPanel();
        handPanel = new HandPanel();
        createInfoPanel();

        activeGameContainer = new JPanel(new BorderLayout());
        activeGameContainer.add(infoPanel, BorderLayout.NORTH);
        activeGameContainer.add(boardPanel, BorderLayout.CENTER);
        activeGameContainer.add(handPanel, BorderLayout.SOUTH);

        add(activeGameContainer, "Board");
        add(intermissionPanel, "Intermission");

        cardLayout.show(this, "Board");

        /*End turn*/
        JButton btnEndTurn = new JButton("TERMINA TURNO");
        btnEndTurn.setBackground(new Color(200, 50, 50)); /*Dark red*/
        btnEndTurn.setForeground(Color.WHITE);
        btnEndTurn.setFont(new Font("Arial", Font.BOLD, 14));
        btnEndTurn.setFocusPainted(false);

        btnEndTurn.addActionListener(e -> {
            if (controller != null) {
                controller.onEndTurnClicked();
            }
        });
        activeGameContainer.add(btnEndTurn, BorderLayout.EAST);
    }

    /**
     * Connects the controller to the panel and its sub-components.
     */
    public void setController(GameController controller) {
        this.controller = controller;
        if (boardPanel != null) {
            boardPanel.setController(controller);
        }
        if (handPanel != null) {
            handPanel.setController(controller);
        }
    }

    /**
     * Creates the top information bar showing turns, current player, and energy.
     * Styled with a dark theme to match the game aesthetics.
     */
    private void createInfoPanel() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setPreferredSize(new Dimension(0, 50));
        Border lineaGrigia = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border spazioInterno = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(lineaGrigia, spazioInterno));

        /*Left*/
        lblTurnInfo = new JLabel("TURNO: --/" + Constants.MAX_TURNS);
        lblTurnInfo.setName("labelTurno"); /*For tests*/
        lblTurnInfo.setForeground(Color.WHITE);
        lblTurnInfo.setFont(new Font("Arial", Font.BOLD, 16));

        /*Center*/
        lblPlayerName = new JLabel("Caricamento...");
        lblPlayerName.setForeground(Color.ORANGE);
        lblPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);

        /*Right*/
        lblEnergyInfo = new JLabel("ENERGIA: --");
        lblEnergyInfo.setForeground(Color.CYAN);
        lblEnergyInfo.setFont(new Font("Arial", Font.BOLD, 16));

        infoPanel.add(lblTurnInfo, BorderLayout.WEST);
        infoPanel.add(lblPlayerName, BorderLayout.CENTER);
        infoPanel.add(lblEnergyInfo, BorderLayout.EAST);
    }

    /**
     * Sets the player names to be displayed in the info panel and intermission
     * screen.
     * 
     * @param p1Name the name of player 1
     * @param p2Name the name of player 2
     */
    public void setPlayerNames(String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    /**
     * Updates all UI components (board, hand, labels) based on the current game
     * state.
     * 
     * @param game The current state of the game model.
     */
    public void updateView(Game game) {
        if (game == null || game.getTurnManager() == null)
            return;
        int turn = game.getTurnManager().getCurrentTurn();
        int playerIdx = game.getTurnManager().getCurrentPlayerIndex();
        Player currentPlayer = game.getPlayer(playerIdx);
        String name = (playerIdx == 0) ? p1Name : p2Name;
        int energy = (currentPlayer != null) ? currentPlayer.getCurrentEnergy() : 0;

        /*Update infoPanel */
        lblTurnInfo.setText("TURNO " + turn + "/" + game.getTurnManager().getMaxTurns()); /*In case of Limbo location*/
        lblPlayerName.setText(name.toUpperCase());
        lblEnergyInfo.setText("ENERGIA: " + energy);
        /*BoardPanel*/
        if (boardPanel != null) {
            if (game.getLocations() != null) {
                boardPanel.refresh(game.getLocations(), playerIdx);
            }
        }
        /*HandPanel*/
        if (handPanel != null) {
            /*Shows only current player's hand*/
            if (currentPlayer != null && currentPlayer.getHand() != null) {
                handPanel.setHand(currentPlayer.getHand());
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Callback called when the player is ready to resume after intermission.
     */
    public void onReadyToPlay() {
        showBoard();
    }

    /** 
     * Switches the view to the game board. 
     */
    public void showBoard() {
        cardLayout.show(this, "Board");
    }

    /** 
     * Switches the view to the intermission/hidden screen. 
     */
    public void showIntermission() {
        cardLayout.show(this, "Intermission");
    }

    /**
     * Displays a dialog box when the game is over.
     * 
     * @param winnerName The name of the player who won.
     * @return the option chosen by the user (Return to Menu or Exit).
     */
    public int showEndGame(String winnerName) {
        Object[] options = { "Torna al Menu", "Esci" };
        return JOptionPane.showOptionDialog(
                this,
                "PARTITA TERMINATA!\nVincitore: " + winnerName,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /** 
     * Repaints the game. 
     */
    @Override
    public void onGameUpdated() {
        repaint();
    }

    /**
     * Handles the turn change by updating the intermission screen
     * with the next player's name.
     */
    @Override
    public void onTurnChanged(int playerIndex) {
        String nextName = (playerIndex == 0) ? p1Name : p2Name;
        intermissionPanel.setNextPlayerName(nextName);
        showIntermission();
    }

    /**
     * Handles the end of the game by showing the winner dialog
     * and managing the player's choice to restart or exit.
     */
    @Override
    public void onGameOver(String winnerName) {

        int choice = showEndGame(winnerName);

        if (choice == 0) {
            /*Back to menu*/
            if (backToMenuAction != null) {
                backToMenuAction.run();
            }
        } else {
            /*Close the app*/
            System.exit(0);
        }
    }

    /** 
     * @param action the logic to execute when returning to the main menu 
     */
    public void setBackToMenuAction(Runnable action) {
        this.backToMenuAction = action;
    }

    /**
     * @return the intermission panel to allow the controller to set the next
     *         player's name
     */
    public IntermissionPanel getIntermissionPanel() {
        return intermissionPanel;
    }

    /**
     * Resets the view to its initial state for a new game.
     */
    public void resetView() {
        this.p1Name = "Player 1";
        this.p2Name = "Player 2";

        lblTurnInfo.setText("TURNO: --/" + Constants.MAX_TURNS);
        lblPlayerName.setText("CARICAMENTO...");
        lblEnergyInfo.setText("ENERGIA: --");

        if (boardPanel != null) {
            boardPanel.reset();
        }

        if (handPanel != null) {
            handPanel.removeAll();
            handPanel.revalidate();
            handPanel.repaint();
        }

        showBoard();

        revalidate();
        repaint();
    }

    /**
     * Gets the boardPanel field.
     * 
     * @return the boardPanel field.
     */
    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    /**
     * Gets the handPanel field.
     * 
     * @return the handPanel field.
     */
    public HandPanel getHandPanel() {
        return this.handPanel;
    }
}