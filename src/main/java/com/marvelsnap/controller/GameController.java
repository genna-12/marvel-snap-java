package com.marvelsnap.controller;

import com.marvelsnap.model.*;
import com.marvelsnap.view.*;

/**
 * Class that handles the communication between the Game(Model) and the GamePanel(View).
 * It listens to user clicks and decides what to do using InputState.
 * It also updates the view when the model changes.
 */
public class GameController implements GameObserver{
    private final Game game;
    private final GamePanel view;
    private InputState inputState;
    private Card selectedCard;

    /**
     * Class constructor.
     * @param game the model.
     * @param view the view.
     */
    public GameController(final Game game, final GamePanel view) {
        this.game = game;
        this.view = view;
        this.inputState = InputState.IDLE;

        this.game.addObserver(this);
    }

    /**
     * Handles the click of a card.
     * It selects the card if we are in IDLE, otherwhise it deselects the card.
     * @param card the card selected.
     */
    public void onCardClicked(final Card card) {
        /*If any card is selected, let's select one */       
        if(this.inputState == InputState.IDLE) {
            this.selectedCard = card;
            this.inputState = InputState.CARD_SELECTED;
        } else if(this.inputState == InputState.CARD_SELECTED && this.selectedCard == card) {
            /*Remove the selection */
            this.selectedCard = null;
            this.inputState = InputState.IDLE;
        }
    }

    /**
     * Handles the click on a location.
     * If a card was selected, it plays it through the model.
     * @param locIdx the index of the selected location.
     */
    public void onLocationClicked(final int locIdx) {
        /*Tries to play a card on a location */
        if(this.inputState == InputState.CARD_SELECTED && this.selectedCard != null) {
            boolean success = game.playCard(selectedCard, locIdx);

            /*After the card is played, reset the selection */
            if(success) {
                this.selectedCard = null;
                this.inputState = InputState.IDLE;
            }
        }
    }

    /**
     * Handles the click on endTurn button.
     */
    public void onEndTurnClicked() {
        if(this.inputState == InputState.IDLE || this.inputState == InputState.CARD_SELECTED) {
            game.endTurn();
        }
    }

    /**
     * It is for the ready button in the intermission panel.
     * It updates the view for the next player.
     */
    public void onIntermissionReadyClicked() {
        /*Changes the view for the next Player */
        if(this.inputState == InputState.WAITING_FOR_SWAP) {
            this.inputState = InputState.IDLE;
            this.view.showBoard();
            this.view.updateView(this.game);
        }
    }

    /*GameObserver implementation */

    /**
     * Updates the GUI when something happens in the game.
     */
    @Override
    public void onGameUpdated() {
        /*Modified in order to avoid to show for a bit player 2's hand */
        if(this.inputState != InputState.WAITING_FOR_SWAP) {
            this.view.updateView(this.game);
        }
    }

    /**
     * Changes the state of the controller when the turn changes.
     */
    @Override
    public void onTurnChanged(final int playerIndex) {
        this.selectedCard = null;
        if(this.game.getTurnManager().isTurnCycleComplete()) {
            this.inputState = InputState.IDLE;
            this.view.updateView(this.game);
        } else {
            this.inputState = InputState.WAITING_FOR_SWAP;
            this.view.showIntermission();
        }
    }

    /**
     * Called when the game is over and it's time to show the winner.
     * @param winnerName the name of the winner.
     */
    @Override
    public void onGameOver(final String winnerName) {
        this.inputState = InputState.GAME_OVER;
        this.view.showEndGame(winnerName);
    }
}