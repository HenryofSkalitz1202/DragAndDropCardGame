package com.hbd.GUI;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class MainPage extends Application {

    private Scene scene;
    private List<KartuGUI> pageKartu = new ArrayList<>();
    private AnchorPane currentPane;
    boolean hasStarted = false;

    private MainController controller;

    private double initialX, initialY;
    private boolean fromDeck = false, fromLadang = false;

    private List<Node> ProperSetting;

    private static final Double BASE_LADANG_X = 255.0;
    private static final Double WIDTH_LADANG = 434.0;
    private static final Double BASE_LADANG_Y = 131.0;
    private static final Double HEIGHT_LADANG = 353.74;
    private static final Double BASE_DECK_AKTIF_X = 141.6;
    private static final Double WIDTH_DECK_AKTIF = 560.0;
    private static final Double BASE_DECK_AKTIF_Y = 555.0;
    private static final Double HEIGHT_DECK_AKTIF = 101.6;
    private static final Double LADANG_TILE_WIDTH = WIDTH_LADANG/5;
    private static final Double LADANG_TILE_HEIGHT = HEIGHT_LADANG/4;
    private static final Double DECK_AKTIF_TILE_WIDTH = WIDTH_DECK_AKTIF/6;

    @Override
    public void start(Stage stage) throws IOException {

        hasStarted = true;
        controller = new MainController();
        controller.initializeGame();

        initializeEverything();
        loadCard();
        loadGameState();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchTo(){

        if (!App.getPane().getChildren().isEmpty()) {
            App.getPane().getChildren().subList(0, App.getPane().getChildren().size()).clear();
        }

        for (Node node : ProperSetting){
            currentPane.getChildren().add(node);
        }

        loadCard();
        loadGameState();
    }

    private void initializeEverything() throws IOException {
        currentPane = (AnchorPane) loadFXML("main");
        ProperSetting = new ArrayList<>(currentPane.getChildren());
        scene = new Scene(currentPane, 890, 667);
    }

    public void loadCard(){
        getCard();
        updateCard();
    }

    public void loadGameState(){
        int turnNumber = controller.getCurrentTurn();
        int player1duit = controller.getCurrentPlayer1Duit();
        int player2duit = controller.getCurrentPlayer2Duit();

        String currentlyPlaying = "Pemain 1";
        if (turnNumber % 2 == 0){
            currentlyPlaying = "Pemain 2";
        }

        Text currentPlayerText = new Text(currentlyPlaying + " Turn");
        AnchorPane.setTopAnchor(currentPlayerText, 215.93);
        AnchorPane.setLeftAnchor(currentPlayerText, 24.34);

        Text currentTurnText = new Text(String.valueOf(turnNumber));
        AnchorPane.setLeftAnchor(currentTurnText, 85.46);
        AnchorPane.setTopAnchor(currentTurnText, 255.12);

        Text currentPlayer1DuitText = new Text(String.valueOf(player1duit));
        AnchorPane.setLeftAnchor(currentPlayer1DuitText, 100.22);
        AnchorPane.setTopAnchor(currentPlayer1DuitText, 319.10);

        Text currentPlayer2DuitText = new Text(String.valueOf(player2duit));
        AnchorPane.setLeftAnchor(currentPlayer2DuitText, 100.22);
        AnchorPane.setTopAnchor(currentPlayer2DuitText, 380.69);

        currentPane.getChildren().addAll(currentPlayerText, currentTurnText, currentPlayer1DuitText, currentPlayer2DuitText);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void pressed(MouseEvent event, KartuGUI k) {
        k.setColor(Color.GREEN);
        System.out.println("Hello " + k.getX() + " " + k.getY());
        initialX = k.getX();
        initialY = k.getY();
        if (initialX >= BASE_LADANG_X && initialX <= BASE_LADANG_X + WIDTH_LADANG && initialY >= BASE_LADANG_Y && initialY <= BASE_LADANG_Y + HEIGHT_LADANG){
            fromLadang = true;
            fromDeck = false;
        } else if (initialX >= BASE_DECK_AKTIF_X && initialX <= BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF && initialY >= BASE_DECK_AKTIF_Y && initialY <= BASE_DECK_AKTIF_Y + HEIGHT_DECK_AKTIF) {
            fromDeck = true;
            fromLadang = false;
        }
    }

    public void dragged(MouseEvent event, KartuGUI k) {
        double cursorX = event.getSceneX();
        double cursorY = event.getSceneY();
        k.setX(cursorX - k.getWidth()/2);
        k.setY(cursorY - k.getHeight()/2);
        k.draw();
    }

    public void released(MouseEvent event, KartuGUI k) {

        k.setColor(Color.TEAL);
        k.setImg();

        double card_middle_x = k.getX() + k.getRect().getWidth()/2.0;
        double card_middle_y = k.getY() + k.getRect().getHeight()/2.0;
        int initial_x_id;
        int initial_y_id;

        if (fromLadang){
            initial_x_id = (int)((initialX + k.getWidth()/2 - BASE_LADANG_X)/LADANG_TILE_WIDTH);
            initial_y_id = (int)((initialY + k.getHeight()/2 - BASE_LADANG_Y)/LADANG_TILE_HEIGHT);
        } else {
            initial_y_id = 0;
            initial_x_id = (int)((initialX + k.getWidth()/2 - BASE_DECK_AKTIF_X)/(DECK_AKTIF_TILE_WIDTH));
        }

        if (card_middle_x >= BASE_LADANG_X && card_middle_x <= BASE_LADANG_X + WIDTH_LADANG && card_middle_y <= BASE_LADANG_Y + HEIGHT_LADANG && card_middle_y >= BASE_LADANG_Y){
            /**
             * Card dilepaskan di petak ladang
             */
            if (!(k.getKartu() instanceof Makhluk)){
                k.setX(initialX);
                k.setY(initialY);
                k.draw();
                return;
            }

            int id_x = (int)((card_middle_x - BASE_LADANG_X)/LADANG_TILE_WIDTH);
            int id_y = (int)((card_middle_y - BASE_LADANG_Y)/LADANG_TILE_HEIGHT);

            try {
                controller.LetGoHandler((Makhluk) k.getKartu(), id_y, id_x, initial_y_id, initial_x_id, fromLadang, true);
            } catch (DiluarPetakException e) {throw new RuntimeException("WAA HOW");}
        } else if (card_middle_x >= BASE_DECK_AKTIF_X && card_middle_x <= BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF && card_middle_y <= BASE_DECK_AKTIF_Y + HEIGHT_DECK_AKTIF && card_middle_y >= BASE_DECK_AKTIF_Y) {

            /**
             * Card dilepaskan di petak deck
             */
            int id_y = 0;
            int id_x = (int)((card_middle_x - BASE_DECK_AKTIF_X)/(DECK_AKTIF_TILE_WIDTH));

            try {
                controller.LetGoHandler(k.getKartu(), id_y, id_x, initial_y_id, initial_x_id, fromLadang, false);
            } catch (DiluarPetakException e) {throw new RuntimeException("WAA HOW");}
        }

        for (int i = 0; i < currentPane.getChildren().size(); i++){
            if (currentPane.getChildren().get(i).getClass() == Rectangle.class){
                currentPane.getChildren().remove(i);
                i--;
            }
        }
        getCard();
        updateCard();
    }

    public void updateCard(){
        for (KartuGUI k: pageKartu) {
            currentPane.getChildren().add(k.getRect());
            k.getRect().setOnMousePressed(event -> pressed(event, k));
            k.getRect().setOnMouseDragged(event -> dragged(event, k));
            k.getRect().setOnMouseReleased(event -> released(event, k));
        }
    }

    public void getCard(){
        pageKartu = new ArrayList<>();

        PetakLadang currPetak = controller.getCurrentPetakLadang();

        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 5; col++){
                try {
                    if (currPetak.getMakhluk(col, row) != null){
                        pageKartu.add(new KartuGUI(currPetak.getMakhluk(col, row),BASE_LADANG_X + WIDTH_LADANG*col/5, BASE_LADANG_Y + HEIGHT_LADANG*row/4));
                    }
                } catch (DiluarPetakException e) {;}
            }
        }

        Deck currDeckAktif = controller.getCurrentDeckAktif();

        for(int col = 0; col < currDeckAktif.size(); col++){
            try {
                pageKartu.add(new KartuGUI(currDeckAktif.peekKartuAt(col), BASE_DECK_AKTIF_X + WIDTH_DECK_AKTIF*col/6, BASE_DECK_AKTIF_Y));
            } catch (DeckOutOfBoundsException | DeckEmptyException e) {/* Tidak akan terjadi */}
        }
    }

    public AnchorPane getCurrentPane(){
        return currentPane;
    }

    public MainController getController() {return controller;}

    public static void main(String[] args) {
        launch();
    }
}