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
}

