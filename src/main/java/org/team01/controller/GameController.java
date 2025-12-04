/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Jasper Katalevsky
 * Date: 11/19/25
 * Time: 10:11 AM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: GameController
 *
 * Description:
 *
 * ****************************************
 */

package org.team01.controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.team01.*;
import org.team01.view.GameFX;

import java.util.List;

/**
 * Class that connects our Game model with our UI Game view
 */
public class GameController {
    private Game model;
    private GameFX view;

    // UI elements we need to control
    private HBox dealerArea;
    private HBox playerArea;
    private Label balanceLabel;
    private Button hitButton;
    private Button standButton;
    private Double balanceBefore;
    private Double balanceAfter;
    private Button white;
    private Button red;
    private Button blue;
    private Button green;
    private Button gold;
    private boolean betPlaced;
    private Button startGameButton;
    private Bet currentBet;
    private Label currentBetLabel;
    private Button clearBetButton;
    private boolean dealerCardRevealed;
    private int lastDealerCardCount = 2;
    private int lastPlayerCardCount = 2;
    private boolean initialDealComplete;

    /**
     * Creates a new GameController object
     *
     * @param model the Game model that we are playing with, contains all BlackJack related functions
     * @param view the UI view that we are using to render our cards, bets, and gameplay
     */
    public GameController(Game model, GameFX view) {
        this.model = new Game();
        this.view = view;
    }

    /**
     * Initializes all UI areas and components
     *
     * @param dealerArea UI area for dealer cards
     * @param playerArea UI area for player cards
     * @param balanceLabel Label showing the player's current balance
     * @param hitButton Button for player hit
     * @param standButton Button for player stand
     * @param white Button for white chip
     * @param red Button for red chip
     * @param blue Button for blue chip
     * @param green Button for green chip
     * @param gold Button for gold chip
     * @param startGameButton Button for placing bet/starting new game
     * @param currentBetLabel Label showing the player's current bet
     * @param clearBetButton Button for clearing the player's potential bet (not placed yet)
     */
    public void initialize(HBox dealerArea, HBox playerArea, Label balanceLabel, Button hitButton, Button standButton, Button white, Button red, Button blue, Button green, Button gold, Button startGameButton, Label currentBetLabel, Button clearBetButton) {
        this.dealerArea = dealerArea;
        this.playerArea = playerArea;
        this.balanceLabel = balanceLabel;
        this.hitButton = hitButton;
        this.standButton = standButton;
        this.white = white;
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.gold = gold;
        this.betPlaced = false;
        this.currentBet = new Bet();
        this.startGameButton = startGameButton;
        this.currentBetLabel = currentBetLabel;
        this.clearBetButton = clearBetButton;
        this.dealerCardRevealed = false;
        this.initialDealComplete = false;

        // Setup event handlers
        setupEventHandlers();

        // Start a new game
        handleNewGame();
    }

    /**
     * Handles a new game, generally called after a game has been completed
     * Resets button states, cards, and bets
     */
    private void handleNewGame() {
        boolean success = this.model.initializeNewGame();
        if (success) {
            clearCards();

            // Re-enable the chips
            gold.setDisable(false);
            white.setDisable(false);
            red.setDisable(false);
            blue.setDisable(false);
            green.setDisable(false);

            // Disable the hit/stand buttons
            hitButton.setDisable(true);
            standButton.setDisable(true);

            // Re-enable the start game button
            startGameButton.setDisable(false);
            clearBetButton.setDisable(false);

            betPlaced = false;
            currentBet = new Bet();
            currentBetLabel.setText("Current Bet: $");
        } else {
            System.out.println("Unable to start new game...");
        }
    }

    /**
     * Function to handle dealing the initial cards of the game
     * Deals, alternating, to player then dealer, the dealers second card is hidden
     */
    private void handleDeal() {
        // Deal the cards from model
        GameResult result = this.model.dealInitialCards();
        // Update the cards to display the cards
        updateCardDisplay();

        if (result != GameResult.IN_PROGRESS)  {
            handleGameEnd(result);
        } else {
            // Update button states here
        }
    }

