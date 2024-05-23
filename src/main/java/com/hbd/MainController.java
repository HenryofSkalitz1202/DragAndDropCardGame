package com.hbd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private BorderPane home;

    @FXML
    private void ButtonLadangku(ActionEvent event){
        System.out.println("Test1");

        //Kode di bawah ini untuk mengganti view dari bagian tengah halaman
        //Ganti "testing" dengan nama file fxml asli, misal "ladangku"
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("testing");
        home.setCenter(view);
    }
    
    @FXML
    private void ButtonLadangLawan(ActionEvent event){
        System.out.println("Test2");

        //Kode di bawah ini untuk mengganti view dari bagian tengah halaman
        //Ganti "testing" dengan nama file fxml asli, misal "ladangku"
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("draggable");
        home.setCenter(view);

    }

    @FXML
    private void ButtonToko(ActionEvent event){
        System.out.println("Test3");

        //Kode di bawah ini untuk mengganti view dari bagian tengah halaman
        //Ganti "testing" dengan nama file fxml asli, misal "ladangku"
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("testing");
        home.setCenter(view);

    }

    @FXML
    private void ButtonSaveState(ActionEvent event){
        System.out.println("Test4");

    }

    @FXML
    private void ButtonLoadPlugin(ActionEvent event){
        System.out.println("Test5");

    }

    @FXML
    private void ButtonLoadState(ActionEvent event){
        System.out.println("Test6");

    }

    @FXML
    private void ButtonNext(ActionEvent event){
        System.out.println("Test7");

    }
}
