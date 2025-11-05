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


import java.util.List;
import java.util.Random;


/**
 * this is the class skeleton for the Deck class
 */
public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffle(List<Card> c) {
        int[] repetitions = new int[13];
        for (int i = 0; i < 52; i++) {

            Random random = new Random();
            int min = 1;
            int max = 52;

            int suit = random.nextInt(4);
            int value = random.nextInt(max - min + 1) + min;
            String suitName;

            if (suit == 0) {
                suitName = "Clubs";
            } else if (suit == 1) {
                suitName = "Spades";
            } else if (suit == 2) {
                suitName = "Hearts";
            } else {
                suitName = "Diamonds";
            }

            Card obj = new Card(suitName, value, "");
            cards.add(obj);
        }
    }


}