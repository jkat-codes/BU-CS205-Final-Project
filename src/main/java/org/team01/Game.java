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

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private GameState state = GameState.MENU;

    public Game() {
        this.deck = new Deck();
        this.player = new Player("P1");
        this.dealer = new Dealer();
    }

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

    public boolean placeBet(Bet bet) {
        if (state != GameState.BETTING) {
            return false;
        }

        // Place bet
        player.placeBet(bet); // Throws an error if amount is greater than balance

        // Shift state to dealing
        state = GameState.DEALING;

        return true;
    }

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

        // Need to check for natural win here too (dealer has 17 and player has > 17)
    }

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

    public GameResult playerStand() {
        if (state != GameState.PLAYER_TURN) {
            return null;
        }

        state = GameState.DEALER_TURN;
        // Automatically switches to dealer's turn
        return dealerPlay();
    }

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

    private void endRound(GameResult result) {
        double payout = calculatePayout(result);
        player.payPlayer(payout);
        state = GameState.GAME_OVER;

    }

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

    public GameState getGameState() {
        return state;
    }

    public Hand getPlayerCards() {
        return this.player.getHand();
    }

    public Hand getDealerCards() {
        return this.dealer.getHand();
    }

    public Double getPlayerBalance() {
        return this.player.getCurrentBalance();
    }
}