    /**
     * Function to set up all button event handlers
     */
    private void setupEventHandlers() {
        if (this.hitButton != null) {
            hitButton.setOnAction(e -> handleHit());
        }

        if (this.standButton != null) {
            standButton.setOnAction(e -> handleStand());
        }

        if (this.white != null) {
            white.setOnAction(e -> handlePlaceBet(1));
        }

        if (this.red != null) {
            red.setOnAction(e -> handlePlaceBet(5));
        }

        if (this.blue != null) {
            blue.setOnAction(e -> handlePlaceBet(10));
        }

        if (this.green != null) {
            green.setOnAction(e -> handlePlaceBet(25));
        }

        if (this.gold != null) {
            gold.setOnAction(e -> handlePlaceBet(100));
        }

        if (this.startGameButton != null) {
            startGameButton.setOnAction(e -> handleStartGame());
        }

        if (this.clearBetButton != null) {
            this.clearBetButton.setOnAction(e -> handleClearBet());
        }
    }

    /**
     * Function to handle when the player hits
     * Adds a card to the player's hand
     */
    private void handleHit() {
        // Call hit through the model
        int cardsBeforeStand = model.getPlayerCards().getCurrentHand().size();
        GameResult result = this.model.playerHit();

        int cardsAfterStand = model.getPlayerCards().getCurrentHand().size();
        int newCards  = cardsAfterStand - cardsBeforeStand;

        updateCardDisplay();

        if (result != GameResult.IN_PROGRESS) {
            long extraAnimationTime = 500 + (newCards * 200) + 200;

            PauseTransition waitForCards = new PauseTransition(Duration.millis(extraAnimationTime));
            waitForCards.setOnFinished(event -> {
                Platform.runLater(() -> handleGameEnd(result));
            });
            waitForCards.play();
        }
    }

    /**
     * Function to handle when the player stands
     * Flips the dealer's card then automatically plays the dealers turn
     */
    private void handleStand() {
        revealDealerCard();

        PauseTransition waitForFlip = new PauseTransition(Duration.millis(800));
        waitForFlip.setOnFinished(e -> {
            this.dealerCardRevealed = true;

            int cardsBeforeStand = model.getDealerCards().getCurrentHand().size();

            // Call stand through the model
            GameResult result = this.model.playerStand();

            int cardsAfterStand = model.getDealerCards().getCurrentHand().size();
            int newCards  = cardsAfterStand - cardsBeforeStand;

            updateCardDisplay();

            // Disable the hit and stand buttons
            if (result != GameResult.IN_PROGRESS) {
                long extraAnimationTime = 500 + (newCards * 200) + 200;

                PauseTransition waitForCards = new PauseTransition(Duration.millis(extraAnimationTime));
                waitForCards.setOnFinished(event -> {
                    Platform.runLater(() -> handleGameEnd(result));
                });
                waitForCards.play();
            }
        });

        waitForFlip.play();

    }

    /**
     * Function that handles the start of a new game
     * Enables/Disables certain buttons allowing certain behaviors during game; shifts state to betting; then deals cards
     */
    private void handleStartGame() {
        if (this.currentBet.calculateTotal() > 0) {
            balanceBefore = this.model.getPlayerBalance();
            if (!this.model.placeBet(this.currentBet)) {
                showNotEnoughFundsAlert();
                return;
            }

            handleUpdateBalance();
            betPlaced = true;

            // Disable the chips and start new game
            gold.setDisable(true);
            white.setDisable(true);
            red.setDisable(true);
            blue.setDisable(true);
            green.setDisable(true);
            startGameButton.setDisable(true);
            clearBetButton.setDisable(true);

            // Re-enable the hit/stand buttons
            hitButton.setDisable(false);
            standButton.setDisable(false);

            currentBet = new Bet();

            handleDeal();

            // Check if the player's balance is 0 after the result
            if (this.model.getPlayerBalance() <= 0) {
                showGameOverAlert();
            }

            //Disable all controls after bankrupt
            gold.setDisable(true);
            white.setDisable(true);
            red.setDisable(true);
            blue.setDisable(true);
            green.setDisable(true);
            startGameButton.setDisable(true);
            clearBetButton.setDisable(true);
            hitButton.setDisable(true);
            standButton.setDisable(true);
        }
    }

    private void handleEndGame() {

    }

    /**
     * Function that clears the player's active bet
     * Used when a new round is started or when the player wants to bet a different amount BEFORE a new round
     */
    private void handleClearBet() {
        if (this.currentBet.calculateTotal() > 0) {
            currentBet = new Bet();
            betPlaced = false;
            handleUpdateBalance();
            currentBetLabel.setText("Current Bet: $");
        }
    }

