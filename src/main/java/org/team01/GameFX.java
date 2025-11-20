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

import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.RecursiveAction;


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
        Scene scene = new Scene(root, 1000, 1000);
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Green Rectangle Background");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(750);

        // Create the background of the game
        Rectangle background = new Rectangle();
        background.setFill(Color.web("#349D38"));
        background.widthProperty().bind(root.widthProperty());
        background.heightProperty().bind(root.heightProperty());

         // Create hbox to hold all vboxes
        HBox mainContainer = new HBox();
        // Fill the entire stack pane (root)
        mainContainer.setMaxWidth(Double.MAX_VALUE);
        mainContainer.setMaxHeight(Double.MAX_VALUE);

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

        // Create and add the balance view Add the balance and chips to leftContainer
        VBox balanceView = new VBox();
        balanceView.getStyleClass().add("balance-view");
        // TODO: replace 1000 with the actual balance of the player playing
        Label balanceLabel = new Label("Current Balance: " + 1000);
        balanceView.getChildren().add(balanceLabel);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        HBox chipsContainer = new HBox();
        chipsContainer.getStyleClass().add("chips-container");
//        Image chips = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01" +
//                "/assets/bitcoinChips.png");
//        ImageView viewChips = new ImageView(chips);

        leftContainer.getChildren().addAll(
                balanceView,
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

        ImageView card2 = createCardView(new Card(Suit.DIAMONDS, 11, FaceValue.ACE));
        playerArea.getChildren().add(card2);

        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(
            dealerArea,
            centerSpacer,
            playerArea
        );


        // Add the choice buttons to rightContainer
        VBox buttonContainer = new VBox();
        buttonContainer.getStyleClass().add("button-container");
        // Always scale with height
        VBox.setVgrow(buttonContainer, Priority.ALWAYS);

        Button hitButton = new Button("Hit");
        hitButton.getStyleClass().add("button");

        Button standButton = new Button("Stand");
        standButton.getStyleClass().add("button");

        buttonContainer.getChildren().addAll(
                hitButton,
                standButton
        );

        rightContainer.getChildren().addAll(
                buttonContainer
        );

        // Add the background and main container to root
        mainContainer.getChildren().addAll(leftContainer, centerContainer, rightContainer);
        root.getChildren().addAll(background, mainContainer);

        controller.initialize(dealerArea, playerArea, balanceLabel, hitButton, standButton);

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

}