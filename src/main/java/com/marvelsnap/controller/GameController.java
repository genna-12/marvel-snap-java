package com.marvelsnap.controller;

import com.marvelsnap.model.*;
import com.marvelsnap.view.*;

/**
 * Class that handles the communication between the Game(Model) and the
 * GamePanel(View).
 * It listens to user clicks and decides what to do using InputState.
 * It also updates the view when the model changes.
 */
public class GameController implements GameObserver {
    private final Game game;
    private final GamePanel view;
    private InputState inputState;
    private Card selectedCard;

    /**
     * Class constructor.
     * 
     * @param game the model.
     * @param view the view.
     */
    public GameController(Game game, GamePanel view) {
        this.game = game;
        this.view = view;
        this.inputState = InputState.IDLE;

        this.game.addObserver(this);
        // errore grave non passare noi stessi alla vieww
        this.view.setController(this);
        this.view.getIntermissionPanel().setReadyAction(e -> onIntermissionReadyClicked());
    }

    /**
     * Handles the click of a card.
     * It selects the card if we are in IDLE, otherwhise it deselects the card.
     * 
     * @param card the card selected.
     */
    public void onCardClicked(Card card) {
        System.out.println("[CTRL-DEBUG] Tentativo selezione: " + card.getName() + " | Stato attuale: " + inputState);
        if (inputState == InputState.WAITING_FOR_SWAP) {
            System.out.println("[CTRL-DEBUG] Bloccato perché in attesa di swap.");
            return;
        }

        /* If any card is selected, let's select one */
        // posso cliccare solo se sono in idle oho gia selezioanto
        if (this.inputState == InputState.IDLE || this.inputState == InputState.CARD_SELECTED) {
            if (this.selectedCard != null && this.selectedCard.equals(card)) {
                // deseleziono
                this.selectedCard = null;
                this.inputState = InputState.IDLE;
                System.out.println("[CTRL] Carta deselezionata: " + card.getName());
            } else {
                // seleziono
                this.selectedCard = card;
                this.inputState = InputState.CARD_SELECTED;
                System.out.println("[CTRL] Selezionata: " + card.getName());
            }
        }
    }

    /**
     * Handles the click on a location.
     * If a card was selected, it plays it through the model.
     * 
     * @param locIdx the index of the selected location.
     */
    public void onLocationClicked(int locIdx) {
        System.out.println("[CTRL] Click su Location " + locIdx);
        /* Tries to play a card on a location */
        if (this.inputState == InputState.CARD_SELECTED && this.selectedCard != null) {
            boolean success = game.playCard(selectedCard, locIdx);

            /* After the card is played, reset the selection */
            if (success) {
                System.out.println("[CTRL] Carta giocata con successo!");
                this.selectedCard = null;
                this.inputState = InputState.IDLE;
            } else {
                System.out.println(
                        "[CTRL] Giocata fallita. Controlla se hai abbastanza energia o se la location è piena.");
            }
        }
    }

    /**
     * Handles the click on endTurn button.
     */
    public void onEndTurnClicked() {
        if (this.inputState == InputState.IDLE || this.inputState == InputState.CARD_SELECTED) {
            System.out.println("[CTRL] Fine turno cliccata.");
            this.inputState = InputState.WAITING_FOR_SWAP;
            this.view.showIntermission();
            game.endTurn();
        }
    }

    /**
     * It is for the ready button in the intermission panel.
     * It updates the view for the next player.
     */
    public void onIntermissionReadyClicked() {
        /* Changes the view for the next Player */
        if (this.inputState == InputState.WAITING_FOR_SWAP) {
            System.out.println("[CTRL] Sono Pronto cliccato.");
            this.inputState = InputState.IDLE;
            this.game.setWaitingForSwap(false);

            this.view.showBoard();
            this.view.updateView(this.game);
        }
    }

    /* GameObserver implementation */

    /**
     * Updates the GUI when something happens in the game.
     */
    @Override
    public void onGameUpdated() {
        /* Modified in order to avoid to show for a bit player 2's hand */
        if (this.inputState == InputState.WAITING_FOR_SWAP) {
            System.out.println("[CTRL] onGameUpdated ignorato perché siamo in Intermission.");
            return;
        }
        this.view.updateView(this.game);
    }

    /**
     * Changes the state of the controller when the turn changes.
     */
    @Override
    public void onTurnChanged(int playerIndex) {
        if (this.game.isWaitingForSwap()) {
            System.out.println("[CTRL] Attivo Sipario (Intermission)");
            this.inputState = InputState.WAITING_FOR_SWAP;
            this.view.showIntermission();
        } else {
            this.inputState = InputState.IDLE;
            this.view.showBoard();
            this.view.updateView(this.game);
        }
    }

    /**
     * Called when the game is over and it's time to show the winner.
     * 
     * @param winnerName the name of the winner.
     */
    @Override
    public void onGameOver(final String winnerName) {
        this.inputState = InputState.GAME_OVER;
        this.view.showEndGame(winnerName);
    }
}