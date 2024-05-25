package com.hbd.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static MainPage mainPage;
    public static ShuffleController shuffleController = new ShuffleController();
    public static PopupInfoController popupInfoController = new PopupInfoController();

    @Override
    public void start(Stage stage) throws IOException {
        mainPage = new MainPage();
        mainPage.start(stage);
    }

    public static AnchorPane getPane(){
        return mainPage.getCurrentPane();
    }

    public static ShuffleController getShuffleController(){
        return shuffleController;
    }

    public static MainController getMainController(){
        return mainPage.getController();
    }

    public static MainPage getMainPage(){
        return mainPage;
    }

    public static void main(String args[]){
        launch();
    }
}