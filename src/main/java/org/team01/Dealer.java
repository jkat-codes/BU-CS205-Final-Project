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
    private Hand dealerHand;

    public Dealer() {
    }

    /**
     * Check if dealer to hit
     * @return true if over 16 and should hit; otherwise false
     */
    public boolean shouldHit() {

        int handValue = dealerHand.getHandValue();

        return handValue <= 16;
    }

    /**
     * Check if dealer has a natural blackjack
     * @return true if natural blackjack; otherwise false
     */
    public boolean hasNaturalBlackjack() {
        // Add control for state of game/hand to check which state the hand is in
        return dealerHand.getCurrentHand().size() == 2 &&
                dealerHand.getHandValue() == 21;
    }

    public void playDealerTurn(Card card) {
        while (shouldHit()) {
            dealerHand.addCardToHand(card);
        }
    }

    /**
     * Check whether dealer hand should be revealed
     * @return true if natural blackjack (21); false otherwise
     */
    public boolean revealCard() {
        return dealerHand.getHandValue() == 21;
    }

    /**
     * Clear and reset the hand for a new game
     */
    public void resetHand() {
        // Add control for state of game/hand to be "reset-able"
        dealerHand.clearHand();
    }

    // Getters

    /**
     * Get dealer's hand
     * @return Hand of dealer
     */
    public Hand getHand() {
        return dealerHand;
    }

    /**
     * Get value of dealer's hand
     * @return int value of hand
     */
    public int getHandValue() {
        return dealerHand.getHandValue();
    }
}

