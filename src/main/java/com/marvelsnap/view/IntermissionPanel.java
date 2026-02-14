package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Screen that appears between turns to prevent players from seeing
 * each other's hidden cards.
 */
public class IntermissionPanel extends JPanel {
    private JButton btnReady;
    private JLabel infoLabel;

    /**
     * Constructor for IntermissionPanel.
     * Sets up the layout and UI warnings to prevent players from peaking during
     * turn changes.
     */
    public IntermissionPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 20)); /*Almost black*/
        this.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel stopIcon = new JLabel("STOP");
        stopIcon.setFont(new Font("Segoe UI Emoji", Font.BOLD, 60));
        stopIcon.setForeground(Color.RED);
        add(stopIcon, gbc);

        /*InfoLabel*/
        gbc.gridy = 1;
        infoLabel = new JLabel("In attesa del giocatore...");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoLabel.setForeground(Color.WHITE);
        add(infoLabel, gbc);

        /*Subtitle*/
        gbc.gridy = 2;
        JLabel subMsg = new JLabel("Non guardare lo schermo se non è il tuo turno!");
        subMsg.setFont(new Font("Arial", Font.ITALIC, 16));
        subMsg.setForeground(Color.LIGHT_GRAY);
        add(subMsg, gbc);

        /* "I am ready" button */
        gbc.gridy = 3;
        btnReady = new JButton("SONO PRONTO");
        btnReady.setFont(new Font("Arial", Font.BOLD, 20));
        btnReady.setBackground(new Color(0, 120, 200)); // Blu
        btnReady.setForeground(Color.WHITE);
        btnReady.setPreferredSize(new Dimension(200, 60));
        btnReady.setFocusPainted(false);
        add(btnReady, gbc);
    }

    /**
     * Links the "I am ready" button to the controller action.
     * 
     * @param action the listener to trigger
     */
    public void setReadyAction(ActionListener action) {
        btnReady.addActionListener(action);
    }

    /**
     * Screen that appears between turns to prevent players from seeing
     * each other's hidden cards.
     */
    public void setNextPlayerName(String name) {
        infoLabel.setText("È il turno di: " + name.toUpperCase());
    }
}
