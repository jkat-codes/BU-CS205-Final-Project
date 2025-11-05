package org.team01;

public enum Suit {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    SPADES("Spades"),
    CLUBS("Clubs");

    private final String value;

    Suit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
