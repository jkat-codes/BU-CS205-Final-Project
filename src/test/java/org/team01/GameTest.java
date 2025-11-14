/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Jasper Katalevsky
 * Date: 11/14/25
 * Time: 10:09 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: GameTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testInitialization() {
        assertEquals(GameState.MENU, game.getGameState());

        game.initializeNewGame();
        assertEquals(GameState.BETTING, game.getGameState());
    }

    @Test
    public void testStartNewRound() {
        game.initializeNewGame();
        game.startNewRound();
        assertEquals(GameState.BETTING, game.getGameState());

        Game newGame = new Game();
        assertFalse(newGame.startNewRound());
    }

    @Test
    public void testBetting() {
        game.initializeNewGame();
        game.startNewRound();
        game.placeBet(10);
        assertEquals(GameState.DEALING, game.getGameState());

        Game newGame = new Game();
        assertFalse(newGame.placeBet(10));
    }

    @Test
    public void testInitialDeal() {
        game.initializeNewGame();
        game.startNewRound();
        game.placeBet(10);
        game.dealInitialCards();
        assertEquals(GameState.PLAYER_TURN, game.getGameState());

        Game newGame = new Game();
        assertNull(newGame.dealInitialCards());
    }

    @Test
    public void testPlayerActions() {
        game.initializeNewGame();
        game.startNewRound();
        game.placeBet(10);
        game.dealInitialCards();

        GameResult result = game.playerHit();

        if (result != GameResult.IN_PROGRESS) {
            assertEquals(GameState.GAME_OVER, game.getGameState());
        }

        Game newGame = new Game();
        assertNull(newGame.playerHit());
    }

    @Test
    public void testDealerActions() {
        game.initializeNewGame();
        game.startNewRound();
        game.placeBet(10);
        game.dealInitialCards();

        // Force instant stand (automatically calls dealerPlay())
        game.playerStand();
        assertEquals(GameState.GAME_OVER, game.getGameState());

        Game newGame = new Game();
        assertNull(newGame.dealerPlay());
        assertNull(newGame.playerStand());
    }
}
