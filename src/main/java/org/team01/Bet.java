/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Uijin Kang
 * Date: 11/12/2025
 * Time: 10:41 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: Bet
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private Map<Chips, Integer> chips;

    /**
     *
     * Constructs a new Bet with initial value zero
     */
    public Bet() {
        this.chips = new HashMap<>();
    }

    /**
     * Add chips to the bet
     * @param chip the type of chip on the bet (ex: Green, Blue, Black)
     * @param amount the number of chips that are on the bet
     */
    public void addBet(Chips chip, int amount) {
        int current = chips.getOrDefault(chip,0);
        chips.put(chip, current + amount);
    }

    /**
     * Identify the total bet value
     * @return total bet value with existing chips, or 0 if there is no chips available to bet or empty
     */
    public int calculateTotal() {
        if (chips == null || chips.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Chips chip : chips.keySet()) {
            int quantity = chips.get(chip);
            sum += chip.getChipValue() * quantity;
        }
        return sum;
    }

    public void resetBet() {
        chips.clear();
    }

    public Map<Chips, Integer> getChips() {
        return chips;
    }
}

