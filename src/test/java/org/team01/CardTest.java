package org.team01;/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Jasper Katalevsky
 * Date: 11/5/25
 * Time: 10:40 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: CardTest
 *
 * Description:
 *
 * ****************************************
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team01.Card;
import org.team01.FaceValue;
import org.team01.Suit;

public class CardTest {
    private Card card1;

    @BeforeEach
    void setUp() {
        card1 = new Card(Suit.SPADES, 5, FaceValue.KING);
    }

    @Test
    void testSuit() {
        Suit cardSuit = card1.getSuit();
        boolean result = cardSuit.equals(Suit.SPADES);
        assertTrue(result);
    }

    @Test
    void testValue() {
        int value = card1.getValue();
        boolean result = value == 5;
        assertTrue(result);
    }

    @Test
    void testRank() {
        FaceValue value = FaceValue.KING;
        boolean result = card1.getRank() == value;
        assertTrue(result);
    }
}
