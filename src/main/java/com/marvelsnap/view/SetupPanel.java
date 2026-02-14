package com.marvelsnap.view;

import javax.swing.*;

import com.marvelsnap.util.DeckType;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel where players can enter their names and choose their decks.
 * It uses a GridBagLayout for precise positioning of UI elements.
 */
public class SetupPanel extends JPanel {
    private JTextField txtP1Name;
    private JComboBox<DeckType> cmbDeckP1;
    private JTextField txtP2Name;
    private JComboBox<DeckType> cmbDeckP2;
    private JButton btnPlay;

    /** 
     * Constructs the setup panel with all necessary input fields and buttons. 
     */
    public SetupPanel() {
        /*GridBag used to aline everything easily*/
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 60));

        /*Constraints used to place the elements */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("CONFIGURAZIONE PARTITA");
        title.setFont(new Font("Impact", Font.BOLD, 30));
        title.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        /*First player*/
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(createLabel("Nome Giocatore 1:"), gbc);

        gbc.gridx = 1;
        txtP1Name = new JTextField(20);
        txtP1Name.setName("p1NameField"); /*For tests*/
        add(txtP1Name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("Mazzo P1:"), gbc);

        gbc.gridx = 1;
        cmbDeckP1 = new JComboBox<>(DeckType.values());
        add(cmbDeckP1, gbc);

        /*Separator*/
        JSeparator sep = new JSeparator();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(sep, gbc);

        /*Second player*/
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        add(createLabel("Nome Giocatore 2:"), gbc);

        gbc.gridx = 1;
        txtP2Name = new JTextField(20);
        txtP2Name.setName("p2NameField"); /*For tests*/
        add(txtP2Name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createLabel("Mazzo P2:"), gbc);

        gbc.gridx = 1;
        cmbDeckP2 = new JComboBox<>(DeckType.values());
        cmbDeckP2.setSelectedItem(DeckType.VILLAINS); /*Different default*/
        add(cmbDeckP2, gbc);

        /*Start game*/
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);

        btnPlay = new JButton("INIZIA BATTAGLIA");
        btnPlay.setBackground(new Color(0, 150, 0)); /*Green*/
        btnPlay.setForeground(Color.WHITE);
        btnPlay.setFont(new Font("Arial", Font.BOLD, 20));
        btnPlay.setPreferredSize(new Dimension(200, 50));
        add(btnPlay, gbc);
    }

    /**
     * Helper method to create styled labels for the setup form.
     */
    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        return l;
    }

    /**
     * Links the game start button to the controller's validation logic.
     * 
     * @param action the listener for the start battle button
     */
    public void setPlayAction(ActionListener action) {
        btnPlay.addActionListener(action);
    }

    /** 
     * @return the text entered for Player 1's name 
     */
    public String getP1Name() {
        return txtP1Name.getText();
    }

    /** 
     * @return the selected DeckType for Player 1 
     */
    public DeckType getP1DeckType() {
        return (DeckType) cmbDeckP1.getSelectedItem();
    }

    /** 
     * @return the text entered for Player 2's name
     */
    public String getP2Name() {
        return txtP2Name.getText();
    }

    /** 
     * @return the selected DeckType for Player 2 
     */
    public DeckType getP2DeckType() {
        return (DeckType) cmbDeckP2.getSelectedItem();
    }

    /** 
     * @param name the name to set for Player 1 
     */
    public void setP1Name(String name) {
        this.txtP1Name.setText(name);
    }

    /** 
     * @param name the name to set for Player 2 
     */
    public void setP2Name(String name) {
        this.txtP2Name.setText(name);
    }
}
