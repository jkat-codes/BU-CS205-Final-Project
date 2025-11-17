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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.team01.Suit.*;


public class PlayerTest {
    private Player player;
    private Bet bet;

    @BeforeEach
    public void setPlayer() {
        player = new Player("Jane Doe");
        bet = new Bet();
    }

    @Test
    public void testgetCurrentBalance() {
        double balanceAmount = player.getCurrentBalance();
        assertEquals(1000,balanceAmount);
    }

    @Test
    public void testplaceBet() throws IllegalArgumentException {
        bet.addBet(Chips.BLACK, 2); // Sum: 100 * 2 = 200
        bet.addBet(Chips.BLUE, 1);  // Sum: 10 * 1 = 10
        player.placeBet(bet);
        assertEquals(790,player.getCurrentBalance());

        player.resetHand();

        bet.addBet(Chips.BLACK, 8); // Sum: 100 * 8 = 800
        //Total: 1100

        assertThrows(IllegalArgumentException.class, () -> player.placeBet(bet));
    }

    @Test
    public void testgetCurrentBet() {
        // Check if currentBet is null before bet is placed
        assertNull(player.getCurrentBet());

        // Check if currentBet amount changes after bet is placed
        bet.addBet(Chips.BLACK, 2); // Sum: 100 * 2 = 200
        bet.addBet(Chips.BLUE, 1);  // Sum: 10 * 1 = 10

        player.placeBet(bet);
        Bet current = player.getCurrentBet();

        assertEquals(210,current.calculateTotal());
    }

//    @Test
//    public void testPayoutPlayer() {
//        double balance = player.getCurrentBalance();
//        Bet currentBet = player.getCurrentBet();
//
//        // Test payout with no active bet
//        assertFalse(player.payPlayer(1));
//
//        // Test payout with a tie (push)
//        bet.addBet(Chips.BLACK,3); // 100 * 3 = 300
//        bet.addBet(Chips.GREEN,8); // 25 * 8 = 200
//
//        player.placeBet(bet);
//        // Simulates a tie (push)
//        player.payPlayer(1);
//        assertEquals(500, player.getCurrentBalance());
//        // Ensure bet is reset
//        assertNull(player.getCurrentBet());
//
//        // Test payout with a win (dealer bust)
//        player.placeBet(bet);
//        // Simulates a win (dealer bust) (2:1 payout)
//        player.payPlayer(2);
//        assertEquals(1000, player.getCurrentBalance());
//        // Ensure bet is reset
//        assertNull(player.getCurrentBet());
//
//        // Test payout with a BlackJack
//        player.placeBet(bet);
//        double balanceBeforeBlackJack = player.getCurrentBalance();
//        // Simulates a BlackJack (3:2 payout)
//        player.payPlayer(2.5); // Lossy int conversion
//
//        // Ensure bet is reset
//        assertNull(player.getCurrentBet());
//        assertEquals(1250, player.getCurrentBalance());
//        assertEquals(2250, balanceBeforeBlackJack + (500 * 2.5));
//    }

    @Test
    public void testInvalidPayouts() {
        bet.addBet(Chips.BLUE, 1);  // Sum: 10 * 1 = 10

        Player test = new Player("test");
        Random random = new Random();

        // No active bet
        assertFalse(test.payPlayer(1));

        // False multiplier amounts
        test.placeBet(bet);
        for (int i = 0; i < 20; i++) {
            double randomMultiplier = random.nextDouble() * (100 - 3) + 3;
            assertFalse(test.payPlayer(randomMultiplier));
        }
    }

    @Test
    public void testInvalidBetReset() {
        Player test = new Player("test");

        // No active bet
        assertFalse(test.resetBet());
    }
}

