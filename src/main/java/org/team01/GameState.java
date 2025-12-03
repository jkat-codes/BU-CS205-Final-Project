package org.team01;

/**
 * Enum representing the possible states of the game
 *
 * Types of game state
 * MENU: no betting or game has begun
 * BETTING: the player is currently betting
 * DEALING: the game has begun and the cards are being dealt
 * PLAYER_TURN: it is the player's turn to hit or stand
 * DEALER_TURN: it is the dealer's turn to hit until 17
 * GAME_OVER: the game has reached an end and everything resets
 */
public enum GameState {
    MENU,
    BETTING,
    DEALING,
    PLAYER_TURN,
    DEALER_TURN,
    GAME_OVER,
}
