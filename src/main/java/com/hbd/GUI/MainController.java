package com.hbd.GUI;


import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.GameEngine;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
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

    public void LetGoHandler(Makhluk makhluk, int row, int column, int initialRow, int initialColumn, boolean fromLadang, boolean toLadang) throws DiluarPetakException {
        if (toLadang) {
            if (fromLadang) {
                if (getCurrentPetakLadang().getMakhluk(column, row) != null) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(column, row, makhluk);
                    getCurrentPetakLadang().setMakhluk(initialColumn, initialRow, null);
                }
            } else {
                if (getCurrentPetakLadang().getMakhluk(column, row) != null) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(column, row, makhluk);
                    try {
                        getCurrentDeckAktif().takeKartuAt(initialColumn);
                    } catch (Exception e) {;}
                }
            }
        } else {
            if (fromLadang) {
                if (getCurrentDeckAktif().remainingSlot() == 0) {
                    return;
                } else {
                    getCurrentPetakLadang().setMakhluk(initialColumn, initialRow, null);
                    try{
                        getCurrentDeckAktif().insertKartu(makhluk);
                    } catch (DeckPenuhException e) {/* Tidak Mungkin */}
                }
            } else{
                try {
                    getCurrentDeckAktif().takeKartuAt(initialColumn);
                    getCurrentDeckAktif().insertKartu(makhluk);
                } catch (Exception e) {/* Tidak akan terjadi */}
            }
        }
    }

    public void initializeGame(){
        GameEngine.getInstance().initializeDefault();
    }

    public PetakLadang getCurrentPetakLadang(){
        return GameEngine.getInstance().getCurrentPemain().getPetakLadang();
    }

    public Deck getCurrentDeckAktif(){
        return GameEngine.getInstance().getCurrentPemain().getDeckAktif();
    }
}