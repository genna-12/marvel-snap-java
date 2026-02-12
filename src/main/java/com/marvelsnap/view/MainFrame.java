package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of the Marvel Snap application.
 * It uses a CardLayout to switch between the Menu, Setup, and Game screens.
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private SetupPanel setupPanel;

    /**
     * Constructs the main frame, sets up the window properties,
     * and initializes all navigation panels.
     */
    public MainFrame() {
        setTitle("Marvel Snap");
        setSize(1280, 720);
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

    /**
     * Switches the visible screen in the container.
     * 
     * @param screenName The identifier of the panel to display (e.g., "MENU",
     *                   "GAME").
     */
    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    /**
     * Provides access to the menu panel for setting up action listeners.
     * 
     * @return the menu panel instance
     */
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    /**
     * Provides access to the setup panel for setting up action listeners.
     * 
     * @return the setup panel instance
     */
    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    /**
     * Provides access to the game panel for setting up the game controller.
     * 
     * @return the game panel instance
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
