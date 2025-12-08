package org.team01.model;

/**
 * An enumerated type that defines different results from the blackjack game
 * Depending on the result, the payout for the winning hand differs
 * --
 * Types of game result:
 * - BOTH_BLACKJACK: both player and dealer has natural; a tie
 * - PLAYER_BLACKJACK: player gets natural blackjack
 * - DEALER_BLACKJACK: dealer gets natural blackjack
 * - PLAYER_BUST: player's hand total exceeds 21; player lose
 * - DEALER_BUST: dealer's hand total exceeds 21; dealer lose
 * - PLAYER_WIN: player wins the game
 * - DEALER_WIN: dealer wins the game
 * - PUSH: tie between player and dealer
 * - IN_PROGRESS: game in progress; players hasn't stood
 *
 */
public enum GameResult {
    BOTH_BLACKJACK,
    PLAYER_BLACKJACK,
    DEALER_BLACKJACK,
    PLAYER_BUST,
    DEALER_BUST,
    PLAYER_WIN,
    DEALER_WIN,
    PUSH,
    IN_PROGRESS
}
