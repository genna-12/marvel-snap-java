package com.marvelsnap.view;

import javax.swing.*;
import javax.swing.border.Border;

import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;
import com.marvelsnap.model.Player;
import com.marvelsnap.util.Constants;

import java.awt.*;

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

        // fine turno
        JButton btnEndTurn = new JButton("TERMINA TURNO");
        btnEndTurn.setBackground(new Color(200, 50, 50)); // Rosso scuro
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

    // Aggiungere metodo all'UML
    public void setController(GameController controller) {
        this.controller = controller;
        if (boardPanel != null) {
            boardPanel.setController(controller); 
        }
    }

    // Aggiungere metodo all'UML
    private void createInfoPanel() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setPreferredSize(new Dimension(0, 50));
        Border lineaGrigia = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border spazioInterno = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(lineaGrigia, spazioInterno));

        // Sinistra
        lblTurnInfo = new JLabel("TURNO: --/" + Constants.MAX_TURNS);
        lblTurnInfo.setForeground(Color.WHITE);
        lblTurnInfo.setFont(new Font("Arial", Font.BOLD, 16));

        // Centro
        lblPlayerName = new JLabel("Caricamento...");
        lblPlayerName.setForeground(Color.ORANGE);
        lblPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);

        // Destra
        lblEnergyInfo = new JLabel("ENERGIA: --");
        lblEnergyInfo.setForeground(Color.CYAN);
        lblEnergyInfo.setFont(new Font("Arial", Font.BOLD, 16));

        infoPanel.add(lblTurnInfo, BorderLayout.WEST);
        infoPanel.add(lblPlayerName, BorderLayout.CENTER);
        infoPanel.add(lblEnergyInfo, BorderLayout.EAST);
    }

    // Aggiungere metodo all'UML
    public void setPlayerNames(String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    public void updateView(Game game) {
        if (game == null || game.getTurnManager() == null)
            return;
        int turn = game.getTurnManager().getCurrentTurn();
        int playerIdx = game.getTurnManager().getCurrentPlayerIndex();
        Player currentPlayer = game.getPlayer(playerIdx);
        String name = (playerIdx == 0) ? p1Name : p2Name;
        // non passo currentPlayer.getCurrentEnergy() per evitare null pointer exception
        // se p3 è indietro
        int energy = (currentPlayer != null) ? currentPlayer.getCurrentEnergy() : 0;

        // aggiorno
        // infopanel
        lblTurnInfo.setText("TURNO " + turn + "/" + Constants.MAX_TURNS);
        lblPlayerName.setText(name.toUpperCase());
        lblEnergyInfo.setText("ENERGIA: " + energy);
        // boardpanel
        if (boardPanel != null) {
            if (game.getLocations() != null) { // questa condizione non dovrebbe mai verificarsi ma serve per debug se
                                               // altri non hanno ancora finito
                boardPanel.refresh(game.getLocations());
            }
        }
        // handpanel
        if (handPanel != null) {
            // Mostra SOLO la mano del giocatore corrente (aggiungo if per debug per evitare
            // nullpointer)
            if (currentPlayer != null && currentPlayer.getHand() != null) {
                handPanel.setHand(currentPlayer.getHand(), this.controller);
            }
        }

        revalidate();
        repaint();
    }

    public void onReadyToPlay() {
        showBoard();
    }

    public void showBoard() {
        cardLayout.show(this, "Board");
    }

    public void showIntermission() {
        cardLayout.show(this, "Intermission");
    }

    public void showEndGame(String message) {
        JOptionPane.showMessageDialog(this, "Il vincitore è: " + message);
    }

    @Override
    public void onGameUpdated() {
        repaint();
    }

    @Override
    public void onTurnChanged(int playerIndex) {
        String nextName = (playerIndex == 0) ? p1Name : p2Name;
        intermissionPanel.setNextPlayerName(nextName);
        showIntermission();
    }

    @Override
    public void onGameOver(String winnerName) {
        showEndGame("Il vincitore è: " + winnerName);
    }

    public IntermissionPanel getIntermissionPanel() {
        return intermissionPanel;
    }
}