package com.marvelsnap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel that explains the rules of marvel snap.
 */
public class RulesPanel extends JPanel {
    private JButton btnBack;

    public RulesPanel() {
        setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JTextArea rulesArea = new JTextArea();
        rulesArea.setEditable(false);
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
        rulesArea.setFont(new Font("Arial", Font.PLAIN, 22));

        String text = "REGOLE DI MARVEL SNAP - UNA BREVE GUIDA PER L'UTENTE \n\n" +
                "1. OBIETTIVO:\n" +
                "L'obiettivo del gioco è vincere in due location su tre." +
                "Per vincere in una location bisogna semplicemente totalizzare un punteggio più alto dell'avversario.\n" +
                "2. CARTE:\n" +
                "Puoi giocare un massimo di 4 carte per location." +
                "Ogni carta ha un costo (indicato a sinistra in blu) e una forza (indicata a destra in arancione) " +
                "e alcune di esse hanno anche abilità speciali." +
                "Ogni carta che ha un costo di energia inferiore o uguale all'energia attualmente disponibile"+
                " può essere giocata in qualsiasi location, " +
                "quindi puoi giocare anche più di una carta per turno.\n" +
                "3. LOCATION:\n" +
                "Ci sono in totale 3 location, " +
                "che vengono rivelate nei primi tre turni di gioco (uno per turno)." +
                "Ogni location ha un proprio effetto, ognuno dei quali può produrre interazioni differenti.\n" +
                "4. PROGRESSIONE DEI TURNI:\n" +
                "Ottieni +1 energia e peschi una carta ogni turno.\n" +
                "5. DURATA:\n" +
                "Il gioco dura 6 turni.\n" +
                "6. REGOLE PER LO SPAREGGIO\n" +
                "Se entrambi i giocatori controllano una location a testa e il terzo è in pareggio, " +
                "il vincitore è determinato dalla differenza di forza totale in tutte " +
                "e tre le location combinate. Il giocatore con la forza totale più alta vince.";
        
        rulesArea.setText(text);

        JScrollPane scrollPane = new JScrollPane(rulesArea);
        add(scrollPane, BorderLayout.CENTER);

        btnBack = new JButton("Ritorna al menu");
        add(btnBack, BorderLayout.SOUTH);
    }

    /**
     * Sets the action for the back button.
     * @param action
     */
    public void setBackAction(ActionListener action) {
        btnBack.addActionListener(action);
    }
}