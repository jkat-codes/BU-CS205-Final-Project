/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Uijin Kang
 * Date: 11/19/2025
 * Time: 9:31 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: BetTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BetTest {
    private Bet bet;
    @BeforeEach
    void setBet() {
        bet = new Bet();
    }

    @Test
    void testaddBet() {
        assertTrue(bet.getChips().isEmpty());

        bet.addBet(Chips.GREEN,5); // 25 * 5 = 125
        bet.addBet(Chips.GOLD, 3); // 100 * 3 = 300
        bet.addBet(Chips.RED, 3); // 5 * 3 = 15
        // Total = 440

        assertEquals(440, bet.calculateTotal());

        bet.addBet(Chips.BLUE, 2); // 10 * 2 = 20

        assertEquals(460, bet.calculateTotal());
    }

    @Test
    void testcalculateTotal() {
        assertEquals(0, bet.calculateTotal());

        bet.addBet(Chips.GOLD, 3); // 100 * 3 = 300
        bet.addBet(Chips.BLUE, 2); // 10 * 2 = 20
        // Total = 320

        assertEquals(320, bet.calculateTotal());
    }

    @Test
    void testresetBet() {
        bet.addBet(Chips.GOLD, 3); // 100 * 3 = 300
        bet.addBet(Chips.BLUE, 2); // 10 * 2 = 20
        // Total = 320

        bet.resetBet();

        assertEquals(0, bet.calculateTotal());
    }

    @Test
    void testgetChips() {
        bet.addBet(Chips.GOLD, 3); // 100 * 3 = 300
        bet.addBet(Chips.BLUE, 2); // 10 * 2 = 20
        // Total = 320

        Map<Chips, Integer> chips = bet.getChips();

        assertEquals(3, (int) chips.get(Chips.GOLD));
        assertEquals(2, (int) chips.get(Chips.BLUE));

        assertFalse(chips.containsKey(Chips.RED));
        assertFalse(chips.containsKey(Chips.GREEN));
    }
}

