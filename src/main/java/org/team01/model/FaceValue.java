package org.team01.model;

/**
 * An enumerated type that defines the type of the cards (number of face)
 * as well as its value and name
 * --
 * Card Types:
 * - ACE: number card of Ace with value of 1
 * - TWO: number card wih value of 2
 * - THREE: number card with value of 3
 * - FOUR: number card with value of 4
 * - FIVE: number card with value of 5
 * - SIX: number card with value of 6
 * - SEVEN: number card with value of 7
 * - EIGHT: number card with value of 8
 * - NINE: number card with value of 9
 * - TEN: number card with value of 10
 * - JACK: face card of Jack wih value of 10
 * - QUEEN: face card of Queen with value of 10
 * - KING: face card of King with value of 10
 */
public enum FaceValue {
    ACE(11, "ACE"),
    TWO(2, "TWO"),
    THREE(3, "THREE"),
    FOUR(4, "FOUR"),
    FIVE(5, "FIVE"),
    SIX(6, "SIX"),
    SEVEN(7, "SEVEN"),
    EIGHT(8, "EIGHT"),
    NINE(9, "NINE"),
    TEN(10, "TEN"),
    JACK(10, "JACK"),
    QUEEN(10, "QUEEN"),
    KING(10, "KING");

    private final int value;
    private final String faceString;

    FaceValue(int value, String faceString) {
        this.value = value;
        this.faceString = faceString;
    }

    public String getFaceString() {
        return this.faceString;
    }

    public int getFaceValue() {
        return this.value;
    }
}
