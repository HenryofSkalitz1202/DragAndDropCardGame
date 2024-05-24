package com.hbd;

import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.KartuGUI;
import com.hbd.Kartu.Item.Item;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.Kartu.Produk.Produk;
import com.hbd.Pemain.Pemain;
import com.hbd.PetakLadang.Exception.DiluarPetakException;
import com.hbd.SimpanMuat.GameState;
import com.hbd.SimpanMuat.Librarian;
import com.hbd.SimpanMuat.Notaris;
import com.hbd.SimpanMuat.PlayerState;
import com.hbd.Toko.Toko;

import java.io.IOException;
import java.util.Map;

public class GameEngine {
    private static GameEngine instance = null;
    private static final Librarian librarian = new Librarian();
    private Pemain pemain1 = null;
    private Pemain pemain2 = null;
    private int nomorTurn = 1;
    private static Pemain currentPemain = null;
    private final Notaris notaris = new Notaris();

    private GameEngine() {
        System.out.println("Game Engine Has Been Created");
    }

    public static synchronized GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public static synchronized void resetInstance() {
        instance = new GameEngine();
    }

    public static synchronized void loadInstance(GameState gameState, PlayerState player1State, PlayerState player2State) {
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

    public static void start() {
        KartuGUI.initializePaths();
    }

    public void next() throws DeckPenuhException {
        pemain1.updateUmurPetak();
        pemain2.updateUmurPetak();
        nomorTurn++;

        if (nomorTurn % 2 == 1) {
            currentPemain = pemain1;
        } else {
            currentPemain = pemain2;
        }
        fasePengocokkan();
        faseBebas();
        // Tentukan apakah akan terjadi serangan beruang
    }

    public void fasePengocokkan() {
        // Shuffle Kartu hingga pemain puas
    }

    public void faseBebas() {
        // Bebas ngapain aja
    }

    public static void faseBearAttack() throws Exception {
        BearAttack.startBearAttack(currentPemain.getPetakLadang(), currentPemain.getDeckAktif());
    }

    public static void main(String[] args) throws DiluarPetakException, IOException, DeckPenuhException, Exception {
        start();
        GameEngine ge = GameEngine.getInstance();

        currentPemain = new Pemain();
        Item protectItem = (Item) FactoryKartu.getKartu("Protect");
        Item trapItem = (Item) FactoryKartu.getKartu("Trap");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Makhluk hewanLadang;
                if (i <= 2 && j <= 3) {
                    hewanLadang = (Makhluk) FactoryKartu.getKartu("Sapi");
                    currentPemain.getPetakLadang().setMakhluk(j, i, hewanLadang);
                } else if (i == 3) {
                    hewanLadang = (Makhluk) FactoryKartu.getKartu("Ayam");
                    hewanLadang.hisabItem(protectItem);
                    currentPemain.getPetakLadang().setMakhluk(j, i, hewanLadang);
                } else {
                    hewanLadang = (Makhluk) FactoryKartu.getKartu("Kuda");
                    hewanLadang.hisabItem(trapItem);
                    currentPemain.getPetakLadang().setMakhluk(j, i, hewanLadang);
                }
            }
        }

        ge = GameEngine.getInstance();
        faseBearAttack();
    }
}

