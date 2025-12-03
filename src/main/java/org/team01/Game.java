/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Jasper Katalevsky
 * Date: 11/10/25
 * Time: 10:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Game
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing a game of blackjack
 * All functionality within can be strung together to play multiple rounds of blackjack
 */
public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private GameState state = GameState.MENU;

    /**
     * Constructs a new Game object
     */
    public Game() {
        this.deck = new Deck();
        this.player = new Player("P1");
        this.dealer = new Dealer();
    }

    /**
     * Function to initialize a new game
     * Creates and shuffles the deck, shifts state to betting
     *
     * @return true if the deck is successfully created and a round has begun, false otherwise
     */
    public boolean initializeNewGame() {
        // Check game state to make sure that there is no other game in play
        if (state != GameState.MENU && state != GameState.GAME_OVER) {
            return false;
        }

        // Setup and shuffle deck
        this.deck.shuffle();

        // Reset hands (regardless of previous play)
        player.resetHand();
        dealer.resetHand();

        // Shift state to betting
        state = GameState.BETTING;

        return true;
    }

    /**
     * Function that starts a new round
     * Resets player and dealer hands (from previous round if one was played)
     *
     * @return true if a new round is able to be/started, false otherwise
     */
    public boolean startNewRound() {
        if (state != GameState.BETTING) {
            return false;
        }

        // Reset hands
        player.resetHand();
        dealer.resetHand();

        // Shift state to betting
        state = GameState.BETTING;

        return true;
    }

    /**
     * Function that allows the player to place a bet for this round
     *
     * @param bet Bet amount that the player wants to wager
     * @return true if the bet is placed successfully, false otherwise
     */
    public boolean placeBet(Bet bet) {
        if (state != GameState.BETTING) {
            return false;
        }

        // Place bet
        try {
            player.placeBet(bet); // Throws an error if amount is greater than balance
        } catch (IllegalArgumentException e) {
            return false;
        }

        // Shift state to dealing
        state = GameState.DEALING;

        return true;
    }

    /**
     * Function that simulates dealing the first 4 cards of a new round/hand
     * Checks for natural blackjack for each player (dealer included), if no blackjack then the round continues, else the round is over
     *
     * @return GameResult of a specific blackjack, or a state shift to player turn and GameResult shifts to IN_PROGRESS
     */
    public GameResult dealInitialCards() {
        if (state != GameState.DEALING) {
            return null;
        }

        player.addCardToHand(deck.deal());
        dealer.addCardToHand(deck.deal());
        player.addCardToHand(deck.deal());
        dealer.addCardToHand(deck.deal());

        // Check for initial (natural) blackjack
        boolean playerHasBlackJack = player.hasBlackjack();
        boolean dealerHasBlackJack = dealer.hasBlackjack();

        if (playerHasBlackJack && dealerHasBlackJack) {
            endRound(GameResult.BOTH_BLACKJACK);
            return GameResult.BOTH_BLACKJACK;
        } else if (playerHasBlackJack) {
            endRound(GameResult.PLAYER_BLACKJACK);
            return GameResult.PLAYER_BLACKJACK;
        } else if (dealerHasBlackJack) {
            endRound(GameResult.DEALER_BLACKJACK);
            return GameResult.DEALER_BLACKJACK;
        }

        // Shift state to player turn
        state = GameState.PLAYER_TURN;
        return GameResult.IN_PROGRESS;
    }

    /**
     * Function that simulates the player hitting
     * Hits at the users consent through the UI. If the player busts, the round is over, else the round remains IN_PROGRESS
     *
     * @return GameResult of PLAYER_BUST, PLAYER_BLACKJACK, or IN_PROGRESS
     */
    public GameResult playerHit() {
        if (state != GameState.PLAYER_TURN) {
            return null;
        }

        player.addCardToHand(deck.deal());

        if (player.getHandValue() > 21) {
            endRound(GameResult.PLAYER_BUST);
            return GameResult.PLAYER_BUST;
        }

        if (player.getHandValue() == 21) {
            endRound(GameResult.PLAYER_BLACKJACK);
            return GameResult.PLAYER_BLACKJACK;
        }

        return GameResult.IN_PROGRESS;
    }

    /**
     * Function that simulates the player standing
     * Automatically calls dealer play, since the player forfeits hitting any further after choosing to stand
     *
     * @return GameResult of a win, loss, or tie
     */
    public GameResult playerStand() {
        if (state != GameState.PLAYER_TURN) {
            return null;
        }

        state = GameState.DEALER_TURN;
        // Automatically switches to dealer's turn
        return dealerPlay();
    }

    /**
     * Function that simulates dealer play
     * Dealer hits until hand value is 17 or greater (inclusive of bust)
     *
     * @return GameResult of a win, loss, or tie
     */
    public GameResult dealerPlay() {
        if (state != GameState.DEALER_TURN) {
            return null;
        }

        while (dealer.getHandValue() <= 16) {
            dealer.addCardToHand(deck.deal());
        }

        // No more dealing necessary (both turns are over)
        GameResult result = determineWinner();
        endRound(result);
        return result;
    }

    /**
     * Function to determine the winner of the round based on the total value of each hand
     * Only used when no blackjack or bust is achieved by either player
     *
     * @return GameResult of a win, loss, or tie
     */
    private GameResult determineWinner() {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        // Check for bust
        if (dealerValue > 21) {
            return GameResult.DEALER_BUST;
        }

        if (playerValue > dealerValue) {
            return GameResult.PLAYER_WIN;
        } else if (dealerValue > playerValue) {
            return GameResult.DEALER_WIN;
        } else {
            return GameResult.PUSH;
        }
    }

    /**
     * Function to end the current game/round
     *
     * @param result the GameResult of the game, used to calculate the payout
     */
    private void endRound(GameResult result) {
        double payout = calculatePayout(result);
        player.payPlayer(payout);
        state = GameState.GAME_OVER;

    }

    /**
     * Function to calculate the total payout owed to the player
     *
     * @param result the GameResult of the game, used to determine the value owed to the player
     * @return returns a multiplier based on the kind of win/loss of the player
     */
    private double calculatePayout(GameResult result) {
        switch (result) {
            case PLAYER_BLACKJACK:
                return 2.5; // 3:2 Payout
            case PLAYER_WIN:
            case DEALER_BUST:
                return 2.0; // 2:1 Payout
            case PUSH:
            case BOTH_BLACKJACK:
                    return 1.0; // Tie
            case DEALER_WIN:
            case DEALER_BLACKJACK:
            case PLAYER_BUST:
            default:
                return 0.0; // Player loses
        }
    }

    /**
     * Returns the current game state
     *
     * @return the GameState state of the game
     */
    public GameState getGameState() {
        return state;
    }

    /**
     * Returns the player's current hand
     *
     * @return the current Hand of the player
     */
    public Hand getPlayerCards() {
        return this.player.getHand();
    }

    /**
     * Returns the dealer's current hand
     *
     * @return the current Hand of the dealer
     */
    public Hand getDealerCards() {
        return this.dealer.getHand();
    }

    /**
     * Gets the player's current balance
     *
     * @return the player's current balance
     */
    public Double getPlayerBalance() {
        return this.player.getCurrentBalance();
    }
}
