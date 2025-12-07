/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Finnian Franks
 * Date: 11/11/25
 * Time: 10:58 PM
 *
 * Project: csci205_final_project
 * Package: org.team01
 * Class: gameFX
 *
 * Description:
 *
 * ****************************************
 */

package org.team01.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.team01.Card;
import org.team01.Game;
import org.team01.controller.GameController;

/**
 * Class containing the entire UI for the BlackJack game
 */
public class GameFX extends Application {
    private HBox dealerArea;
    private HBox playerArea;
    private Game model;
    private GameController controller;

    /**
     * Initializes the entire UI as well as instantiates the GameController (and Game) and passes in all necessary buttons, labels, and views
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // Start the controller

        controller = new GameController(model, this);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 550);
        String css = getClass().getResource("/css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(650);
        primaryStage.show();

        // Create the background of the game
        Rectangle background = new Rectangle();
        background.setFill(Color.web("#151B3C"));
        background.widthProperty().bind(root.widthProperty());
        background.heightProperty().bind(root.heightProperty());

        // Create VBox to hold all boxes
        VBox mainContainer = new VBox();
        mainContainer.setMaxWidth(Double.MAX_VALUE);
        mainContainer.setMaxHeight(Double.MAX_VALUE);

        // Create HBox for header
        HBox header = new HBox();
        header.setMaxWidth(Double.MAX_VALUE);
        header.setMaxHeight(50);
        header.getStyleClass().add("header-container");
        // Add balance to header
        Label balanceLabel = new Label("$" + 1000.00);
        balanceLabel.setTextFill(Color.web("white"));
        balanceLabel.getStyleClass().add("balance-label");
        // Add spacer to force vertically aligned center
        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        header.getChildren().addAll(balanceLabel, headerSpacer);

         // Create hbox to hold all vboxes
        HBox gameContainer = new HBox();
        // Fill the entire stack pane (root)
        gameContainer.setMaxWidth(Double.MAX_VALUE);
        gameContainer.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(gameContainer, Priority.ALWAYS);

        // Create vboxes
        VBox leftContainer = new VBox();
        VBox centerContainer = new VBox();
        VBox rightContainer = new VBox();

        // Scale with hbox
        leftContainer.setMaxHeight(Double.MAX_VALUE);
        centerContainer.setMaxHeight(Double.MAX_VALUE);
        rightContainer.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(leftContainer, Priority.ALWAYS);
        HBox.setHgrow(centerContainer, Priority.ALWAYS);
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        HBox chipsContainer = new HBox();
        chipsContainer.getStyleClass().add("chips-container");

        // Create image view for each chip
        Image whiteChipImage = new Image(getClass().getResourceAsStream("/assets/chip-white.png"));
        if (whiteChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            whiteChipImage.getException().printStackTrace();
        }
        ImageView whiteChip = new ImageView(whiteChipImage);
        whiteChip.setFitWidth(35);
        whiteChip.setFitHeight(35);
        whiteChip.setPreserveRatio(true);

        Image redChipImage = new Image(getClass().getResourceAsStream("/assets/chip-red.png"));
        if (redChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            redChipImage.getException().printStackTrace();
        }
        ImageView redChip = new ImageView(redChipImage);
        redChip.setFitWidth(35);
        redChip.setFitHeight(35);
        redChip.setPreserveRatio(true);

        Image greenChipImage = new Image(getClass().getResourceAsStream("/assets/chip-green.png"));
        if (greenChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            greenChipImage.getException().printStackTrace();
        }
        ImageView greenChip = new ImageView(greenChipImage);
        greenChip.setFitWidth(35);
        greenChip.setFitHeight(35);
        greenChip.setPreserveRatio(true);

        Image blackChipImage = new Image(getClass().getResourceAsStream("/assets/chip-gold.png"));
        if (blackChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            blackChipImage.getException().printStackTrace();
        }
        ImageView blackChip = new ImageView(blackChipImage);
        blackChip.setFitWidth(35);
        blackChip.setFitHeight(35);
        blackChip.setPreserveRatio(true);

        Image blueChipImage = new Image(getClass().getResourceAsStream("/assets/chip-blue.png"));
        if (blueChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            blueChipImage.getException().printStackTrace();
        }
        ImageView blueChip = new ImageView(blueChipImage);
        blueChip.setFitWidth(35);
        blueChip.setFitHeight(35);
        blueChip.setPreserveRatio(true);

        // Set button graphic to each chip image
        Button white = new Button();
        white.getStyleClass().add("chips-image");
        white.setGraphic(whiteChip);
        Button red = new Button();
        red.getStyleClass().add("chips-image");
        red.setGraphic(redChip);
        Button blue = new Button();
        blue.getStyleClass().add("chips-image");
        blue.setGraphic(blueChip);
        Button green = new Button();
        green.getStyleClass().add("chips-image");
        green.setGraphic(greenChip);
        Button black = new Button();
        black.getStyleClass().add("chips-image");
        black.setGraphic(blackChip);

        // Create spacer to horizontally align everything
        Region chipsSpacer = new Region();
        HBox.setHgrow(chipsSpacer, Priority.ALWAYS);

        // Create the current bet label
        Label currentBetLabel = new Label("Current Bet: $");
        currentBetLabel.setTextFill(Color.web("white"));
        currentBetLabel.getStyleClass().add("balance-label");
        // Add the buttons and label to chip container
        chipsContainer.getChildren().addAll(
                white,
                red,
                blue,
                green,
                black,
                chipsSpacer,
                currentBetLabel
        );

        // Create container for place bet/clear bet buttons
        HBox betButtonsContainer = new HBox();
        betButtonsContainer.getStyleClass().add("betbtn-container");

        Button clearBetButton = new Button("Clear Bet");
        Button newGameButton = new Button("Place Bet");
        clearBetButton.getStyleClass().add("bet-btn");
        newGameButton.getStyleClass().add("bet-btn");
        betButtonsContainer.getChildren().addAll(clearBetButton, newGameButton);

        leftContainer.getChildren().addAll(
                spacer,
                betButtonsContainer,
                chipsContainer
        );
        leftContainer.setPadding(new Insets(10));
        leftContainer.setSpacing(5);

        // Create and add elements to the centerContainer
        centerContainer.setPadding(new Insets(10));

        dealerArea = new HBox();
        dealerArea.getStyleClass().add("card-area");

        Region centerSpacer = new Region();
        VBox.setVgrow(centerSpacer, Priority.ALWAYS);

        playerArea = new HBox();
        playerArea.getStyleClass().add("card-area");


        HBox buttonArea = new HBox();
        buttonArea.getStyleClass().add("button-container");

        Button doubleButton  = new Button("Double Down ");
        doubleButton.getStyleClass().add("choice-button");

        Button hitButton = new Button("Hit");
        hitButton.getStyleClass().add("choice-button");

        Button standButton = new Button("Stand");
        standButton.getStyleClass().add("choice-button");

        buttonArea.getChildren().addAll(
                hitButton,
                standButton
        );

        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setSpacing(10);
        centerContainer.getChildren().addAll(
            dealerArea,
            centerSpacer,
            playerArea,
            buttonArea
        );


        // Add the background and main container to root
        gameContainer.getChildren().addAll(leftContainer, centerContainer, rightContainer);
        mainContainer.getChildren().addAll(header, gameContainer);
        root.getChildren().addAll(background, mainContainer);


        controller.initialize(dealerArea, playerArea, balanceLabel, hitButton, standButton, white, red, blue, green, black, newGameButton, currentBetLabel, clearBetButton);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Function to create the front card view for a normal card
     *
     * @param card the Card object that we want to create an ImageView for
     * @return the CardView of the desired card
     */
    public ImageView createCardView(Card card) {
        String fullImagePath = "/assets/Cards (large)/" + card.getImagePath() + ".png";
        Image cardImage = new Image(getClass().getResourceAsStream(fullImagePath));

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(140);
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        return cardView;
    }

    /**
     * Function to create the back card view for a flipped card
     *
     * @return the CardView of the flipped card
     */
    public ImageView createBackCardView() {
        String fullImagePath = "/assets/Cards (large)/card_back.png";
        Image cardImage = new Image(getClass().getResourceAsStream(fullImagePath));

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(140);
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        cardView.setUserData("HIDDEN_DEALER_CARD");

        return cardView;
    }
}