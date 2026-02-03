package com.marvelsnap.controller;

import com.marvelsnap.model.*;
import com.marvelsnap.view.*;

public class GameController implements GameObserver{
    private final Game game;
    private final GamePanel view;
    private InputState inputState;
    private Card selectedCard;

    public GameController(final Game game, final GamePanel view) {
        this.game = game;
        this.view = view;
        this.inputState = InputState.IDLE;

        this.game.addObserver(this);
    }

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

    public void onEndTurnClicked() {
        if(this.inputState == InputState.IDLE || this.inputState == InputState.CARD_SELECTED) {
            game.endTurn();
        }
    }

    public void onIntermissionReadyClicked() {
        /*Changes the view for the next Player */
        if(this.inputState == InputState.WAITING_FOR_SWAP) {
            this.inputState = InputState.IDLE;
            this.view.showBoard();
            this.view.updateView(this.game);
        }
    }

    /*GameObserver implementation */

    @Override
    public void onGameUpdated() {
        /*Modified in order to avoid to show for a bit player 2's hand */
        if(this.inputState != InputState.WAITING_FOR_SWAP) {
            this.view.updateView(this.game);
        }
    }

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

    @Override
    public void onGameOver(final String winnerName) {
        this.inputState = InputState.GAME_OVER;
        this.view.showEndGame(winnerName);
    }
}