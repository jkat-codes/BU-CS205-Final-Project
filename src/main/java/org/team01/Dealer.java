/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sanda Tan
 * Date: 11/5/25
 * Time: 10:15 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Dealer
 *
 * Description: Representation of a dealer. Has a hand that came be played according to dealer rules.
 *
 * ****************************************
 */

package org.team01;

import java.util.List;

/**
 * Representation of a dealer. Has a hand that came
 * be played according to dealer rules.
 */
public class Dealer {

    // Hold the cards in the dealer's dealerHand
    private Hand dealerHand = Hand();

    public Dealer() {
    }

    /**
     * Check if dealer to hit
     * @return true if over 16 and should hit; otherwise false
     */
    public boolean shouldHit() {

        int handValue = dealerHand.getHandValue();

        if (handValue <= 16) {
            return true;
        }
        return false;
    }

    /**
     * Check whether dealer hand should be revealed
     * @return true if natural blackjack (21); false otherwise
     */
    public boolean revealCard() {
        if (dealerHand.size() == 21) {
            return true;
        }
        return false;
    }

    /**
     * Clear and reset the hand for a new game
     */
    public void resetHand() {
        dealerHand.clear();
    }
}

