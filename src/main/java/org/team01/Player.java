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

public class Player extends Participant {
    private String name;
    private double balance = 2000;
    private double currentBet;
    private boolean openBet = false;

    /**
     * Public constructor for Player with the specified name and balance
     *
     */
    public Player(String name) {
        super();
        this.name = name;
        this.balance = balance;
        this.currentBet = currentBet;
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
        if (amount > this.balance) {
            throw new IllegalArgumentException("Given amount cannot be paid with your current balance.");
        }

        this.currentBet = amount;
        this.balance -= amount;
        openBet = true;
    }

    /**
     * Getter method for balance
     * @return player's balance
     */
    public double getCurrentBalance() {
        return this.balance;
    }

    /**
     * Gets the current active bet
     *
     * @return double representation of the player's bet
     */
    public double getCurrentBet() {
        return this.currentBet;
    }

    /**
     * Resets bet amount to 0 if there is an active bet
     *
     * @return true if the bet is successfully reset, false otherwise
     */
    public boolean resetBet() {
        if (!openBet) {
            return false;
        }

        this.currentBet = 0;
        return true;
    }

    /**
     * Pays the player based on their current bet and a multiplier, only if there is a bet active
     *
     * @param multiplier the multipler, generally 1, but 1.5 - 3 based on type of win (natural blackjack, dealer bust, push)
     * @return true if the player is successfully payed out, false otherwise
     */
    public boolean payPlayer(double multiplier) {
        /*
         -- Multiplier --
         0 for player loss
         1 for Push (tie)
         2 for Regular Win (dealer bust)
         2.5 for 3:2 (BlackJack)
         */

        // Ensure that there is an open bet
        if (!openBet) {
            return false;
        }

        // Ensure that the multiplier is acceptable
        if (multiplier != 0 && multiplier != 1  && multiplier != 2 && multiplier != 2.5) {
            return false;
        }

        // Payout amount
        double payout = this.currentBet *= multiplier;

        // Pay the player
        this.balance += payout;

        // Reset the current bet
        resetBet();

        return true;
    }
}

