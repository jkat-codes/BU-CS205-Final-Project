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

/**
 * Represent sum of cards the dealer and player has in the game
 */
public class Hand {

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Add a card to hand
     *
     * @param card to add in hand
     */
    public void addCardToHand(Card card) {
        cards.add(card);
    }

    /**
     * Determine if hand is blackjack
     *
     * @return true if hand equals 21; otherwise false
     */
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

    /**
     * Determine is hand is bust
     *
     * @return true if hand is over 21; otherwise false
     */
    public boolean isBust() {
        if (cards.isEmpty()) {
            return false;
        }
        return getHandValue() > 21;
    }

    /**
     * Clear hand for a new ground
     *
     * @return true if hand is clearable and cleared; otherwise false
     */
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

    /**
     * Return a copy of the current hand
     *
     * @return List of Cards copy of current hand; empty List if empty
     */
    public List<Card> getCurrentHand() {
        List<Card> tempHand = new ArrayList<>();
        if (!cards.isEmpty()) {
            for (Card card : cards) {
                tempHand.add(card);
            }
        }
        return tempHand;
    }

    /**
     * Get the value of the current hand
     *
     * @return int value of the current hand
     */
    public int getHandValue() {
        // Check base case
        if (cards.isEmpty()) {
            return -1;
        }

        System.out.println("Get hand value called");

        // Add all card values
        int handValue = 0;
        for (Card card : cards) {
            handValue += card.getValue();
        }

        for (int i = 0; i < numAcesInHand(); i++) {
            if (containsAce() && handValue > 21) {
                handValue -= 10;
            }
        }

        System.out.println("Hand value: " + handValue);
        return handValue;
    }

    // String representation of entire hand
    // Represents the Card as a string containing the rank and suit "QUEEN of Hearts"

    /**
     * String representation of entire hand
     * Represents the Card as a string containing the rank and suit "QUEEN of Hearts"
     *
     * @return String representation of hand
     */

    private boolean containsAce() {
        // first I need to get the hand
        boolean containsAce = cards.stream()
                .anyMatch(card -> card.getRank() == FaceValue.ACE);
        return containsAce;
    }

    private int numAcesInHand() {
        return (int) cards.stream()
                .filter(card -> card.getRank() == FaceValue.ACE)
                .count();
    }

    public String toString() {
        if (cards.isEmpty()) {
            return "[]";
        }
        String handString = "[";
        for (Card card : cards) {
            handString += card.toString() + ", ";
        }
        handString = handString.substring(0, handString.length() - 2) + "]";
        return handString;
    }
}
