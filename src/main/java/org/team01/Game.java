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
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Deck deck = new Deck();
        Player player = new Player("P1");
        Dealer dealer = new Dealer();
        String newGame = "y";

        // Welcome user and present balance
        System.out.println("Welcome User!");
        System.out.println("--Account Balance--");
        System.out.println("$" + player.getCurrentBalance());
        System.out.println("\n\n");

        // Setup and shuffle deck
        deck.shuffle();

        while(newGame.trim().equalsIgnoreCase("y")) {
            boolean gameOver = false;

            // Ask for player bet here
            player.placeBet(5);

            // Deal the initial cards
            dealInitialCards(deck, dealer, player, gameOver);

            // Player turn
            while (!gameOver) {
                System.out.print("Hit or Stand? [y/n]: ");
                String response = scan.next();

                // Hit until satisfied or bust
                while (response.trim().equalsIgnoreCase("y")) {
                    // Add card to hand
                    player.addCardToHand(deck.deal());

                    // Show card
                    System.out.println("Player hand: " + player.getHand());
                    System.out.println("Player has: " + player.getHandValue());

                    // Check for bust
                    if (player.getHandValue() > 21) {
                        System.out.println("Player bust with: " + player.getHandValue());
                        // Player lost, reset bet and update balance
                        player.payPlayer(0);
                        gameOver = true;
                        // Clear both hands
                        dealer.resetHand();
                        player.resetHand();

                        break;
                    }
                    // Check for blackjack
                    if (player.hasBlackjack()) {
                        System.out.println("BlackJack! Player has won!");
                        // Player won, reset bet and update balance
                        player.payPlayer(2.5);
                        gameOver = true;
                        // Clear both hands
                        dealer.resetHand();
                        player.resetHand();

                        break;
                    }

                    System.out.print("Hit or Stand? [y/n]: ");
                    response = scan.next();
                }

                if (!gameOver) {
                    // Player turn over, dealer reveals card (dealer does not have blackjack here)
                    System.out.println("\nDealer reveals: " + dealer.getHand());
                    System.out.println("Dealer has: " + dealer.getHandValue());

                    // Dealer hit until at least 17
                    while (dealer.getHandValue() < 17) {
                        dealer.addCardToHand(deck.deal());
                        System.out.println("Dealer hits: " + dealer.getHand());
                        System.out.println("Dealer has: " + dealer.getHandValue());
                    }

                    checkWin(player, dealer);
                    gameOver = true;
                }
            }

            // Clear both hands
            dealer.resetHand();
            player.resetHand();

            // Show current balance
            System.out.println("\n\nPlayer balance: $" + player.getCurrentBalance());

            System.out.print("Play again? [y/n]: ");
            newGame = scan.next();
        }


    }

    private static void dealInitialCards(Deck deck, Dealer dealer, Player player, boolean gameState) {
        // Deal cards
        player.addCardToHand(deck.deal());
        dealer.addCardToHand(deck.deal());
        player.addCardToHand(deck.deal());
        dealer.addCardToHand(deck.deal());

        // Immediately check for blackjack
        if (player.hasBlackjack() && dealer.hasBlackjack()) {
            System.out.println("Both have BlackJack! - Push");
            player.payPlayer(1);
            gameState = true;
            clearHands(player, dealer);
        } else if (player.hasBlackjack()) {
            System.out.println("Player has BlackJack! Player wins!");
            player.payPlayer(2.5);
            gameState = true;
            clearHands(player, dealer);
        } else if (dealer.hasBlackjack()) {
            System.out.println("Dealer has BlackJack. Dealer wins.");
            player.payPlayer(0);
            gameState = true;
            clearHands(player, dealer);
        }


        System.out.println("Player has: " + player.getHandValue());
        System.out.println("Player hand: " + player.getHand());
        System.out.println("Dealer showing: " + dealer.getFirstCard() + "\n");
    }

    private static void checkWin(Player player, Dealer dealer) {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        if (dealer.hand.isBust()) {
            System.out.println("\n\nDealer bust! Player wins!");
            // Player wins, reset bet and update balance
            player.payPlayer(2.0);
        } else if (playerValue > dealerValue) {
            System.out.println("\n\nPlayer wins with " + playerValue + " vs " + dealerValue);
            // Player wins, reset bet and update balance
            player.payPlayer(2.0);
        } else if (dealerValue > playerValue) {
            System.out.println("\n\nDealer wins with " + dealerValue + " vs " + playerValue);
            // Dealer wins, reset bet and update balance
            player.payPlayer(0);
        } else {
            System.out.println("\n\nTie (Push) Both have " + playerValue);
            // Tie, reset bet and update balance
            player.payPlayer(1.0);
        }

        clearHands(player, dealer);
    }

    private static void clearHands(Player player, Dealer dealer) {
        player.resetHand();
        dealer.resetHand();
    }
}
