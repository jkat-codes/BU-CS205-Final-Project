package org.team01;

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
