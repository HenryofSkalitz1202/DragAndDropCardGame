package com.hbd;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class FxmlLoader {
    private Pane view;
    private AnchorPane scene;

    public Pane getPage(String fileName){

        try{
            URL fileURL = App.class.getResource("/com/hbd/" + fileName + ".fxml");
            if(fileURL == null){
                throw new java.io.FileNotFoundException("FMXL file can't be found");

            }

            view = FXMLLoader.load(fileURL);


        } catch (Exception e){
            System.out.println("No page");
        }
    
        return view;
    }



}
