package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private SetupPanel setupPanel;

    public MainFrame(){
        setTitle("Marvel Snap");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        menuPanel = new MenuPanel();
        setupPanel = new SetupPanel();
        gamePanel = new GamePanel();

        mainContainer.add(menuPanel, "MENU");
        mainContainer.add(setupPanel, "SETUP");
        mainContainer.add(gamePanel, "GAME");

        add(mainContainer);
    }

    public void showScreen(String screenName){
        cardLayout.show(mainContainer, screenName);
    }

    // Getters
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    
    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
