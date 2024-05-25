package com.hbd.GUI;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.stage.Stage;

public class LoadStateController {

    @FXML
    private TextField format;

    @FXML
    private TextField folder;

    @FXML
    public void HandlePopUp(ActionEvent event) {
        System.out.println("HandlePopUp");
    }

    @FXML
    public void SubmitLoadState(ActionEvent event) {
        System.out.println("SubmitLoadState");
        System.out.println("Formatnya " + format.getText());
        System.out.println("Foldernya " + folder.getText());

        // Close the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

// public class PopupController {

// @FXML
// private Stage stage;

// }
