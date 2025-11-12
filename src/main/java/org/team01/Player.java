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
    private int balance;
    private List<Card> currentHand;
    private int currentBet;

    /**
     * Public constructor for Player with the specified name and balance
     *
     */
    public Player(String name, int balance) {
        super();
        this.name = name;
        this.balance = balance;
        this.currentBet = currentBet;
    }

    public void placeBet() {
    }

    /**
     * Getter method for balance
     * @return player's balance
     */
    public int getCurrentBalance() {
        return balance;
    }

}

