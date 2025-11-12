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

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.team01.Suit.*;


public class PlayerTest {
    private Player player;
    @BeforeEach
    public void setPlayer() {
        player = new Player("Jane Doe");
    }

    @Test
    public void testgetCurrentBalance() {
        double balanceAmount = player.getCurrentBalance();
        assertEquals(2000,balanceAmount);
    }

    @Test
    public void testplaceBet() throws IllegalArgumentException {
        player.placeBet(500);
        assertEquals(1500,player.getCurrentBalance());

        player.resetHand();

        assertThrows(IllegalArgumentException.class, () -> player.placeBet(3000));
    }

    @Test
    public void testgetCurrentBet() {
//        assertEquals(0,player.getCurrentBet());

        player.placeBet(1000);
//        assertEquals(1000,player.getCurrentBet());
    }

    @Test
    public void testPayoutPlayer() {
        double balance = player.getCurrentBalance();

        // Test payout with no active bet
        assertFalse(player.payPlayer(1));

        // Test payout with an active bet
        player.placeBet(1000);
        // Simulates a tie (push)
        player.payPlayer(1);
        assertEquals(2000, player.getCurrentBalance());

        player.placeBet(1000);
        // Simulates a win (dealer bust) (2:1 payout)
        player.payPlayer(2);
        assertEquals(3000, player.getCurrentBalance());

        player.placeBet(1000);
        double balanceBeforeBlackJack = player.getCurrentBalance();
        // Simulates a BlackJack (3:2 payout)
        player.payPlayer(2.5); // Lossy int conversion

        assertEquals(4500, player.getCurrentBalance());
        assertEquals(4500, balanceBeforeBlackJack + (1000 * 2.5));
    }

    @Test
    public void testInvalidPayouts() {
        Player test = new Player("test");
        Random random = new Random();

        // No active bet
        assertFalse(test.payPlayer(1));

        // False multiplier amounts
        test.placeBet(10);
        for (int i = 0; i < 20; i++) {
            double randomMultiplier = random.nextDouble() * (100 - 3) + 3;
            assertFalse(test.payPlayer(randomMultiplier));
        }
    }
}

