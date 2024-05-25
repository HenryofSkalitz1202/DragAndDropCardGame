package com.hbd.GUI;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SaveStateController {
    @FXML
    private TextField format;

    @FXML
    private TextField folder;

    @FXML
    public void HandleKembali(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void HandleSubmit(ActionEvent event) {
        System.out.println("SubmitLoadState");
        System.out.println("Formatnya " + format.getText());
        System.out.println("Foldernya " + folder.getText());

        // Close the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
