package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{
    private JButton btnStart;
    private JButton btnExit;

    public MenuPanel(){
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

    public void setStartAction(ActionListener action){
        btnStart.addActionListener(action);
    }

    public void setExitAction(ActionListener action){
        btnExit.addActionListener(action);
    }
}
