/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Uijin Kang
 * Date: 11/6/2025
 * Time: 10:59 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: PlayerTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.team01.Suit.*;


public class PlayerTest {
    private Player player;
    private Dealer dealer;
    @BeforeEach
    public void setPlayer() {
        player = new Player("Jane Doe");
    }

    @Test
    public void testgetCurrentBalance() {
        int balanceAmount = player.getCurrentBalance();
        assertEquals(2000,balanceAmount);
    }

    @Test
    public void testplaceBet() throws IllegalArgumentException {
        player.placeBet(500);
        assertEquals(1500,player.getBalance());

        player.resetHand();

        assertThrows(IllegalArgumentException.class, () -> player.placeBet(3000));
    }

//    @Test
//    public void testWon() {
//        // Add cards to player and dealer
//        Card card1 = new Card(HEARTS,10,"QUEEN");
//        Card card2 = new Card(SPADES, 4,"FOUR");
//        Card card3 = new Card(DIAMONDS, 9, "NINE");
//        Card card4 = new Card(CLUBS, 8, "EIGHT");
//        Card card5 = new Card(SPADES, 10, "KING");
//
//        player.addCardToHand(card1);
//        player.addCardToHand(card3);
//
//        dealer.addCardToHand(card2);
//        dealer.addCardToHand(card4);
//
//        assertTrue(player.won());
//
//    }


}

