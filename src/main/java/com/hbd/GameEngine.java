package com.hbd;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Pemain;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.PetakLadang.PetakLadang;
import com.hbd.SimpanMuat.GameState;
import com.hbd.SimpanMuat.Language.Language;
import com.hbd.SimpanMuat.Librarian;
import com.hbd.SimpanMuat.Notaris;
import com.hbd.SimpanMuat.PlayerState;
import com.hbd.Toko.Toko;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameEngine {
    private static GameEngine instance = null;
    private static final Librarian librarian = new Librarian();
    private Pemain pemain1 = null;
    private Pemain pemain2 = null;
    private int nomorTurn = 1;
    private Pemain currentPemain = null;
    private final Notaris notaris = new Notaris();

    private GameEngine() {
        System.out.println("Game Engine Has Been Created");
    }

    public static synchronized GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();

        return instance;
    }

    public static synchronized void resetInstance() {
        instance = new GameEngine();
    }

    public static synchronized void loadInstance(GameState gameState, PlayerState player1State,
            PlayerState player2State) {
        instance.nomorTurn = gameState.getCurrentTurn();

        Toko.resetInstance();

        for (Map.Entry<String, Integer> shop_item : gameState.getShopItems().entrySet()) {
            for (int i = 0; i < shop_item.getValue(); i++) {
                Toko.getInstance().tambahItemKeToko((Produk) FactoryKartu.getKartu(shop_item.getKey()));
            }
        }

        instance.pemain1 = new Pemain();
        instance.pemain1.setDuit(player1State.getDuit());
        instance.pemain1.setDeckSize(player1State.getUkuranDeck());
        instance.pemain1.setDeckAktif(player1State.getActiveDeck());
        instance.pemain1.setPetakLadang(player1State.getPetakLadang());

        instance.pemain2 = new Pemain();
        instance.pemain2.setDuit(player2State.getDuit());
        instance.pemain2.setDeckSize(player2State.getUkuranDeck());
        instance.pemain2.setDeckAktif(player2State.getActiveDeck());
        instance.pemain2.setPetakLadang(player2State.getPetakLadang());

        if (instance.nomorTurn % 2 == 1) {
            instance.currentPemain = instance.pemain1;
        } else {
            instance.currentPemain = instance.pemain2;
        }
    }

    public Pemain getCurrentPemain() {
        return currentPemain;
    }

    public void initializeDefault() {
        loadInstance(new GameState(1, new HashMap<>()), new PlayerState(0, 40, new Deck(6), new PetakLadang()),
                new PlayerState(0, 40, new Deck(6), new PetakLadang()));
        try {
            pemain1.getDeckAktif().addRandom(2);
            pemain2.getDeckAktif().addRandom(2);
        } catch (DeckPenuhException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveState(Language lang, String FolderPath) throws IOException {
        librarian.save(notaris.getGameState(nomorTurn, Toko.getInstance()),
                notaris.getPlayerState(pemain1),
                notaris.getPlayerState(pemain2),
                lang,
                FolderPath);
    }

    public void start() {

    }

    public void next() {
        pemain1.updateUmurPetak();
        pemain2.updateUmurPetak();
        nomorTurn++;

        if (nomorTurn % 2 == 1) {
            currentPemain = pemain1;
        } else {
            currentPemain = pemain2;
        }
    }

    public void loadFile(String path) throws Exception {
        librarian.load(librarian.getLanguageAtIndex(0), path);
    }

    public int getNomorTurn() {
        return nomorTurn;
    }

    public int getPlayer1Duit() {
        return pemain1.getDuit();
    }

    public int getPlayer2Duit() {
        return pemain2.getDuit();
    }

    public static void main(String args[]) throws IOException {
        librarian.study(
                "C:/Users/HP/Documents/Tugas_Coolyeah/semester_4/OOP/pluginCode/jsonPlugin/target/hbd_jsonPlugin-1.0-jar-with-dependencies.jar");
        try {
            librarian.load(librarian.getLanguageAtIndex(1), "cobajson");
        } catch (FileNotFoundException e) {
            /**/
        } catch (DeckPenuhException | DiluarPetakException e) {
            /**/
        }
        getInstance().saveState(librarian.getLanguageAtIndex(0), "backtotxt");
    }
}