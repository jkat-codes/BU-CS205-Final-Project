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

import java.util.List;

public class Player {
    private String name;
    private int balance = 2000;
    private List<Card> currentHand;
    private int currentBet;

    /**
     * Public constructor for Player with the specified name and balance
     *
     */
    public Player(String name) {
        this.name = name;
        this.balance = balance;
        this.currentHand = currentHand;
        this.currentBet = currentBet;
    }

    public void placeBet() {
    }

    /**
     * Check who won the game between player and dealer
     * Either side with closer value to 21 wins
     * @return true if player won, false otherwise
     */
    public boolean won() {
        int total = 0;
        int dealertotal = 0;
        // Obtain total value from player card
        for (Card card : currentHand) {
            total += card.getValue();
        }
        // Obtain total value from dealer card

        // Check if the total is over 21
//        if (isBust() == true) {
//            return false;
//        }

        // Compare distance to 21 between player and dealer
        int playerval = Math.abs(21-total);
        int dealerval = Math.abs(21-dealertotal);

        if (playerval < dealerval) {
            return true;
        }
        return false;
    }

    /**
     * Check who lost the game between player and dealer
     * Either side with value farther from 21 loses
     * @return true if player lost, false otherwise
     */
    public boolean lost() {
        int total = 0;
        int dealertotal = 0;
        // Obtain total value from player card
        for (Card card : currentHand) {
            total += card.getValue();
        }
        // Obtain total value from dealer card

        // Compare distance to 21 between player and dealer
        int playerval = Math.abs(21-total);
        int dealerval = Math.abs(21-dealertotal);

        if (playerval > dealerval) {
            return true;
        }
        return false;
    }

    /**
     * Getter method for balance
     * @return player's balance
     */
    public int getCurrentBalance() {
        return balance;
    }

    public void resetHand() {
        currentHand.clear();
    }

}

