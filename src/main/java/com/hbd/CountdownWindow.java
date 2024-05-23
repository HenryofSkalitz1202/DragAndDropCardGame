package com.hbd;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountdownWindow {
    private Stage stage;
    private Label timeLabel;
    private Label messageLabel;

    public CountdownWindow() {
        stage = new Stage();
        timeLabel = new Label("Starting...");
        messageLabel = new Label();
        VBox root = new VBox(timeLabel, messageLabel);

        Scene scene = new Scene(root, 200, 100);
        stage.setTitle("Countdown Clock");
        stage.setScene(scene);
        // Initially, the window is hidden
    }

    public void showWindow() {
        Platform.runLater(() -> stage.show());
    }

    public void updateTime(String time) {
        Platform.runLater(() -> timeLabel.setText(time));
    }

    public void updateMessage(String message) {
        Platform.runLater(() -> messageLabel.setText("Message: " + message));
    }
}