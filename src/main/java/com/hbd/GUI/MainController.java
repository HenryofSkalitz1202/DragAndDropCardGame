package com.hbd.GUI;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private BorderPane home;

    @FXML
    private void ButtonLadangku(ActionEvent event) {
        System.out.println("Test1");

        // Kode di bawah ini untuk mengganti view dari bagian tengah halaman
        // Ganti "testing" dengan nama file fxml asli, misal "ladangku"
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("testing");
        home.setCenter(view);
    }

    @FXML
    private void ButtonLadangLawan(ActionEvent event) {
        System.out.println("Test2");

        // Kode di bawah ini untuk mengganti view dari bagian tengah halaman
        // Ganti "testing" dengan nama file fxml asli, misal "ladangku"
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("draggable");
        home.setCenter(view);

    }

    @FXML
    private void ButtonToko(ActionEvent event) {
        ;
    }

    @FXML
    private void ButtonSaveState(ActionEvent event) {
        System.out.println("Test4");

    }

    @FXML
    private void ButtonLoadPlugin(ActionEvent event) {
        System.out.println("Test5");
        Stage stage = new Stage();
        stage.setTitle("Load Plugin");

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("loadplugin");

        if (view != null) {
            Scene scene = new Scene(view, 600, 400);
            stage.setScene(scene);
            stage.show();
            System.out.println("Scene displayed successfully");
        } else {
            System.out.println("Failed to load view");
        }

    }

    @FXML
    private void ButtonLoadState(ActionEvent event) {
        System.out.println("Test6");
        Stage stage = new Stage();
        stage.setTitle("Load State");

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("loadstate");

        if (view != null) {
            Scene scene = new Scene(view, 600, 400);
            stage.setScene(scene);
            stage.show();
            System.out.println("Scene displayed successfully");
        } else {
            System.out.println("Failed to load view");
        }
    }

    @FXML
    private void ButtonNext(ActionEvent event) {
        GameEngine.getInstance().next();
        if (getCurrentDeckAktif().remainingSlot() != 0) {
            App.getShuffleController().enterShuffle();
        } else {
            App.getMainPage().switchTo();
        }
    }

    public void LetGoHandler(Kartu makhluk, int row, int column, int initialRow, int initialColumn, boolean fromLadang,
            boolean toLadang) throws DiluarPetakException {
        if (toLadang) {
            if (fromLadang) {
                if (getCurrentPetakLadang().getMakhluk(column, row) != null) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(column, row, (Makhluk) makhluk);
                    getCurrentPetakLadang().setMakhluk(initialColumn, initialRow, null);
                }
            } else {
                if (getCurrentPetakLadang().getMakhluk(column, row) != null) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(column, row, (Makhluk) makhluk);
                    try {
                        getCurrentDeckAktif().takeKartuAt(initialColumn);
                    } catch (Exception e) {
                        ;
                    }
                }
            }
        } else {
            if (fromLadang) {
                if (getCurrentDeckAktif().remainingSlot() == 0) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(initialColumn, initialRow, null);
                    try {
                        getCurrentDeckAktif().insertKartu(makhluk);
                    } catch (DeckPenuhException e) {
                        /* Tidak Mungkin */}
                }
            } else {
                try {
                    getCurrentDeckAktif().takeKartuAt(initialColumn);
                    getCurrentDeckAktif().insertKartu(makhluk);
                } catch (Exception e) {
                    /* Tidak akan terjadi */}
            }
        }
    }

    public void initializeGame() {
        GameEngine.getInstance().initializeDefault();
    }

    public PetakLadang getCurrentPetakLadang() {
        return GameEngine.getInstance().getCurrentPemain().getPetakLadang();
    }

    public Deck getCurrentDeckAktif() {
        return GameEngine.getInstance().getCurrentPemain().getDeckAktif();
    }

    public int getCurrentTurn() {
        return GameEngine.getInstance().getNomorTurn();
    }

    public int getCurrentPlayer1Duit() {
        return GameEngine.getInstance().getPlayer1Duit();
    }

    public int getCurrentPlayer2Duit() {
        return GameEngine.getInstance().getPlayer2Duit();
    }
}