    /**
     * Function that handles the player placing a bet
     *
     * @param denomination the desired amount that the user wants to bet
     */
    private void handlePlaceBet(int denomination) {
        switch (denomination) {
            case 1:
                currentBet.addBet(Chips.WHITE, 1);
                break;
            case 5:
                currentBet.addBet(Chips.RED, 1);
                break;
            case 10:
                currentBet.addBet(Chips.BLUE, 1);
                break;
            case 25:
                currentBet.addBet(Chips.GREEN, 1);
                break;
            case 100:
                currentBet.addBet(Chips.GOLD, 1);
                break;
            default:
                break;
        }

        handleUpdateBalance();
        int total = currentBet.calculateTotal();
        currentBetLabel.setText("Current Bet: $" + total);

        betPlaced = true;
    }

    /**
     * Function that updates the label that shows the player's balance
     */
    private void handleUpdateBalance() {
        Double newBalance = this.model.getPlayerBalance();
        Platform.runLater(() -> {
            if (balanceLabel != null) {
                balanceLabel.setText("$" + String.format("%.2f", newBalance));
            }
        });
    }

    /**
     * Function that animates the dealing of the cards (slide in from right to left)
     *
     * @param cardView the ImageView of the card being animated
     * @param area the desired UI location where we want the card to be dealt/placed
     * @param cardIndex the index of the card being dealt, used to delay the cards being dealt
     */
    private void animateCardDeal(ImageView cardView, HBox area, int cardIndex) {
        cardView.setTranslateX(200);
        cardView.setTranslateY(0);
        cardView.setOpacity(0);

        area.getChildren().add(cardView);

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(500), cardView);
        slideIn.setToX(0);
        slideIn.setToY(0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), cardView);
        fadeIn.setToValue(1);


        ParallelTransition dealAnimation = new ParallelTransition(slideIn, fadeIn);
        dealAnimation.setDelay(Duration.millis(cardIndex * 200));
        dealAnimation.play();
    }

    /**
     * Function that animates the flipping of the dealer's hidden card
     *
     * @param hiddenCard the ImageView of the hiddenCard
     * @param actualCard the Card representation of the hiddenCard
     * @param area the UI area in which the card/hidden card is contained
     */
    private void flipCard(ImageView hiddenCard, Card actualCard, HBox area) {
        ImageView newCardView = this.view.createCardView(actualCard);
        newCardView.setScaleX(0);

        RotateTransition rotateOut = new RotateTransition(Duration.millis(500), hiddenCard);
        rotateOut.setAxis(Rotate.Y_AXIS);
        rotateOut.setByAngle(90);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(500), hiddenCard);
        scaleOut.setToX(0);

        ParallelTransition flipOut = new ParallelTransition(rotateOut, scaleOut);

        flipOut.setOnFinished(e -> {
            int index = area.getChildren().indexOf(hiddenCard);
            area.getChildren().set(index, newCardView);

            RotateTransition rotateIn = new RotateTransition(Duration.millis(500), newCardView);
            rotateIn.setAxis(Rotate.Y_AXIS);
            rotateIn.setFromAngle(-90);
            rotateIn.setToAngle(0);

            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), newCardView);
            scaleIn.setFromX(0);
            scaleIn.setToX(1);

            DropShadow glow = new DropShadow();
            glow.setColor(Color.WHITESMOKE);
            glow.setRadius(20);
            newCardView.setEffect(glow);

            ParallelTransition flipIn = new ParallelTransition(rotateIn, scaleIn);
            flipIn.setOnFinished(event -> {
                FadeTransition fadeGlow = new FadeTransition(Duration.millis(500), newCardView);
                fadeGlow.setOnFinished(e2 -> newCardView.setEffect(null));
                fadeGlow.play();
            });

            flipIn.play();
        });
        flipOut.play();
    }

    /**
     * Function that reveals the dealers card, with flip and highlight animation(s)
     */
    private void revealDealerCard() {
        Platform.runLater(() -> {
            for (Node node: dealerArea.getChildren()) {
               if (node instanceof ImageView && "HIDDEN_DEALER_CARD".equals(node.getUserData()))  {
                   ImageView hiddenCard = (ImageView) node;
                   int cardIndex = dealerArea.getChildren().indexOf(hiddenCard);

                   if (model.getDealerCards() != null && model.getDealerCards().getCurrentHand().size() > cardIndex) {
                       Card actualCard = model.getDealerCards().getCurrentHand().get(cardIndex);
                       flipCard(hiddenCard, actualCard, dealerArea);
                   }
                   break;
               }
            }
        });
    }

    /**
     * Function that updates the UI for both the player and dealer card areas
     * If the dealer has only 2 cards, the first will be revealed and the second will be flipped
     */
    private void updateCardDisplay() {
        Platform.runLater(() -> {
            // Update dealer cards
            if (!this.dealerCardRevealed) {
                // Deal the initial cards
                if (!initialDealComplete) {
                    if (model.getPlayerCards() != null) {
                        int index = 0;
                        for (Card card : model.getPlayerCards().getCurrentHand()) {
                            ImageView cardView = this.view.createCardView(card);
                            animateCardDeal(cardView, this.playerArea, index);
                            index++;
                        }
                    }
                    // Update dealer cards
                    if (model.getDealerCards() != null) {
                        int index = 0;
                        for (Card card : model.getDealerCards().getCurrentHand()) {
                            index += 1;
                            if (model.getDealerCards().getCurrentHand().size() == 2 && index == 2 && model.getGameState() == GameState.PLAYER_TURN) {
                                ImageView backView = this.view.createBackCardView();
                                animateCardDeal(backView, this.dealerArea, index);
                            }else {
                                ImageView cardView = this.view.createCardView(card);
                                animateCardDeal(cardView, this.dealerArea, index);
                            }
                        }
                    }

                    initialDealComplete = true;
                } else {
                    // Cards have already been dealt, now we add to player cards
                    if (model.getPlayerCards() != null) {
                        List<Card> hand = model.getPlayerCards().getCurrentHand();
                        for (int i = lastPlayerCardCount; i < hand.size(); i++) {
                            ImageView cardView = this.view.createCardView(hand.get(i));
                            animateCardDeal(cardView, this.playerArea, i);
                        }
                        lastPlayerCardCount = hand.size();
                    }
                }
            } else {
                if (model.getDealerCards() != null) {
                    List<Card> hand = model.getDealerCards().getCurrentHand();
                    for (int i = lastDealerCardCount; i < hand.size(); i++) {
                        ImageView cardView = this.view.createCardView(hand.get(i));
                        animateCardDeal(cardView, this.dealerArea, i);
                    }
                    lastDealerCardCount = hand.size();
                }
            }
        });
    }

    /**
     * General function to show an alert with title and content
     *
     * @param title the title of the desired alert
     * @param content the content of the desired alert
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();

    }

    /**
     * Shows an alert warning the user that they do not have enough funds
     */
    private void showNotEnoughFundsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not enough funds!");
        alert.setHeaderText("Not enough funds!");
        alert.setContentText("You do not have enough chips to place this bet! Please adjust your bet.");
        alert.showAndWait();
    }

    /**
     * Shows an alert warning the user that they ran out of balance to continue the game
     */
    private void showGameOverAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Game Over! You Lost.");
        alert.setContentText("You do not have enough balance to place bet! Restart the game to continue.");
        alert.showAndWait();
    }

    /**
     * Gets a result message based on the outcome of the game i.e. win/loss/tie messaage
     *
     * @param result the GameResult of the current game
     * @return String representation of the outcome of the game
     */
    private String getResultMessage(GameResult result) {
        String output = "";
        switch (result) {
            case BOTH_BLACKJACK:
            case PUSH:
                output = "Tie game!";
                break;
            case PLAYER_BLACKJACK:
                double payout = balanceAfter - balanceBefore;
                output = "BlackJack! You win! \n +$" + payout;
                break;
            case DEALER_BLACKJACK:
                output = "Dealer BlackJack, you lose.";
                break;
            case PLAYER_BUST:
                output = "Busted! You lose.";
                break;
            case DEALER_BUST:
                output = "Dealer busted! You win! \n +$" + (balanceAfter - balanceBefore);
                break;
            case PLAYER_WIN:
                output = "You win! \n +$" + (balanceAfter - balanceBefore);
                break;
            case DEALER_WIN:
                output = "Dealer wins, you lose.";
                break;
            default:
                break;
    }

        return output;
    }

    /**
     * Handles the ending of the current game
     *
     * @param result the GameResult of the current game, used to get a state relative finishing message
     */
    private void handleGameEnd(GameResult result) {
        balanceAfter = this.model.getPlayerBalance();
        showAlert("Game over!", getResultMessage(result));
        // Update player balance
        handleUpdateBalance();
        // Clear the cards
        clearCards();
        // Reset bet
        betPlaced = false;
        // Reset hidden card
        dealerCardRevealed = false;
        // Reset initial deal
        initialDealComplete = false;
        // Reset dealer count
        lastDealerCardCount = 2;
        // Reset player count
        lastPlayerCardCount = 2;
        // Reset game
        handleNewGame();
    }

    /**
     * Clears all cards from dealer area and player area (removes them from the UI)
     */
    private void clearCards() {
        Platform.runLater(() -> {
            this.dealerArea.getChildren().clear();
            this.playerArea.getChildren().clear();
        });
    }
}
