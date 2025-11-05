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
 * Description:
 *
 * ****************************************
 */

package org.team01;

import java.util.List;

public class Dealer {

    // Hold the cards in the dealer's dealerHand
    private Hand dealerHand = Hand();

    public Dealer() {
    }

    public boolean shouldHit() {

        int handValue = dealerHand.getHandValue();

        if (handValue <= 16) {
            return true;
        }
        return false;
    }

    public boolean revealCard() {
        if (dealerHand.size() == 21) {
            return true;
        }
        return false;
    }

    public void resetHand() {
        dealerHand.clear();
    }

}

