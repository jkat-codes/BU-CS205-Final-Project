/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Uijin Kang
 * Date: 11/5/2025
 * Time: 10:17 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Player
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int balance = 2000;
    private int currentBet;
    private Hand playerHand;

    /**
     * Public constructor for Player with the specified name and balance
     *
     */
    public Player(String name) {
        this.name = name;
        this.playerHand = new Hand();
    }

    /**
     * Identify the amount of bet and the balance after placing the bet
     *
     * @param amount the amount of money that player places the bet on
     */
    public void placeBet(int amount) throws IllegalArgumentException {
        // Check if the amount of Bet is appropriate
        if (amount < 0) {
            throw new IllegalArgumentException("Bet must be greater than 0.");
        }
        // Check if the amount is greater than the balance
        if (amount > balance) {
            throw new IllegalArgumentException("Given amount cannot be paid with your current balance.");
        }

        currentBet = amount;
        balance -= amount;

    }

    /**
     * this code should be added to Game class
     * (requires dealer class in player class, which is not appropriate to have)
     */
//    public boolean won() {
//        int total = 0;
//        int dealertotal = 0;
//        // Obtain total value from player card
//        for (Card card : currentHand) {
//            total += card.getValue();
//        }
//        // Obtain total value from dealer card
//
//        // Check if the total is over 21
//        if (playerHand.isBust()) {
//            return false;
//        }
//
//        // Compare distance to 21 between player and dealer
//        int playerval = Math.abs(21-total);
//        int dealerval = Math.abs(21-dealertotal);
//
//        if (playerval < dealerval) {
//            return true;
//        }
//        return false;
//        // Obtain values from both player and dealer
//        int playerVal = getHandValue();
//        int dealerVal = dealer.getHandValue();
//
//        // Calculate difference between player and dealer's card value to 21
//        int diffPlayer = Math.abs(21-playerVal);
//        int diffDealer = Math.abs(21-dealerVal);
//
//        // Check if player's card value is 21
//        if (playerVal == 21) {
//            return true;
//        }
//        // Find which value is closer to 21
//        else return diffPlayer < diffDealer;
//    }

//    /**
//     * Check who lost the game between player and dealer
//     * Either side with value farther from 21 loses
//     * @return true if player lost, false otherwise
//     */
//    public boolean lost() {
//        int total = 0;
//        int dealertotal = 0;
//        // Obtain total value from player card
//        for (Card card : currentHand) {
//            total += card.getValue();
//        }
//        // Obtain total value from dealer card
//
//        // Compare distance to 21 between player and dealer
//        int playerval = Math.abs(21-total);
//        int dealerval = Math.abs(21-dealertotal);
//
//        if (playerval > dealerval) {
//            return true;
//        }
//        return false;
//    }

    /**
     * Getter method for balance
     * @return player's balance
     */
    public int getCurrentBalance() {
        return balance;
    }

    /**
     * Resets the number of cards that the user has in their hand
     */
    public void resetHand() {
        playerHand.clearHand();
    }

    /**
     * Get value of player's hand
     * @return int value of hand
     */
    public int getHandValue() {
        return playerHand.getHandValue();
    }

    /**
     * Adds card to player's hand
     * @param card Card from Card Class
     */
    public void addCardToHand(Card card) {
        playerHand.addCardToHand(card);
    }

    /**
     * Identify balance of the player
     * @return player's balance
     */
    public int getBalance() {
        return balance;
    }
}

