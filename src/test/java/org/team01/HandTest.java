/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sanda Tan
 * Date: 11/9/25
 * Time: 10:15 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: HandTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team01.model.Card;
import org.team01.model.Hand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.team01.model.FaceValue.*;
import static org.team01.model.Suit.*;

public class HandTest {

    private Hand hand;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeEach
    public void setUp() {
//        Hand emptyHand = new Hand();
        hand = new Hand();
        card1 = new Card(HEARTS, 7, SEVEN);
        card2 = new Card(CLUBS, 1, ACE);
        card3 = new Card(DIAMONDS, 10, KING);
        card4 = new Card(DIAMONDS, 11, ACE);

    }

    @Test
    public void testHand() {
        assertTrue(hand.getCurrentHand().size() == 0);

    }

    @Test
    public void testAddCardToHand() {

        hand.addCardToHand(card1);
        assertTrue(hand.getCurrentHand().size() == 1);
        Card firstCard = hand.getCurrentHand().get(0);
        assertTrue(firstCard.getSuit() == HEARTS);
        assertTrue(firstCard.getRank() == SEVEN);
        assertTrue(firstCard.getValue() == 7);

        hand.addCardToHand(card2);
        assertTrue(hand.getCurrentHand().size() == 2);
        assertTrue(hand.getCurrentHand().get(0).getValue() == 7);
        Card secondCard = hand.getCurrentHand().get(1);
        assertTrue(secondCard.getSuit() == CLUBS);
        assertTrue(secondCard.getRank() == ACE);
        assertTrue(secondCard.getValue() == 1);
    }

    @Test
    public void testIsBlackJack() {
        hand.addCardToHand(card3);
        assertFalse(hand.isBlackJack());

        hand.addCardToHand(card4);
        assertTrue(hand.getHandValue() == 21);
        assertTrue(hand.isBlackJack());
    }

    @Test
    public void isBust() {
        hand.addCardToHand(card1);
        assertFalse(hand.isBust());
        assertEquals(7, hand.getHandValue());

        hand.addCardToHand(card3);
        assertFalse(hand.isBust());
        assertEquals(17, hand.getHandValue());

        // Add an ace, automatically changes to 18
        hand.addCardToHand(card4);
        assertEquals(18, hand.getHandValue());
    }

    @Test
    public void clearHand() {
        assertEquals(0, hand.getCurrentHand().size());

        hand.addCardToHand(card1);
        assertEquals(1, hand.getCurrentHand().size());
        assertTrue(hand.clearHand());
        assertEquals(0, hand.getCurrentHand().size());

        hand.addCardToHand(card2);
        hand.addCardToHand(card3);
        assertEquals(2, hand.getCurrentHand().size());
        assertTrue(hand.clearHand());
        assertEquals(0, hand.getCurrentHand().size());
    }

    @Test
    public void testGetCurrentHand() {
        hand.addCardToHand(card1);
        hand.addCardToHand(card3);
        hand.addCardToHand(card2);
        List<Card> currentHand = hand.getCurrentHand();

        assertEquals(card1, currentHand.get(0));
        assertEquals(card3, currentHand.get(1));
        assertEquals(card2, currentHand.get(2));
    }

    @Test
    public void testGetHandValue() {
        assertEquals(-1, hand.getHandValue());

        hand.addCardToHand(card1);
        assertEquals(7, hand.getHandValue());

        hand.addCardToHand(card3);
        // Add an ace, automatically converts to 1 from 11
        hand.addCardToHand(card4);
        assertEquals(18, hand.getHandValue());

        hand.clearHand();
        assertEquals(-1, hand.getHandValue());
    }

    @Test
    public void testToString() {

        assertEquals("[]", hand.toString());

        hand.addCardToHand(card1);
        assertEquals("[SEVEN of Hearts]", hand.toString());

        hand.addCardToHand(card2);
        assertEquals("[SEVEN of Hearts, ACE of Clubs]", hand.toString());

        hand.addCardToHand(card3);
        hand.addCardToHand(card4);
        assertEquals("[SEVEN of Hearts, ACE of Clubs, KING of Diamonds, ACE of Diamonds]", hand.toString());
    }
}
