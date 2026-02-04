package com.marvelsnap.controller;

/**
 * Enum to define the possible input states of the controller.
 */
public enum InputState {
    /**
     * Default state.
     */
    IDLE,

    /**
     * Selected card state.
     */
    CARD_SELECTED, 

    /**
     * Turn change.
     */
    WAITING_FOR_SWAP,

    /**
     * The match is over.
     */
    GAME_OVER
}
