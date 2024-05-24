package com.hbd;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.stage.Stage;



public class Popup {
    private static Stage popupwindow;
    
    public void display()
    {

        // ... Add UI elements to the VBox

        
        popupwindow = new Stage();
        
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("loadstate");
        
        Scene scene = new Scene(view, 400, 400);
        

        popupwindow.setScene(scene); 
        
 
        popupwindow.showAndWait();
        
        
    }
    
    @FXML
    public void HandlePopUp(ActionEvent event){
        System.out.println("HandlePopUp");
        popupwindow.close();
    }    
}
    


// public class PopupController {
   
//     @FXML
//     private Stage stage;

    

// }
