package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The initial landing screen of the game.
 * Provides options to start a new match or exit the application.
 */
public class MenuPanel extends JPanel {
    private JButton btnStart;
    private JButton btnExit;

    /**
     * Constructs the menu panel with a centered layout.
     */
    public MenuPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        btnStart = new JButton("Nuova partita");
        btnExit = new JButton("Esci");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnStart, gbc);

        gbc.gridy = 1;
        add(btnExit, gbc);
    }

    /**
     * Assigns an action to the Start button.
     * 
     * @param action the listener to trigger when clicked
     */
    public void setStartAction(ActionListener action) {
        btnStart.addActionListener(action);
    }

    /**
     * Assigns an action to the Exit button.
     * 
     * @param action the listener to trigger when clicked
     */
    public void setExitAction(ActionListener action) {
        btnExit.addActionListener(action);
    }
}
