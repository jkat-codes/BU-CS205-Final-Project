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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.concurrent.RecursiveAction;


public class GameFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

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

        // Add the balance and chips to leftContainer
        Image balanceButton = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01" +
                "/assets/balance-button.png");
        ImageView viewBalance = new ImageView(balanceButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Image chips = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01" +
                "/assets/bitcoinChips.png");
        ImageView viewChips = new ImageView(chips);

        leftContainer.getChildren().addAll(
                viewBalance,
                spacer,
                viewChips
        );

        // Create and add elements to the centerContainer
        centerContainer.setPadding(new Insets(10));

        HBox dealerArea = new HBox();
        dealerArea.setMinWidth(140);
        dealerArea.setMinHeight(200);
        dealerArea.add

        Region centerSpacer = new Region();
        VBox.setVgrow(centerSpacer, Priority.ALWAYS);

        Rectangle playerArea = new Rectangle();
        playerArea.setFill(Color.web("Black"));
        playerArea.setWidth(140);
        playerArea.setHeight(200);

        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(
            dealerArea,
            centerSpacer,
            playerArea
        );


        // Add the choice buttons to rightContainer


        // Add the background and main container to root
        mainContainer.getChildren().addAll(leftContainer, centerContainer, rightContainer);
        root.getChildren().addAll(background, mainContainer);

        Scene scene = new Scene(root, 750, 450);
        primaryStage.setTitle("Green Rectangle Background");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(750);
        primaryStage.show();

        // Add test cards
//        ImageView card2 = createCardView(new Card(Suit.DIAMONDS, 11, FaceValue.ACE));
//        root.getChildren().add(card2);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ImageView createCardView(Card card) {
        String fullImagePath = "file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/Cards (large)/" + card.getImagePath() + ".png";
        Image cardImage = new Image(fullImagePath);

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(140);
        cardView.setFitWidth(100);
        cardView.setPreserveRatio(true);

        return cardView;
    }
}