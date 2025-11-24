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

package org.team01;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    private Button black;
    private boolean betPlaced;
    private Button startGameButton;
    private Bet currentBet;
    private Label currentBetLabel;
    private Button clearBetButton;

    public GameController(Game model, GameFX view) {
        this.model = new Game();
        this.view = view;
    }

    public void initialize(HBox dealerArea, HBox playerArea, Label balanceLabel, Button hitButton, Button standButton, Button white, Button red, Button blue, Button green, Button black, Button startGameButton, Label currentBetLabel, Button clearBetButton) {
        this.dealerArea = dealerArea;
        this.playerArea = playerArea;
        this.balanceLabel = balanceLabel;
        this.hitButton = hitButton;
        this.standButton = standButton;
        this.white = white;
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.black = black;
        this.betPlaced = false;
        this.currentBet = new Bet();
        this.startGameButton = startGameButton;
        this.currentBetLabel = currentBetLabel;
        this.clearBetButton = clearBetButton;

        // Setup event handlers
        setupEventHandlers();

        // Start a new game
        handleNewGame();
    }

    private void handleNewGame() {
        boolean success = this.model.initializeNewGame();
        if (success) {
            clearCards();

            // Re-enable the chips
            black.setDisable(false);
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

    private void handleDeal() {
        // Deal the cards from model
        GameResult result = this.model.dealInitialCards();
        System.out.println("Result after deal: " + result);
        // Update the cards to display the cards
        updateCardDisplay();

        if (result != GameResult.IN_PROGRESS)  {
            handleGameEnd(result);
        } else {
            // Update button states here
        }
    }


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

        if (this.black != null) {
            black.setOnAction(e -> handlePlaceBet(100));
        }

        if (this.startGameButton != null) {
            startGameButton.setOnAction(e -> handleStartGame());
        }

        if (this.clearBetButton != null) {
            this.clearBetButton.setOnAction(e -> handleClearBet());
        }
    }


    private void handleHit() {
        // Call hit through the model
        GameResult result = this.model.playerHit();

        updateCardDisplay();

        if (result != GameResult.IN_PROGRESS) {
            handleGameEnd(result);
        }
    }

    private void handleStand() {
        // Call stand through the model
        GameResult result = this.model.playerStand();

        updateCardDisplay();

        // Disable the hit and stand buttons

        if (result != GameResult.IN_PROGRESS) {
            handleGameEnd(result);
        }
    }

    private void handleStartGame() {
        if (this.currentBet.calculateTotal() > 0) {
            balanceBefore = this.model.getPlayerBalance();
            this.model.placeBet(this.currentBet);
            handleUpdateBalance();
            betPlaced = true;

            // Disable the chips and start new game
            black.setDisable(true);
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
        }
    }

    private void handleClearBet() {
        if (this.currentBet.calculateTotal() > 0) {
            currentBet = new Bet();
            betPlaced = false;
            handleUpdateBalance();
            currentBetLabel.setText("Current Bet: $");

            System.out.println(this.model.getPlayerBalance());
        }
    }

    // Handler for each of the different chips
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
                currentBet.addBet(Chips.BLACK, 1);
                break;
            default:
                break;
        }

        handleUpdateBalance();
        int total = currentBet.calculateTotal();
        currentBetLabel.setText("Current Bet: $" + total);

        betPlaced = true;
    }

    private void handleUpdateBalance() {
        Double newBalance = this.model.getPlayerBalance();
        Platform.runLater(() -> {
            if (balanceLabel != null) {
                balanceLabel.setText("$" + String.format("%.2f", newBalance));
            }
        });
    }

    private void updateCardDisplay() {
        Platform.runLater(() -> {
            // Clear both cards
            this.playerArea.getChildren().clear();
            this.dealerArea.getChildren().clear();

            // Update player cards
            if (model.getPlayerCards() != null) {
                for (Card card : model.getPlayerCards().getCurrentHand()) {
                    this.playerArea.getChildren().add(this.view.createCardView(card));
                }
            }

            // Update dealer cards
            if (model.getDealerCards() != null) {
                int index = 0;
                for (Card card : model.getDealerCards().getCurrentHand()) {
                    index += 1;
                    if (model.getDealerCards().getCurrentHand().size() == 2 && index == 2 && model.getGameState() == GameState.PLAYER_TURN) {
                        // Dealer second card is hidden
                        this.dealerArea.getChildren().add(this.view.createBackCardView());
                    }else {
                        this.dealerArea.getChildren().add(this.view.createCardView(card));
                    }
                }
            }
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

    }

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

    private void handleGameEnd(GameResult result) {
        balanceAfter = this.model.getPlayerBalance();
        showAlert("Game over!", getResultMessage(result));
        // Update player balance
        handleUpdateBalance();
        // Clear the cards
        clearCards();
        // Reset bet
        betPlaced = false;
        // Reset game
        handleNewGame();
    }

    private void clearCards() {
        Platform.runLater(() -> {
            this.dealerArea.getChildren().clear();
            this.playerArea.getChildren().clear();
        });
    }
}
