package com.hbd;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.stage.Stage;



public class Popup {
    private static Stage popupwindow;

    @FXML
    private TextField format;
    
    @FXML
    private TextField folder;

    public void display()
    {

        // ... Add UI elements to the VBox

        
        popupwindow = new Stage();
        
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("loadstate");
        
        Scene scene = new Scene(view, 600, 400);
        

        popupwindow.setScene(scene); 
        
 
        popupwindow.showAndWait();
        
        
    }
    
    @FXML
    public void HandlePopUp(ActionEvent event){
        System.out.println("HandlePopUp");
        popupwindow.close();
    }    

    @FXML
    public void SubmitLoadState(ActionEvent event){
        System.out.println("SubmitLoadState");
        System.out.println("Formatnya " + format.getText());
        System.out.println("Foldernya " + folder.getText());
        popupwindow.close();
    }
}
    


// public class PopupController {
   
//     @FXML
//     private Stage stage;

    

// }
