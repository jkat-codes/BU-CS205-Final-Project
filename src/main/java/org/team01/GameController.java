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

    public GameController(Game model, GameFX view) {
        this.model = new Game();
        this.view = view;
    }

    public void initialize(HBox dealerArea, HBox playerArea, Label balanceLabel, Button hitButton, Button standButton) {
        this.dealerArea = dealerArea;
        this.playerArea = playerArea;
        this.balanceLabel = balanceLabel;
        this.hitButton = hitButton;
        this.standButton = standButton;

        // Setup event handlers
        setupEventHandlers();
//        updateButtonStates();

        // Start a new game
        handleNewGame();
    }

    private void handleNewGame() {
        boolean success = this.model.initializeNewGame();
        if (success) {
            clearCards();
            // Add a call to the betting logic here
            System.out.println("Balance before bet: " + this.model.getPlayerBalance());
            Bet bet = new Bet();
            bet.addBet(Chips.WHITE, 1);
            System.out.println("Bet: " + bet);
            this.model.placeBet(bet);

            System.out.println("Balance after bet: " + this.model.getPlayerBalance());

            handleDeal();
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

    // Handler for each of the different chips
    private void handlePlaceBet() {
    }

    private void handleUpdateBalance() {
        System.out.println(this.model.getPlayerBalance());
        Double newBalance = this.model.getPlayerBalance();
        Platform.runLater(() -> {
            if (balanceLabel != null) {
                balanceLabel.setText("Current Balance: $" + String.format("%.2f", newBalance));
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
                // TODO: Only reveal the first card, show the back of the second
                for (Card card : model.getDealerCards().getCurrentHand()) {
                    this.dealerArea.getChildren().add(this.view.createCardView(card));
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

    private void handleGameEnd(GameResult result) {
        showAlert("Game over!", result.toString());
        // Update player balance
        handleUpdateBalance();
        // Clear the cards
        clearCards();
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
