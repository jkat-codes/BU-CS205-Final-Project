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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team01.model.Card;
import org.team01.model.FaceValue;
import org.team01.model.Suit;

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

    @Test
    void testHigherValues() {
        Card king = new Card(Suit.SPADES, 10, FaceValue.KING);
        Card queen = new Card(Suit.SPADES, 10, FaceValue.KING);
        Card jack = new Card(Suit.SPADES, 10, FaceValue.JACK);

        boolean kingValue = king.getValue() == 10;
        boolean queenValue = queen.getValue() == 10;
        boolean jackValue = jack.getValue() == 10;

        assertTrue(kingValue);
        assertTrue(queenValue);
        assertTrue(jackValue);
    }

    @Test
    void testImagePaths() {
        Card king = new Card(Suit.SPADES, 10, FaceValue.KING);
        Card queen = new Card(Suit.SPADES, 10, FaceValue.QUEEN);
        Card jack = new Card(Suit.SPADES, 10, FaceValue.JACK);
        Card ace = new Card(Suit.SPADES, 11, FaceValue.ACE);

        assertEquals("KS", king.getImagePath());
        assertEquals("QS", queen.getImagePath());
        assertEquals("JS", jack.getImagePath());
        assertEquals("AS", ace.getImagePath());

        Card hking = new Card(Suit.HEARTS, 10, FaceValue.KING);
        Card hqueen = new Card(Suit.HEARTS, 10, FaceValue.QUEEN);
        Card hjack = new Card(Suit.HEARTS, 10, FaceValue.JACK);
        Card hace = new Card(Suit.HEARTS, 11, FaceValue.ACE);

        assertEquals("KH", hking.getImagePath());
        assertEquals("QH", hqueen.getImagePath());
        assertEquals("JH", hjack.getImagePath());
        assertEquals("AH", hace.getImagePath());

        Card dking = new Card(Suit.DIAMONDS, 10, FaceValue.KING);
        Card dqueen = new Card(Suit.DIAMONDS, 10, FaceValue.QUEEN);
        Card djack = new Card(Suit.DIAMONDS, 10, FaceValue.JACK);
        Card dace = new Card(Suit.DIAMONDS, 11, FaceValue.ACE);

        assertEquals("KD", dking.getImagePath());
        assertEquals("QD", dqueen.getImagePath());
        assertEquals("JD", djack.getImagePath());
        assertEquals("AD", dace.getImagePath());

        Card cking = new Card(Suit.CLUBS, 10, FaceValue.KING);
        Card cqueen = new Card(Suit.CLUBS, 10, FaceValue.QUEEN);
        Card cjack = new Card(Suit.CLUBS, 10, FaceValue.JACK);
        Card cace = new Card(Suit.CLUBS, 11, FaceValue.ACE);

        assertEquals("KC", cking.getImagePath());
        assertEquals("QC", cqueen.getImagePath());
        assertEquals("JC", cjack.getImagePath());
        assertEquals("AC", cace.getImagePath());
    }
}
