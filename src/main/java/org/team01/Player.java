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

public class Player extends Participant {
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
     * Getter method for balance
     * @return player's balance
     */
    public int getCurrentBalance() {
        return balance;
    }

    /**
     * Getter method for current Bet value
     * @return player's current bet value
     */
    public int getCurrentBet() {
        return currentBet;
    }
}

