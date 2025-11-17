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

import java.io.InputStream;


public class GameFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the background of the game
        Rectangle background = new Rectangle();
        background.setWidth(586);
        background.setHeight(282);
        background.setFill(Color.web("#349D38"));

        StackPane root = new StackPane();
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}