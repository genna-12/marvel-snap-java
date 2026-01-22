package com.marvelsnap.controller;

import com.marvelsnap.model.*;
import com.marvelsnap.view.*;

public class GameController {
    private Game game;
    private GamePanel view;
    private InputState inputState;
    private Card selectedCard;

    public GameController(Game game, GamePanel view) {
        this.game = game;
        this.view = view;
        this.inputState = InputState.IDLE;
    }

    public void onCardClicked(final Card card) {
        return;
    }

    public void onLocationClicked(final int locIdx) {
        return;
    }

    public void onEndTurnClicked() {
        return;
    }

    public void onIntermissionReadyClicked() {
        return;
    }
}