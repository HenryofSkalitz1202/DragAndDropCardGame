package com.hbd.GUI;

import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JavaFX App
 */
public class MainPage extends Application {

    private static Scene scene;
    private static List<KartuGUI> pageKartu = new ArrayList<>();
    private static AnchorPane currentPane;

    private double initialX, initialY;
    private boolean fromDeck = false, fromLadang = false;

    private static final Double BASE_LADANG_X = 255.0;
    private static final Double WIDTH_LADANG = 434.0;
    private static final Double BASE_LADANG_Y = 131.0;
    private static final Double HEIGHT_LADANG = 353.74;
    private static final Double BASE_DECK_AKTIF_X = 135.0;
    private static final Double WIDTH_DECK_AKTIF = 580.0;
    private static final Double BASE_DECK_AKTIF_Y = 555.0;
    private static final Double HEIGHT_DECK_AKTIF = 101.6;

    @Override
    public void start(Stage stage) throws IOException {
        MainController.initializeGame();
        pageKartu.add(new KartuGUI(FactoryKartu.getKartu("Ayam"), new Rectangle(BASE_DECK_AKTIF_X, BASE_DECK_AKTIF_Y, 80, 80)));
        pageKartu.add(new KartuGUI(FactoryKartu.getKartu("Accelerate"), new Rectangle(BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF/6, BASE_DECK_AKTIF_Y, 80, 80)));
        pageKartu.add(new KartuGUI(FactoryKartu.getKartu("Hiu Darat"), new Rectangle(BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF/3, BASE_DECK_AKTIF_Y, 80, 80)));
        pageKartu.add(new KartuGUI(FactoryKartu.getKartu("Biji Labu"), new Rectangle(BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF/2, BASE_DECK_AKTIF_Y, 80, 80)));
        pageKartu.add(new KartuGUI(FactoryKartu.getKartu("Biji Labu"), new Rectangle(BASE_LADANG_X, BASE_LADANG_Y, 80, 80)));
        currentPane = (AnchorPane) loadFXML("main");
        updateCard();
        scene = new Scene(currentPane, 890, 667);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void pressed(MouseEvent event, KartuGUI k) {
        k.setColor(Color.GREEN);
        initialX = event.getX();
        initialY = event.getY();
        if (initialX >= BASE_LADANG_X && initialX <= BASE_LADANG_X + WIDTH_LADANG && initialY >= BASE_LADANG_Y && initialY <= BASE_LADANG_Y + HEIGHT_LADANG){
            fromLadang = true;
            fromDeck = false;
        } else if (initialX >= BASE_DECK_AKTIF_X && initialX <= BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF && initialY >= BASE_DECK_AKTIF_Y && initialY <= BASE_DECK_AKTIF_Y + HEIGHT_DECK_AKTIF) {
            fromDeck = true;
            fromLadang = false;
        }
    }

    public void dragged(MouseEvent event, KartuGUI k) {
        double translateX = event.getSceneX() - initialX;
        double translateY = event.getSceneY() - initialY;
        k.draw(translateX, translateY);
    }

    public void released(MouseEvent event, KartuGUI k) {
        k.setColor(Color.TEAL);
        k.setImg();
        k.setX(k.getCard().getLayoutX());
        k.setY(k.getCard().getLayoutY());

        Double card_middle_x = k.getX() + k.getCard().getWidth()/2.0;
        Double card_middle_y = k.getY() + k.getCard().getHeight()/2.0;

        if (card_middle_x >= BASE_LADANG_X && card_middle_x <= BASE_LADANG_X + WIDTH_LADANG && card_middle_y <= BASE_LADANG_Y + HEIGHT_LADANG && card_middle_y >= BASE_LADANG_Y){
            /**
             * Card dilepaskan di petak ladang
             */
            int id_x = (int)((BASE_LADANG_X + WIDTH_LADANG - initialX)/86.8);
            int id_y = (int)((BASE_LADANG_Y + HEIGHT_LADANG - initialY)/88.435);

//            try {
////                MainController.LetGoHandler((Makhluk) k.getKartu(), id_x, id_y);
//            } catch (DiluarPetakException e) {throw new RuntimeException("WAA HOW");}
//            try {
//                currentPane = (AnchorPane) loadFXML("main");
//            } catch (IOException e) {;}
//            updateCard();
        }
    }

    public void updateCard(){
        for (KartuGUI k: pageKartu) {
            currentPane.getChildren().add(k.getCard());
            k.draw(k.getCard().getLayoutX(), k.getCard().getLayoutY());
            k.getCard().setOnMousePressed(event -> pressed(event, k));
            k.getCard().setOnMouseDragged(event -> dragged(event, k));
            k.getCard().setOnMouseReleased(event -> released(event, k));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}