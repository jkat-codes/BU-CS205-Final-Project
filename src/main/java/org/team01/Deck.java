/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Finnian Franks
 * Date: 11/5/25
 * Time: 10:17 AM
 *
 * Project: csci205_final_project
 * Package: org.team01.StateDiagrams
 * Class: Deck
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * this is the class skeleton for the Deck class
 */
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void shuffle() {
        Random random = new Random();
        Suit suit = null;
        int max = 13;
        int min = 1;
        // Iterate through each suit
        for (int i = 0; i < 4; i++) {
            List<Integer> numbersAlreadyGenerated = new ArrayList<>();
            switch (i) {
                case 0:
                    suit = Suit.CLUBS;
                    break;
                case 1:
                    suit = Suit.SPADES;
                    break;
                case 2:
                    suit = Suit.HEARTS;
                    break;
                case 3:
                    suit = Suit.DIAMONDS;
                    break;
                default:
                    break;
            }

            int numOfCards = 0;

            while (numOfCards < 13) {
                int value = random.nextInt(max - min + 1) + min;
                if (!numbersAlreadyGenerated.contains(value)) {
                    cards.add(new Card(suit, value, "rank"));
                    numbersAlreadyGenerated.add(value);
                    numOfCards++;
                }
            }
        }

        // Current state is all the suits and values in order from 1 to 13 by suit, we need to shuffle the cards

        // Shuffle the cards
       Collections.shuffle(cards);
    }

    public int getCardsRemaining() {
        return cards.size();
    }

    public Card deal() {
        if (cards.size() <= 4) {
            // Get a new dec
            shuffle();
        }
        // Get a card from the top of the deck
        Card dealtCard = cards.getFirst();

        // Remove the dealt card from the deck
        cards.removeFirst();

        // Return the top card
        return dealtCard;
    }
}