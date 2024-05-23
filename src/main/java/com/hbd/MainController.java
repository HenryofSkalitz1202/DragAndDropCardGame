package com.hbd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private BorderPane home;

    @FXML
    private void handleButton(ActionEvent event){
        System.out.println("WAKWAW");
        FxmlLoader object = new FxmlLoader();
        
        Pane view = object.getPage("testing");
        home.setCenter(view);
    }
    
    
}
