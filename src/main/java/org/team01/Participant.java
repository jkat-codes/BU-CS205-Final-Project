/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sanda Tan
 * Date: 11/6/25
 * Time: 11:30 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Participant
 *
 * Description: A parent class of participant that will be implemented with dealer and player
 *
 * ****************************************
 */

package org.team01;

/**
 * A parent class of participant that will be implemented with dealer and player
 */
public abstract class Participant {

    protected Hand hand;

    public Participant() {
        hand = new Hand();
    }

    /**
     * Clear and reset the hand for a new game
     */
    public void resetHand() {
        hand.clearHand();
    }

    /**
     * Get value of hand
     * @return int value of hand
     */
    public int getHandValue() {
        return hand.getHandValue();
    }

    /**
     * Get participant's hand
     * @return the current hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Check if participant has a blackjack
     * @return true if blackjack; otherwise false
     */
    public boolean hasBlackjack() {
        return hand.getHandValue() == 21;
    }
}
