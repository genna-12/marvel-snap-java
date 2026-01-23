package com.marvelsnap.view;

import javax.swing.*;

import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;

import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    
    private IntermissionPanel intermissionPanel;
    private BoardPanel boardPanel;
    private HandPanel handPanel;
    private CardLayout cardLayout;
    private JPanel infoPanel;
    private GameController controller;
    private JPanel activeGameContainer; // Aggiungere campo all'UML

    public GamePanel(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        intermissionPanel = new IntermissionPanel();
        boardPanel = new BoardPanel();
        handPanel = new HandPanel();

        infoPanel = new JPanel();
        infoPanel.add(new JLabel("Info Panel"));
        infoPanel.setBackground(Color.LIGHT_GRAY);

        activeGameContainer = new JPanel(new BorderLayout());

        add(activeGameContainer, "Board");
        add(intermissionPanel, "Intermission");
    }
    
    // Aggiungere metodo all'UML
    public void setController(GameController controller){
        this.controller = controller;
    }

    public void updateView(Game game){}

    public void showBoard(){
        cardLayout.show(this, "Board");
    }

    public void showIntermission(){
        cardLayout.show(this, "Intermission");
    }

    public void showEndGame(String message){
        JOptionPane.showMessageDialog(this, "Il vincitore è: " + message);
    }

    @Override public void onGameUpdated() {}
    @Override public void onTurnChanged(int playerIndex) {}
    @Override public void onGameOver(String winnerName) {}
}