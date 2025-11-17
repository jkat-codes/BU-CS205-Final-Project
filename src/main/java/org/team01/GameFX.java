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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        // Create the background of the game
        Rectangle background = new Rectangle();
        background.setFill(Color.web("#349D38"));
        background.widthProperty().bind(root.widthProperty());
        background.heightProperty().bind(root.heightProperty());

        root.getChildren().add(background);

        Scene scene = new Scene(root, 586, 282);
        primaryStage.setTitle("Green Rectangle Background");
        primaryStage.setScene(scene);
        primaryStage.show();

        //going to create the H box that will hold the Vboxes for the different features
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        HBox hbox  = new HBox(10, vBox1, vBox2, vBox3);

        root.getChildren().add(hbox);

        //add the balance button
        Image balanceButton = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01" +
                "/assets/balance-button.png");
        ImageView viewBalance = new ImageView(balanceButton);
        vBox1.getChildren().add(viewBalance);

        //add the chips
        Image chips = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/org/team01" +
                "/assets/bitcoinChips.png");
        ImageView viewChips = new ImageView(chips);
        vBox1.getChildren().add(viewChips);

        System.out.println(viewBalance.getImage().getWidth());
        System.out.println(viewBalance.getImage().getHeight());

        ImageView card1 = createCardView(new Card(Suit.SPADES, 10, FaceValue.KING));
        ImageView card2 = createCardView(new Card(Suit.DIAMONDS, 11, FaceValue.ACE));

        root.getChildren().addAll(card1, card2);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ImageView createCardView(Card card) {
        String fullImagePath = "file:" + System.getProperty("user.dir") + "/src/main/java/org/team01/assets/Cards (large)/" + card.getImagePath() + ".png";
        Image cardImage = new Image(fullImagePath);

        ImageView cardView = new ImageView(cardImage);
        cardView.setFitHeight(100);
        cardView.setFitWidth(60);
        cardView.setPreserveRatio(true);

        return cardView;
    }
}