package com.hbd;

import java.util.ArrayList;

import com.hbd.Kartu.Kartu;

public class Pemain {
    private Deck deck;
    private ArrayList<Kartu> deckAktif;
    private PetakLadang petakLadang;
    private int duit;

    public Pemain() {
        this.deck = new Deck();
        this.deckAktif = new ArrayList<Kartu>();
        this.petakLadang = new PetakLadang();
        this.duit = 0;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public ArrayList<Kartu> getDeckAktif() {
        return this.deckAktif;
    }

    public PetakLadang getPetakLadang() {
        return this.petakLadang;
    }

    public int getDuit() {
        return this.duit;
    }

    public void setDuit(int duit) {
        this.duit = duit;
    }
}
