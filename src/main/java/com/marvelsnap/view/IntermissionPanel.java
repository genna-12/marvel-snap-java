package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class IntermissionPanel extends JPanel{
    private JButton btnReady;
    private JLabel infoLabel; // per mostrare il nome del prossimo giocatore lo aggiungo all'uml

    // ricostruisco il pannello che il costruttore autogenerato non mi piaceva
    public IntermissionPanel(){
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 20)); // Quasi nero
        this.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel stopIcon = new JLabel("STOP");
        stopIcon.setFont(new Font("Segoe UI Emoji", Font.BOLD, 60));
        stopIcon.setForeground(Color.RED);
        add(stopIcon, gbc);

        // info label
        gbc.gridy = 1;
        infoLabel = new JLabel("In attesa del giocatore...");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoLabel.setForeground(Color.WHITE);
        add(infoLabel, gbc);

        // sottotitolo
        gbc.gridy = 2;
        JLabel subMsg = new JLabel("Non guardare lo schermo se non è il tuo turno!");
        subMsg.setFont(new Font("Arial", Font.ITALIC, 16));
        subMsg.setForeground(Color.LIGHT_GRAY);
        add(subMsg, gbc);

        // bottone sono pronto
        gbc.gridy = 3;
        btnReady = new JButton("SONO PRONTO");
        btnReady.setFont(new Font("Arial", Font.BOLD, 20));
        btnReady.setBackground(new Color(0, 120, 200)); // Blu
        btnReady.setForeground(Color.WHITE);
        btnReady.setPreferredSize(new Dimension(200, 60));
        btnReady.setFocusPainted(false);
        add(btnReady, gbc);
    }

    public void setReadyAction(ActionListener action){
        btnReady.addActionListener(action);
    }

    // devvo aggiungerlo all'uml, è per cambiare il nome del giocatore
    public void setNextPlayerName(String name) {
        infoLabel.setText("È il turno di: " + name.toUpperCase());
    }
}
