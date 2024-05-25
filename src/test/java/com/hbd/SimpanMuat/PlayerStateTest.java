package com.hbd.SimpanMuat;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Deck.Exception.DeckPenuhException;
import com.hbd.Kartu.FactoryKartu;
import com.hbd.Kartu.Exception.CardNotFoundException;
import com.hbd.Kartu.Exception.JenisKartuTidakValidException;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.PetakLadang;
import com.hbd.PetakLadang.Exception.DiluarPetakException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStateTest {

    private PlayerState playerState;
    private Deck activeDeckMock;
    private PetakLadang petakLadangMock;

    @BeforeEach
    void setUp() {
        activeDeckMock = new Deck();
        petakLadangMock = new PetakLadang();
        playerState = new PlayerState(100, 5, activeDeckMock, petakLadangMock);
    }

    @Test
    void testGetDuit() {
        assertEquals(100, playerState.getDuit());
    }

    @Test
    void testGetUkuranDeck() {
        assertEquals(5, playerState.getUkuranDeck());
    }

    @Test
    void testGetActiveDeck() {
        assertEquals(activeDeckMock, playerState.getActiveDeck());
    }

    @Test
    void testGetPetakLadang() {
        assertEquals(petakLadangMock, playerState.getPetakLadang());
    }

    @Test
    void testGetActiveDeckEntries() throws DeckOutOfBoundsException, DeckEmptyException, CardNotFoundException,
            JenisKartuTidakValidException, DeckPenuhException {
        activeDeckMock.insertKartu(FactoryKartu.getKartu("Biji Labu"));
        activeDeckMock.insertKartu(FactoryKartu.getKartu("Ayam"));
        activeDeckMock.insertKartu(FactoryKartu.getKartu("Beruang"));

        List<Map<String, String>> expectedEntries = new ArrayList<>();
        Map<String, String> entry1 = new LinkedHashMap<>();
        entry1.put("LOKASI_KARTU", "A01");
        entry1.put("NAMA_KARTU", "Biji Labu");
        Map<String, String> entry2 = new LinkedHashMap<>();
        entry2.put("LOKASI_KARTU", "B01");
        entry2.put("NAMA_KARTU", "Ayam");
        Map<String, String> entry3 = new LinkedHashMap<>();
        entry3.put("LOKASI_KARTU", "C01");
        entry3.put("NAMA_KARTU", "Beruang");
        expectedEntries.add(entry1);
        expectedEntries.add(entry2);
        expectedEntries.add(entry3);

        assertEquals(expectedEntries, playerState.getActiveDeckEntries());
    }

    @Test
    void testGetLadangItems() throws CardNotFoundException, JenisKartuTidakValidException, DiluarPetakException {
        // PetakLadangCodeAdapter adaptedPetakLadangMock = new
        // PetakLadangCodeAdapter(petakLadangMock);
        petakLadangMock.setMakhluk(0, 0, (Makhluk) FactoryKartu.getKartu("Biji Labu"));
        petakLadangMock.setMakhluk(0, 1, (Makhluk) FactoryKartu.getKartu("Ayam"));
        List<String> list = new ArrayList<>();
        list.add("Accelerate");
        petakLadangMock.getMakhluk(0, 0).setEffect(list);

        List<Map<String, Object>> expectedItems = new ArrayList<>();
        Map<String, Object> item1 = new LinkedHashMap<>();
        item1.put("LOKASI_KARTU", "A01");
        item1.put("NAMA_KARTU", "Biji Labu");
        item1.put("PROGRESS_PANEN", 0);
        item1.put("ITEM_AKTIF", list);

        Map<String, Object> item2 = new LinkedHashMap<>();
        item2.put("LOKASI_KARTU", "B01");
        item2.put("NAMA_KARTU", "Ayam");
        item2.put("PROGRESS_PANEN", 0);
        item2.put("ITEM_AKTIF", new ArrayList<>());

        expectedItems.add(item1);
        expectedItems.add(item2);

        assertEquals(expectedItems, playerState.getLadangItems());
    }
}