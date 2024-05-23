package com.hbd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    private static CountdownWindow window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Button activateClockButton = new Button("Activate clock");

        activateClockButton.setOnAction(e -> activateClock());

        root.getChildren().addAll(activateClockButton);
        Scene scene = new Scene(root, 300, 100);

        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the countdown window (but keep it hidden initially)
        window = new CountdownWindow();
    }

    private void activateClock() {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Countdown seconds: ");
        String input = inputObj.nextLine();
        int countdownSeconds = Integer.parseInt(input);

        window.showWindow();  // Show the countdown window
        Thread runner = new Thread(new TestClock(countdownSeconds, window));
        runner.start();
    }
}