/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Jasper Katalevsky
 * Date: 11/4/25
 * Time: 12:32 PM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Card
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

/**
 * Represents a single playing card from a standard deck of cards
 * Each card has a suit, rank, and numerical value associated with it
 */
public class Card {
    private String suit;
    private int value;
    private String rank;

    /**
     * Constructs a new Card with the specified suit, value, and rank
     * @param suit the suit of the card (i.e. hearts, diamonds, clubs, spades)
     * @param value the numerical value of the card (i.e. 1, 2, 3, ...)
     * @param rank the rank name of the card (i.e. ace, two, three, ..., jack, king, queen)
     */
    public Card(String suit, int value, String rank) {
        this.suit = suit; // HEARTS, DIAMONDS, CLUBS, SPADES
        this.value = value; // 1, 2, 3, ...
        this.rank = rank; // ACE, TWO, THREE, ...
    }

    /**
     * Gets the suit of the Card
     *
     * @return the suit of the Card
     */
    public String getSuit() {
        return this.suit;
    }

    /**
     * Gets the numerical value of the Card
     *
     * @return the numerical value of the Card as an integer
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Gets the rank of the Card
     *
     * @return the rank of the Card as a String
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * Represents the Card as a string containing the rank and suit "Queen of Hearts"
     *
     * @return string representation of the Card
     */
    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }
}

