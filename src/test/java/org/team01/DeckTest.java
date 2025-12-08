package org.team01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team01.model.Card;
import org.team01.model.Deck;
import org.team01.model.FaceValue;
import org.team01.model.Suit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testDeckConstructor() {
        Deck testDeck = new Deck();
        assertTrue(testDeck.getCards().isEmpty());
    }

    @Test
    void testDeckCreation() {
        deck.shuffle();
        boolean result = deck.getCardsRemaining() == 52;
        assertTrue(result);
        assertFalse(deck.getCards().isEmpty());
    }

    @Test
    void testProperCardsInEachSuit() {
        Deck test = new Deck();
        test.shuffle();

        int spadesCount = 0;
        int clubsCount = 0;
        int heartsCount = 0;
        int diamondsCount = 0;

        for (int i = 0; i < test.getCards().size(); i++) {
            Suit suit = test.get(i).getSuit();

            switch (suit) {
                case Suit.SPADES:
                    spadesCount += 1;
                    break;
                case Suit.CLUBS:
                    clubsCount += 1;
                    break;
                case Suit.HEARTS:
                    heartsCount += 1;
                    break;
                case Suit.DIAMONDS:
                    diamondsCount += 1;
                    break;
                default:
                    break;
            }
        }

        assertTrue(spadesCount == 13);
        assertTrue(clubsCount == 13);
        assertTrue(heartsCount == 13);
        assertTrue(diamondsCount == 13);
    }

    @Test
    void testNoDuplicateCards() {
        Deck duplicateTest = new Deck();
        duplicateTest.shuffle();
        List<Card> existingCards = new ArrayList<>();

        for (int i = 0; i < duplicateTest.getCards().size(); i++) {
            Card card = duplicateTest.get(i);
            assertFalse(existingCards.contains(card));
            existingCards.add(card);
        }
    }

    @Test
    void testWorkingShuffle() {
        Deck test = new Deck();
        test.shuffle();
        List<Card> test1Shuffle = test.getCards();

        test.shuffle();
        List<Card> test2Shuffle = test.getCards();

        assertNotEquals(test1Shuffle, test2Shuffle);
    }

    @Test
    void testShuffleCardAmounts() {
        Deck test = new Deck();
        for (int i = 0; i < 10; i++) {
            test.shuffle();
            assertEquals(52, test.getCards().size());
        }
    }

    @Test
    void testShuffleAfterDeal() {
        Deck test = new Deck();
        test.shuffle();
        List<Card> test1Shuffle = test.getCards();
        test.deal();
        test.deal();
        test.shuffle();
        List<Card> test2Shuffle = test.getCards();

        assertNotEquals(test1Shuffle, test2Shuffle);
        assertEquals(52, test1Shuffle.size());
        assertEquals(50, test2Shuffle.size());
    }

    @Test
    void testFirstCardDealt() {
        Deck test = new Deck();
        test.shuffle();
        Card firstCard = test.get(0);
        Card dealtCard = test.deal();
        assertEquals(firstCard, dealtCard);
        assertEquals(51, test.getCardsRemaining());
    }

    @Test
    void testUniqueDeals() {
        Deck test = new Deck();
        List<Card> dealtCards = new ArrayList<>();
        test.shuffle();
        for (int i = 0; i < 52; i++) {
            Card dealtCard = test.deal();
            assertFalse(dealtCards.contains(dealtCard));
            dealtCards.add(dealtCard);
        }
    }

    @Test
    void testDealAllCards() {
        Deck test = new Deck();
        test.shuffle();
        for (int i = 0; i <= 52; i++) {
            test.deal();
        }

        // After deal the deck should recreate itself and deal the first card
        assertEquals(51, test.getCards().size());
    }

    @Test void testAllFaceCards() {
        Deck test = new Deck();
        test.shuffle();
        for (int i = 0; i <= 52; i++) {
            Card card = test.deal();
            if (card.getRank().equals(FaceValue.KING) || card.getRank().equals(FaceValue.QUEEN) || card.getRank().equals(FaceValue.JACK)) {
                assertEquals(10, card.getValue());
            }
        }
    }
}