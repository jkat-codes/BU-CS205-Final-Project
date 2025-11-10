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

/**
 * Representation of a dealer. Has a hand that came be played according to dealer rules.
 */
public class Dealer extends Participant {

    // Hold the cards in the dealer's dealerHand

    public Dealer() {
        super();
    }

    /**
     * Check if dealer to hit
     *
     * @return true if over 16 and should hit; otherwise false
     */
    public boolean shouldHit() {

        int handValue = hand.getHandValue();

        return handValue <= 16;
    }

    /**
     * Check if dealer has a natural blackjack
     *
     * @return true if natural blackjack; otherwise false
     */
    public boolean hasNaturalBlackjack() {
        // Add control for state of game/hand to check which state the hand is in
        return hand.getCurrentHand().size() == 2 &&
                hand.getHandValue() == 21;
    }

    public boolean playDealerTurn(Card card) {
        if (shouldHit()) {
            hand.addCardToHand(card);
            return true;
        }
        return false;
    }

    /**
     * Check whether dealer hand should be revealed
     *
     * @return true if natural blackjack (21); false otherwise
     */
    public boolean shouldRevealCard() {
        return hand.getHandValue() == 21;
    }

    public int getFirstCard() {
        return hand.getCurrentHand().get(0).getValue();
    }

}


