/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sanda Tan
 * Date: 11/6/25
 * Time: 10:15 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Hand
 *
 * Description: Representation of a hand with cards that are passed out.
 *
 * ****************************************
 */

package org.team01;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        // Check base case
        if (cards.isEmpty()) {
            return false;
        }

        // Get value from method
        int handValue = getHandValue();

        // Check for 21
        return handValue == 21;
    }

    public boolean isBust() {
        if (cards.isEmpty()) {
            return false;
        }
        return getHandValue() > 21;
    }

    public boolean clearHand() {
        if (cards.isEmpty()) {
            return false;
        }

        // Clear the hand
        cards.clear();

        // Notify success or failure of clear
        return cards.isEmpty();
    }

    // Getter
    public List<Card> getCurrentHand() {
        List<Card> tempHand = new ArrayList<>();
        if (!cards.isEmpty()) {
            for (Card card : cards) {
                tempHand.add(card);
            }
        }
        return tempHand;
    }

    public int getHandValue() {
        // Check base case
        if (cards.isEmpty()) {
            return -1;
        }

        // Add all card values
        int handValue = 0;
        for (Card card : cards) {
            handValue += card.getValue();
        }
        return handValue;
    }

    public String toString() {
        String handString = "[";
        for (Card card : cards) {
            handString += card.toString() + " ";
        }
        handString = handString.trim() + "]";
        return handString;
    }
}
