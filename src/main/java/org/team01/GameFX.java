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

package org.team01;

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


public class GameFX extends Application {
    private HBox dealerArea;
    private HBox playerArea;
    private Game model;
    private GameController controller;


    @Override
    public void start(Stage primaryStage) {
        // Start the controller

        controller = new GameController(model, this);

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 550);
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Green Rectangle Background");
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
        // Add spacer to force vertically aligned center
        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        header.getChildren().addAll(balanceLabel, headerSpacer);

         // Create hbox to hold all vboxes
        HBox gameContainer = new HBox();
        // Fill the entire stack pane (root)
        gameContainer.setMaxWidth(Double.MAX_VALUE);
        gameContainer.setMaxHeight(Double.MAX_VALUE);

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
        Image whiteChipImage = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/chip-white.png");
        if (whiteChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            whiteChipImage.getException().printStackTrace();
        }
        ImageView whiteChip = new ImageView(whiteChipImage);
        whiteChip.setFitWidth(35);
        whiteChip.setFitHeight(35);
        whiteChip.setPreserveRatio(true);

        Image redChipImage = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/chip-red.png");
        if (redChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            redChipImage.getException().printStackTrace();
        }
        ImageView redChip = new ImageView(redChipImage);
        redChip.setFitWidth(35);
        redChip.setFitHeight(35);
        redChip.setPreserveRatio(true);

        Image greenChipImage = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/chip-green.png");
        if (greenChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            greenChipImage.getException().printStackTrace();
        }
        ImageView greenChip = new ImageView(greenChipImage);
        greenChip.setFitWidth(35);
        greenChip.setFitHeight(35);
        greenChip.setPreserveRatio(true);

        Image blackChipImage = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/chip-gold.png");
        if (blackChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            blackChipImage.getException().printStackTrace();
        }
        ImageView blackChip = new ImageView(blackChipImage);
        blackChip.setFitWidth(35);
        blackChip.setFitHeight(35);
        blackChip.setPreserveRatio(true);

        Image blueChipImage = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/chip-blue.png");
        if (blueChipImage.isError()) {
            System.out.println("Error: Failed to load image");
            blueChipImage.getException().printStackTrace();
        }
        ImageView blueChip = new ImageView(blueChipImage);
        blueChip.setFitWidth(35);
        blueChip.setFitHeight(35);
        blueChip.setPreserveRatio(true);

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

        // Create the current bet label
        Label currentBetLabel = new Label("Current Bet: $");
        currentBetLabel.setTextFill(Color.web("white"));
        // Add the buttons and label to chip container
        chipsContainer.getChildren().addAll(
                white,
                red,
                blue,
                green,
                black,
                currentBetLabel
        );

        leftContainer.getChildren().addAll(
                spacer,
                chipsContainer
        );
        leftContainer.setPadding(new Insets(10));

        // Create and add elements to the centerContainer
        centerContainer.setPadding(new Insets(10));

        dealerArea = new HBox();
        dealerArea.getStyleClass().add("card-area");

        Region centerSpacer = new Region();
        VBox.setVgrow(centerSpacer, Priority.ALWAYS);

        playerArea = new HBox();
        playerArea.getStyleClass().add("card-area");

        Button newGameButton = new Button("New Game");

        HBox buttonArea = new HBox();
        buttonArea.getStyleClass().add("button-container");

        Button hitButton = new Button("Hit");
        hitButton.getStyleClass().add("choice-button");

        Button standButton = new Button("Stand");
        standButton.getStyleClass().add("choice-button");

        buttonArea.getChildren().addAll(
                hitButton,
                standButton
        );

        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(
            dealerArea,
            newGameButton,
            centerSpacer,
            playerArea,
            buttonArea
        );


        // Add the background and main container to root
        gameContainer.getChildren().addAll(leftContainer, centerContainer, rightContainer);
        mainContainer.getChildren().addAll(header, gameContainer);
        root.getChildren().addAll(background, mainContainer);


        controller.initialize(dealerArea, playerArea, balanceLabel, hitButton, standButton, white, red, blue, green, black, newGameButton, currentBetLabel);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ImageView createCardView(Card card) {
        String fullImagePath = "file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/Cards (large)/" + card.getImagePath() + ".png";
        Image cardImage = new Image(fullImagePath);

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(140);
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        return cardView;
    }

    public ImageView createBackCardView() {
        String fullImagePath = "file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/Cards (large)/card_back.png";
        Image cardImage = new Image(fullImagePath);

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(140);
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        return cardView;
    }
}