package com.hbd.SimpanMuat;

import com.hbd.Deck.Deck;

public class PlayerState {
    private int duit;
    private int deckSize;
    private Deck activeDeck;
//    private PetakLadang petakLadang;

//    public PlayerState(int _duit, int _deckSize, Deck _activeDeck, PetakLadang _petakLadang){
//        duit = _duit;
//        deckSize = _deckSize;
//        activeDeck = _activeDeck;
//        petakLadang = _petakLadang;
//    }

    public int getDuit(){
        return duit;
    }

    public int getUkuranDeck() {
        return deckSize;
    }

//    public int getUkuranDeckAktif(){
//        return activeDeck.size()
//    }

//    public Map<String, String> getActiveDeckEntries(){
//        ;
//    }

//    public int getJumlahKartuLadang(){
//        ;
//    }

//    public List<LadangItemEntry> getLadangItems{
//        ;
//    }
}
