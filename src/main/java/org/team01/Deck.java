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


import java.util.*;

/**
 * this is the class skeleton for the Deck class
 */
public class Deck {
    private List<Card> cards;
    private List<FaceValue> FaceValues;

    public Deck() {
        cards = new ArrayList<>();
        FaceValues = new ArrayList<>(Arrays.asList(null, FaceValue.ACE, FaceValue.TWO, FaceValue.THREE, FaceValue.FOUR, FaceValue.FIVE, FaceValue.SIX, FaceValue.SEVEN, FaceValue.EIGHT, FaceValue.NINE, FaceValue.TEN, FaceValue.JACK, FaceValue.QUEEN, FaceValue.KING));
    }

    public void shuffle() {
        // Creates if deck is empty
        createDeck();

        // Current state is all the suits and values in order from 1 to 13 by suit, we need to shuffle the cards
        // Shuffle the cards using the Fisher-Yates shuffle algorithm
        Random random = new Random();
        for (int j = cards.size() - 1; j > 0; j--) {
            int index = random.nextInt(0, j);
            Card temp = cards.get(j);
            cards.set(j, cards.get(index));
            cards.set(index, temp);
        }

    }

    private void createDeck() {
        // Only create the deck if its currently empty
        if (!cards.isEmpty()) {
            return;
        }

        Suit suit = null;
        // Iterate through each suit
        for (int i = 0; i < 4; i++) {
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

            for (int k = 1; k <= 13; k++) {
                cards.add(new Card(suit, k, FaceValues.get(k)));
            }
        }
    }

    public int getCardsRemaining() {
        return cards.size();
    }

    public Card deal() {
        if (cards.isEmpty()) {
            // Creates and shuffles a new deck
            shuffle();
        }
        // Get a card from the top of the deck
        Card dealtCard = cards.getFirst();

        // Remove the dealt card from the deck
        cards.removeFirst();

        // Return the top card
        return dealtCard;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public Card get(int index) {
        if (index >= cards.size() || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        return cards.get(index);
    }
}