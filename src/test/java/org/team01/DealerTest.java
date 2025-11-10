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
 * Class: DealerTest
 *
 * Description: Representation of a dealer. Has a hand that came be played according to dealer rules.
 *
 * ****************************************
 */

package org.team01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.team01.FaceValue.*;
import static org.team01.FaceValue.ACE;
import static org.team01.Suit.*;
import static org.team01.Suit.DIAMONDS;

public class DealerTest {

    Dealer dealer;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
        card1 = new Card(HEARTS, 7, SEVEN);
        card2 = new Card(CLUBS, 1, ACE);
        card3 = new Card(DIAMONDS, 10, KING);
        card4 = new Card(DIAMONDS, 11, ACE);
    }

    @Test
    public void testDealerConstructor() {
        assertInstanceOf(Dealer.class, dealer);
        assertInstanceOf(Participant.class, dealer);
        assertTrue(dealer.hand.getCurrentHand().isEmpty());
    }

    @Test
    public void testGetHandValue() {
        dealer.hand.addCardToHand(card1);
        assertEquals(7, dealer.getHandValue());

        dealer.hand.addCardToHand(card2);
        dealer.hand.addCardToHand(card3);
        assertEquals(18, dealer.getHandValue());
    }

    @Test
    public void testResetHand() {
        assertEquals(0, dealer.hand.getCurrentHand().size());

        dealer.hand.addCardToHand(card1);
        assertEquals(1, dealer.hand.getCurrentHand().size());
        assertTrue(dealer.hand.clearHand());
        assertEquals(0, dealer.hand.getCurrentHand().size());

        dealer.hand.addCardToHand(card2);
        dealer.hand.addCardToHand(card3);
        assertEquals(2, dealer.hand.getCurrentHand().size());
        assertTrue(dealer.hand.clearHand());
        assertEquals(0, dealer.hand.getCurrentHand().size());
    }

    @Test
    public void testGetHand() {
        dealer.hand.addCardToHand(card1);
        dealer.hand.addCardToHand(card1);
        dealer.hand.addCardToHand(card3);

        List<Card> currentHand = dealer.hand.getCurrentHand();

        assertEquals(card1, currentHand.get(0));
        assertEquals(card1, currentHand.get(1));
        assertEquals(card3, currentHand.get(2));
    }

    @Test
    public void testHasBlackjack() {
        assertFalse(dealer.hasBlackjack());

        dealer.hand.addCardToHand(card2);
        assertFalse(dealer.hasBlackjack());

        dealer.hand.addCardToHand(card3);
        dealer.hand.addCardToHand(card3);
        assertTrue(dealer.hasBlackjack());
    }

    @Test
    public void testShouldHit() {
        assertTrue(dealer.shouldHit());

        dealer.hand.addCardToHand(card1);
        assertTrue(dealer.shouldHit());

        dealer.hand.addCardToHand(card3);
        assertFalse(dealer.shouldHit());

        dealer.hand.clearHand();
        dealer.hand.addCardToHand(new Card(HEARTS, 6, SEVEN));
        dealer.hand.addCardToHand(card3);
        assertTrue(dealer.shouldHit());
    }

    @Test
    public void testHasNaturalBlackjack() {

        assertFalse(dealer.hasNaturalBlackjack());

        dealer.hand.addCardToHand(card3);
        dealer.hand.addCardToHand(card3);
        dealer.hand.addCardToHand(card2);
        assertEquals(21, dealer.getHandValue());
        assertFalse(dealer.hasNaturalBlackjack());

        dealer.hand.clearHand();

        dealer.hand.addCardToHand(card3);
        dealer.hand.addCardToHand(card4);
        assertEquals(21, dealer.getHandValue());
        assertTrue(dealer.hasNaturalBlackjack());

    }

    @Test
    public void testPlayDealerTurn() {

        assertTrue(dealer.playDealerTurn(card1));
        assertTrue(dealer.playDealerTurn(card2));
        assertTrue(dealer.playDealerTurn(card3));
        assertEquals(18, dealer.getHandValue());

        assertFalse(dealer.playDealerTurn(card2));
        assertEquals(18, dealer.getHandValue());
    }

    @Test
    public void testShouldRevealCard() {
        assertFalse(dealer.shouldRevealCard());

        dealer.hand.addCardToHand(card3);
        assertFalse(dealer.shouldRevealCard());

        dealer.hand.addCardToHand(card3);
        assertFalse(dealer.shouldRevealCard());

        dealer.hand.addCardToHand(card2);
        assertTrue(dealer.shouldRevealCard());

        dealer.hand.addCardToHand(card2);
        assertFalse(dealer.shouldRevealCard());
    }
}
