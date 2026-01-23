package com.marvelsnap.view;

import javax.swing.*;

import com.marvelsnap.util.DeckType;

import java.awt.*;
import java.awt.event.ActionListener;

public class SetupPanel extends JPanel{
    private JTextField txtP1Name;
    private JComboBox<DeckType> cmbDeckP1;
    private JTextField txtP2Name;
    private JComboBox<DeckType> cmbDeckP2;
    private JButton btnPlay;

    public SetupPanel(){
        setLayout(new GridLayout(3, 2, 10, 10));
        txtP1Name = new JTextField("Giocatore 1");
        cmbDeckP1 = new JComboBox<>(DeckType.values());
        
        txtP2Name = new JTextField("Giocatore 2");
        cmbDeckP2 = new JComboBox<>(DeckType.values());
        
        btnPlay = new JButton("Inizia Battaglia");

        add(new JLabel("Nome P1:"));
        add(txtP1Name);
        add(new JLabel("Mazzo P1:"));
        add(cmbDeckP1);
        add(new JLabel("Nome P2:"));
        add(txtP2Name);
        add(new JLabel("Mazzo P2:"));
        add(cmbDeckP2);
        
        add(new JLabel("")); // Spaziatore
        add(btnPlay);
    }

    public void setPlayAction(ActionListener action){
        btnPlay.addActionListener(action);
    }

    // Getters
    public String getP1Name() {
        return txtP1Name.getText();
    }

    public DeckType getP1DeckType() {
        return (DeckType) cmbDeckP1.getSelectedItem();
    }

    public String getP2Name() {
        return txtP2Name.getText();
    }

    public DeckType getP2DeckType() {
        return (DeckType) cmbDeckP2.getSelectedItem();
    }
}
