package com.hbd.SimpanMuat;

import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Exception.CardNotFoundException;
import com.hbd.Kartu.Exception.JenisKartuTidakValidException;
import com.hbd.Pemain.Pemain;
import com.hbd.Toko.Toko;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotarisTest {

    private Notaris notaris;
    private Toko toko;
    private Pemain pemain;

    @BeforeEach
    void setUp() {
        notaris = new Notaris();
        Toko.resetInstance();
        toko = Toko.getInstance();
        pemain = new Pemain();
    }

    @Test
    void testGetGameState() {
        Map<String, Integer> expectedShopItems = new LinkedHashMap<>();

        GameState gameState = notaris.getGameState(20, toko);

        assertNotNull(gameState);
        assertEquals(20, gameState.getCurrentTurn());
        assertEquals(expectedShopItems, gameState.getShopItems());
    }

    @Test
    void testGetPlayerState() throws CardNotFoundException, JenisKartuTidakValidException, DeckPenuhException {
        pemain.setDuit(500);
        pemain.setDeckSize(5);
        pemain.getDeckAktif().insertKartu(FactoryKartu.getKartu("Telur"));
        pemain.getDeckAktif().insertKartu(FactoryKartu.getKartu("Daging Beruang"));
        pemain.getDeckAktif().insertKartu(FactoryKartu.getKartu("Sirip Hiu"));

        PlayerState playerState = notaris.getPlayerState(pemain);

        assertNotNull(playerState);
        assertEquals(500, playerState.getDuit());
        assertEquals(5, playerState.getUkuranDeck());
        // assertEquals(pemain.getDeckAktif(), playerState.getDeckAktif());
        assertEquals(pemain.getPetakLadang(), playerState.getPetakLadang());
    }
}
