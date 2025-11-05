package org.team01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testDeck() {
        deck.shuffle();
        boolean result = deck.getCardsRemaining() == 52;
        assertTrue(result);
    }
}