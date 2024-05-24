package com.hbd.SimpanMuat;

import com.hbd.Deck.Deck;
import com.hbd.Deck.Exception.DeckEmptyException;
import com.hbd.Deck.Exception.DeckOutOfBoundsException;
import com.hbd.Kartu.Kartu;
import com.hbd.Kartu.Makhluk.Makhluk;
import com.hbd.PetakLadang.PetakLadang;
import com.hbd.PetakLadang.PetakLadangCodeAdapter;

import java.util.*;

public class PlayerState {
    private final int duit;
    private final int deckSize;
    private final Deck activeDeck;
    private final PetakLadang petakLadang;

    public PlayerState(int _duit, int _deckSize, Deck _activeDeck, PetakLadang _petakLadang){
        duit = _duit;
        deckSize = _deckSize;
        activeDeck = _activeDeck;
        petakLadang = _petakLadang;
    }

    public int getDuit(){
        return duit;
    }

    public int getUkuranDeck() {
        return deckSize;
    }

    public Deck getActiveDeck(){
        return activeDeck;
    }

    public PetakLadang getPetakLadang() {
        return petakLadang;
    }

    public List<Map<String, String>> getActiveDeckEntries(){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            for (int i = 0; i < activeDeck.size(); i++) {
                result.add(new LinkedHashMap<>());
                result.get(i).put("LOKASI_KARTU", (char)('A' + i) + "01");
                result.get(i).put("NAMA_KARTU", activeDeck.peekKartuAt(i).getNama());
            }
        } catch (DeckOutOfBoundsException | DeckEmptyException e){
            System.out.println("Anomaly in getActiveDeckEntries");
            return null;
        }
        return result;
    }

    public List<Map<String, Object>> getLadangItems(){
        PetakLadangCodeAdapter adaptedPetakLadang = new PetakLadangCodeAdapter(petakLadang);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Iterator<Makhluk> currIter = adaptedPetakLadang.iterator(); currIter.hasNext();){
            String location = ((PetakLadangCodeAdapter.PetakLadangCodeAdapterIterator) currIter).getCurrentCoordinates();
            Makhluk makhluk = currIter.next();

            if (makhluk == null) {continue;}

            Map<String, Object> ladang_entry = new LinkedHashMap<>();
            ladang_entry.put("LOKASI_KARTU", location);
            ladang_entry.put("NAMA_KARTU", makhluk.getNama());
            ladang_entry.put("PROGRESS_PANEN", makhluk.getProgressPanen());
            ladang_entry.put("ITEM_AKTIF", makhluk.getEffect());
            result.add(ladang_entry);
        }

        return result;
    }
}
