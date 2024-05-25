package com.hbd.GUI;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ShufflePage {

    private AnchorPane pane;
    private final ShuffleController myController;

    public ShufflePage(ShuffleController controller){
        myController = controller;
    }

    public ShufflePage(){
        myController = new ShuffleController();
    }

    public void switchTo(){

        if (!App.getPane().getChildren().isEmpty()) {
            App.getPane().getChildren().subList(0, App.getPane().getChildren().size()).clear();
        }

        pane = App.getPane();

        Image bgimg = new Image(getClass().getResource("img/Frame Background.png").toExternalForm());
        ImageView bg = new ImageView(bgimg);
        bg.setFitHeight(667);

        Button reshuffle = new Button("Reshuffle");
        Button accept = new Button("Accept");

        reshuffle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myController.reshuffleHandler();
            }
        });

        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myController.acceptHandler();
            }
        });

        AnchorPane.setLeftAnchor(reshuffle, 257.0);
        AnchorPane.setTopAnchor(reshuffle, 593.0);
        AnchorPane.setLeftAnchor(accept, 528.0);
        AnchorPane.setTopAnchor(accept, 593.0);

        pane.getChildren().add(bg);
        pane.getChildren().add(reshuffle);
        pane.getChildren().add(accept);
    }

    public void showCards(List<Kartu> kartukartu){
        int numKartu = kartukartu.size();
        showFirstCard(kartukartu.get(0), numKartu);
        if (numKartu > 1){
            showSecondCard(kartukartu.get(1), numKartu);
        }
        if (numKartu > 2){
            showThirdCard(kartukartu.get(2), numKartu);
        }
        if (numKartu > 3){
            showFourthCard(kartukartu.get(3));
        }
    }

    private void showFirstCard(Kartu kartu, int numKartu){
        StackPane card = new DealedCardGUI(kartu);
        if (numKartu == 4 || numKartu == 3){
            AnchorPane.setTopAnchor(card, 122.0);
            AnchorPane.setLeftAnchor(card, 238.0);
        } else if (numKartu == 2) {
            AnchorPane.setTopAnchor(card, 244.0);
            AnchorPane.setLeftAnchor(card, 238.0);
        } else if (numKartu == 1){
            AnchorPane.setTopAnchor(card, 244.0);
            AnchorPane.setLeftAnchor(card, 385.0);
        }
        pane.getChildren().add(card);
    }

    private void showSecondCard(Kartu kartu, int numKartu){
        StackPane card = new DealedCardGUI(kartu);
        if (numKartu == 4 || numKartu == 3){
            AnchorPane.setTopAnchor(card, 122.0);
            AnchorPane.setLeftAnchor(card, 509.0);
        } else if (numKartu == 2) {
            AnchorPane.setTopAnchor(card, 244.0);
            AnchorPane.setLeftAnchor(card, 509.0);
        }
        pane.getChildren().add(card);
    }

    private void showThirdCard(Kartu kartu, int numKartu){
        StackPane card = new DealedCardGUI(kartu);
        if (numKartu == 4){
            AnchorPane.setTopAnchor(card, 364.0);
            AnchorPane.setLeftAnchor(card, 238.0);
        } else if (numKartu == 3) {
            AnchorPane.setTopAnchor(card, 364.0);
            AnchorPane.setLeftAnchor(card, 385.0);
        }
        pane.getChildren().add(card);
    }

    private void showFourthCard(Kartu kartu){
        StackPane card = new DealedCardGUI(kartu);
        AnchorPane.setTopAnchor(card, 364.0);
        AnchorPane.setLeftAnchor(card, 509.0);
        pane.getChildren().add(card);
    }
}