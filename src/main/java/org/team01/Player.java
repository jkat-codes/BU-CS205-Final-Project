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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Participant {
    private String name;
    private double balance = 1000;
    private Bet currentBet;


    /**
     * Public constructor for Player with the specified name and balance
     *
     */
    public Player(String name) {
        super();
        this.name = name;
    }

    /**
     * Identify the amount of bet and the balance after placing the bet
     *
     * @param bet the amount of money that player places the bet on
     */
    public void placeBet(Bet bet) throws IllegalArgumentException {
        // Get total amount of bet the player has
        int amount = bet.calculateTotal();

        // Check if the amount of Bet is appropriate
        if (amount <= 0) {
            throw new IllegalArgumentException("Bet must be greater than 0.");
        }
        if (amount > getCurrentBalance()) {
            throw new IllegalArgumentException("Not enough chips for the bet.");
        }

        this.currentBet = bet;
        this.balance -= amount;
    }

    /**
     * Getter method for balance
     * @return player's balance
     */
    public double getCurrentBalance() {
        return balance;
    }

    /**
     * Gets the current active bet
     *
     * @return double representation of the player's bet
     */
    public Bet getCurrentBet() {
        return currentBet;
    }

    /**
     * Resets bet amount to 0 if there is an active bet
     *
     * @return true if the bet is successfully reset, false otherwise
     */
    public boolean resetBet() {
        if (currentBet == null) {
            return false;
        }

        currentBet = null;
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

        // Ensure that there is an active bet
        if (currentBet == null) {
            return false;
        }

        // Ensure that the multiplier is acceptable
        if (multiplier != 0 && multiplier != 1  && multiplier != 2 && multiplier != 2.5) {
            return false;
        }

        // Get bet amount from chips
        double betAmount = currentBet.calculateTotal();

        // Payout amount
        double payout = betAmount * multiplier;

        // Pay the player
        this.balance += payout;

        // Reset the current bet
        return resetBet();
    }
}

