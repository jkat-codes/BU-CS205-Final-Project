package org.team01.model;

/**
 * An enumerated type that defines color and value of Chips that player has
 * and used as wagers to place bets
 * --
 * Chip types:
 * - WHITE: white-colored chip which its value is $1
 * - RED: red-colored chip which its value is $5
 * - BLUE: blue-colored chip which its value is $10
 * - GREEN: green-colored chip which its value is $25
 * - GOLD: gold-colored chip which its value is $100
 *
 */
public enum Chips {
    WHITE(1),
    RED(5),
    BLUE(10),
    GREEN(25),
    GOLD(100);


    private final int chipValue;

    /**
     * Generate a chip in which its value is determined by chip types under enumerate
     *
     * @param chipValue value of chips
     */
    private Chips(int chipValue) {
        this.chipValue = chipValue;
    }

    /**
     * Get values from designated chips
     * @return chip value
     */
    public int getChipValue() {
        return this.chipValue;
    }
}
