package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class IntermissionPanel extends JPanel{
    private JButton btnReady;

    public IntermissionPanel(){
        setLayout(new BorderLayout());
        JLabel msg = new JLabel("Turno finito! Passa il computer all'altro giocatore.");
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        
        btnReady = new JButton("Pronto");
        btnReady.setFont(new Font("Arial", Font.PLAIN, 18));
        
        add(msg, BorderLayout.CENTER);
        add(btnReady, BorderLayout.SOUTH);
    }

    public void setReadyAction(ActionListener action){
        btnReady.addActionListener(action);
    }
}
